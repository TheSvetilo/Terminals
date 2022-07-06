package com.vbogd.terminals.presentation.screen.direction

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vbogd.terminals.App
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

        return binding.root
    }

}