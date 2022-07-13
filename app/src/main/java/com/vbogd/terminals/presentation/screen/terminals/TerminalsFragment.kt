package com.vbogd.terminals.presentation.screen.terminals

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.vbogd.terminals.App
import com.vbogd.terminals.R
import com.vbogd.terminals.databinding.FragmentTerminalsBinding
import com.vbogd.terminals.domain.model.Direction
import com.vbogd.terminals.presentation.MainActivity
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TerminalsFragment : Fragment() {

    @Inject
    lateinit var vmFactory: TerminalsViewModelFactory
    lateinit var viewModel: TerminalsViewModel

    lateinit var binding: FragmentTerminalsBinding

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
                )
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
            viewModel.getTerminalsByDirection(direction)
        }

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> viewModel.getTerminalsByDirection(Direction.FROM)
                    1 -> viewModel.getTerminalsByDirection(Direction.TO)
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.terminalMenuSearch -> {
//
//                val searchView = item as SearchView
//
//                Observable.create(ObservableOnSubscribe<String> { subscriber ->
//                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                        override fun onQueryTextSubmit(newText: String?): Boolean {
//                            subscriber.onNext(newText!!)
//                            return false
//                        }
//
//                        override fun onQueryTextChange(newText: String?): Boolean {
//                            subscriber.onNext(newText!!)
//                            return false
//                        }
//
//                    })
//                })
//                    .map { text -> text.trim() }
//                    .debounce(300, TimeUnit.MILLISECONDS)
//                    .distinct()
//                    .filter { text -> text.isNotBlank() }
//                    .subscribe { text ->
//                        Log.d("SEARCH", "subscriber: $text")
//                    }

                true
            }
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