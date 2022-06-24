package com.vbogd.terminals.presentation.screen.terminals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vbogd.terminals.databinding.ListItemTerminalBinding
import com.vbogd.terminals.domain.model.Terminal

class TerminalsAdapter :
    ListAdapter<Terminal, TerminalsAdapter.TerminalViewHolder>(DiffCallback()) {

    class TerminalViewHolder(private val binding: ListItemTerminalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(terminal: Terminal) {
            binding.terminal = terminal
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TerminalViewHolder {
        return TerminalViewHolder(
            ListItemTerminalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TerminalViewHolder, position: Int) {
        val terminal = getItem(position)
        holder.bind(terminal)
    }

}

class DiffCallback : DiffUtil.ItemCallback<Terminal>() {
    override fun areItemsTheSame(oldItem: Terminal, newItem: Terminal): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Terminal, newItem: Terminal): Boolean {
        return oldItem.id == newItem.id
    }
}
