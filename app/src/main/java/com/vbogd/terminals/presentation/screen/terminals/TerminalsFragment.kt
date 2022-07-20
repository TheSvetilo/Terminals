package com.vbogd.terminals.presentation.screen.terminals

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.vbogd.terminals.App
import com.vbogd.terminals.R
import com.vbogd.terminals.databinding.FragmentTerminalsBinding
import com.vbogd.terminals.domain.model.Direction
import com.vbogd.terminals.presentation.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TerminalsFragment : Fragment() {

    @Inject
    lateinit var vmFactory: TerminalsViewModelFactory
    lateinit var viewModel: TerminalsViewModel

    private lateinit var binding: FragmentTerminalsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var userLocation: Location? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTerminalsBinding.inflate(inflater)

        viewModel = ViewModelProvider(this, vmFactory)
            .get(TerminalsViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.terminalList.adapter = TerminalsAdapter(OnClickListener { terminal ->
            this.findNavController().navigate(
                TerminalsFragmentDirections.sendChosenTerminalId(
                    orderId = viewModel.currentOrderId.toString(),
                    orderDirectionId = binding.tabs.selectedTabPosition,
                    terminalId = terminal.id
                ),
                navOptions {
                    anim {
                        enter = android.R.animator.fade_in
                        exit = android.R.animator.fade_out
                    }
                }
            )
        })

        val currentOrderId = TerminalsFragmentArgs.fromBundle(requireArguments()).orderId
        val orderDirectionTab =
            TerminalsFragmentArgs.fromBundle(requireArguments()).orderDirectionId
        if (currentOrderId.isNotEmpty()) {
            binding.tabs.getTabAt(orderDirectionTab)!!.select()
            val direction = when (orderDirectionTab) {
                0 -> Direction.FROM
                1 -> Direction.TO
                else -> Direction.BOTH
            }

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        userLocation = location
                    }
                    viewModel.getTerminalsByDirection(direction, userLocation)
                }
        }

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> viewModel.getTerminalsByDirection(Direction.FROM, userLocation)
                    1 -> viewModel.getTerminalsByDirection(Direction.TO, userLocation)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.terminal_menu, menu)
        viewModel.filter.observe(viewLifecycleOwner) {
            val filterIcon = menu.findItem(R.id.terminalMenuFilter)
            if (it != TerminalFilter.DEFAULT) {
                filterIcon.setIcon(R.drawable.ic_outline_filter_list_24_active)
            } else {
                filterIcon.setIcon(R.drawable.ic_outline_filter_list_24)
            }
        }
        val searchView = menu.findItem(R.id.terminalMenuSearch).actionView as SearchView
        search(searchView)
    }

    private fun search(searchView: SearchView) {
        Observable.create<String> {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (!it.isDisposed) {
                        it.onNext(newText!!)
                    }
                    return false
                }
            })
            searchView.setOnCloseListener(SearchView.OnCloseListener {
                viewModel.searchTerminalClear()
                return@OnCloseListener true
            })
        }
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .map { it.trim() }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewModel.searchTerminal(it)
            }, {

            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.terminalMenuFilter -> {
                showFilterBottomSheet()
                true
            }
            else -> true
        }
    }

    private fun showFilterBottomSheet() {
        val dialog = BottomSheetDialog(requireActivity() as MainActivity)
        val view = layoutInflater.inflate(R.layout.terminal_filter_dialog, null)

        val nameSort = view.findViewById<TextView>(R.id.terminalFilterByName)
        val distanceSort = view.findViewById<TextView>(R.id.terminalFilterByDistance)
        nameSort.setOnClickListener {

            viewModel.applyTerminalFilter(TerminalFilter.NAME)
            dialog.dismiss()
        }
        distanceSort.setOnClickListener {
            viewModel.applyTerminalFilter(TerminalFilter.DISTANCE)
            dialog.dismiss()
        }

        dialog.setCancelable(true)
        dialog.setContentView(view)
        dialog.show()
    }


}