package com.vbogd.terminals.presentation.screen.direction

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vbogd.terminals.R
import com.vbogd.terminals.databinding.FragmentDirectionBinding

class DirectionFragment : Fragment() {

    private val viewModel: DirectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDirectionBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

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