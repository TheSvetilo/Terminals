package com.vbogd.terminals.presentation.screen.direction

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vbogd.terminals.App
import com.vbogd.terminals.R
import com.vbogd.terminals.databinding.FragmentDirectionBinding
import javax.inject.Inject

class DirectionFragment : Fragment() {

    @Inject
    lateinit var vmFactory: DirectionViewModelFactory
    lateinit var viewModel: DirectionViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDirectionBinding.inflate(inflater)

        viewModel = ViewModelProvider(this, vmFactory)
            .get(DirectionViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val orderId = DirectionFragmentArgs.fromBundle(requireArguments()).orderId
        val terminalId = DirectionFragmentArgs.fromBundle(requireArguments()).terminalId
        val direction = DirectionFragmentArgs.fromBundle(requireArguments()).orderDirectionId
        if (!orderId.isNullOrEmpty() && !terminalId.isNullOrEmpty()) {
            viewModel.setDirection(orderId, direction, terminalId)
        }

        binding.directionFrom.setOnClickListener {
            this.findNavController().navigate(
                DirectionFragmentDirections.showTerminals(
                    orderId = viewModel.currentOrder.value!!.id,
                    orderDirectionId = 0
                )
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
                Toast.makeText(requireActivity(), "Data has been dropped", Toast.LENGTH_SHORT)
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}