package com.vbogd.terminals.presentation.screen.terminals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.vbogd.terminals.R
import com.vbogd.terminals.databinding.FragmentTerminalsBinding
import com.vbogd.terminals.domain.model.Direction
import com.vbogd.terminals.presentation.screen.direction.DirectionViewModel

class TerminalsFragment : Fragment() {

    private val viewModel by lazy {
        val currentOrderId = TerminalsFragmentArgs.fromBundle(requireArguments()).orderId
        val orderDirectionTab =
            TerminalsFragmentArgs.fromBundle(requireArguments()).orderDirectionId

        ViewModelProvider(
            this,
            TerminalsViewModelFactory(
                currentOrderId,
                orderDirectionTab
            )
        ).get(TerminalsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTerminalsBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.terminalList.adapter = TerminalsAdapter()
        binding.viewModel = viewModel

        viewModel.orderDirection.observe(viewLifecycleOwner) {
            binding.tabs.getTabAt(it)!!.select()
        }

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.id) {
                    R.id.tabTerminalFrom -> viewModel.getTerminalsByDirection(Direction.FROM)
                    R.id.tabTerminalTo -> viewModel.getTerminalsByDirection(Direction.TO)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        return binding.root
    }

}