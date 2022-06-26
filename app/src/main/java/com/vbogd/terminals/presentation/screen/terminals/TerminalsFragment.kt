package com.vbogd.terminals.presentation.screen.terminals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.vbogd.terminals.R
import com.vbogd.terminals.databinding.FragmentTerminalsBinding
import com.vbogd.terminals.domain.model.Direction

class TerminalsFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            TerminalsViewModelFactory()
        ).get(TerminalsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTerminalsBinding.inflate(inflater)

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
            viewModel.getTerminalsByDirection(orderDirectionTab)
        }

        viewModel.orderDirection.observe(viewLifecycleOwner) {
            binding.tabs.getTabAt(it)!!.select()
        }

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.id) {
                    R.id.tabTerminalFrom -> viewModel.getTerminalsByDirection(0)
                    R.id.tabTerminalTo -> viewModel.getTerminalsByDirection(1)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        return binding.root
    }

}