package com.vbogd.terminals.presentation.screen.direction

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vbogd.terminals.databinding.FragmentDirectionBinding

class DirectionFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            DirectionViewModelFactory()
        ).get(DirectionViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDirectionBinding.inflate(inflater)

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