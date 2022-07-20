package com.vbogd.terminals.presentation.screen.direction

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.material.snackbar.Snackbar
import com.vbogd.terminals.App
import com.vbogd.terminals.R
import com.vbogd.terminals.databinding.FragmentDirectionBinding
import javax.inject.Inject

class DirectionFragment : Fragment() {

    @Inject
    lateinit var vmFactory: DirectionViewModelFactory
    lateinit var viewModel: DirectionViewModel

    lateinit var binding: FragmentDirectionBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDirectionBinding.inflate(inflater)

        viewModel = ViewModelProvider(this, vmFactory)
            .get(DirectionViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val terminalId = DirectionFragmentArgs.fromBundle(requireArguments()).terminalId
        val direction = DirectionFragmentArgs.fromBundle(requireArguments()).orderDirectionId
        if (!terminalId.isNullOrEmpty()) {
            viewModel.setDirection(direction, terminalId)
        } else {
            viewModel.getLastOrder()
        }

        binding.directionFrom.setOnClickListener {
            this.findNavController().navigate(
                DirectionFragmentDirections.showTerminals(
                    orderId = viewModel.currentOrder.value!!.id,
                    orderDirectionId = 0
                ),
                navOptions {
                    anim {
                        enter = android.R.animator.fade_in
                        exit = android.R.animator.fade_out
                    }
                }
            )
        }

        binding.directionTo.setOnClickListener {
            this.findNavController().navigate(
                DirectionFragmentDirections.showTerminals(
                    orderId = viewModel.currentOrder.value!!.id,
                    orderDirectionId = 1
                )
            )
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.order_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuSetting -> {
                viewModel.clearOrderData()
                Snackbar.make(
                    binding.root,
                    getString(R.string.menu_order_drop_data_result),
                    Snackbar.LENGTH_SHORT
                ).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}