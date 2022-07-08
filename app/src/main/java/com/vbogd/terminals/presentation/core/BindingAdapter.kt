package com.vbogd.terminals.presentation.core

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vbogd.terminals.R
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.model.Terminal
import com.vbogd.terminals.presentation.screen.terminals.TerminalsAdapter

@BindingAdapter("orderTerminalNameFrom")
fun bindDirectionFromView(view: TextView, orderDirection: Order) {
    view.text =
        if (orderDirection.terminalFrom != null) orderDirection.terminalFrom.name else view.context.getString(
            R.string.direction_no_name
        )
}

@BindingAdapter("orderTerminalNameTo")
fun bindDirectionToView(view: TextView, orderDirection: Order) {
    view.text =
        if (orderDirection.terminalTo != null) orderDirection.terminalTo.name else view.context.getString(
            R.string.direction_no_name
        )
}

@BindingAdapter("orderTerminalAddressFromVisibility")
fun bindTerminalAddressFromVisibility(view: TextView, orderDirection: Order) {
    view.text =
        if (orderDirection.terminalFrom != null) orderDirection.terminalFrom.address else view.context.getString(
            R.string.direction_no_address
        )
//    view.visibility =
//        if (orderDirection.terminalFrom != null) View.VISIBLE else View.GONE
}

@BindingAdapter("orderTerminalAddressToVisibility")
fun bindTerminalAddressToVisibility(view: TextView, orderDirection: Order) {
    view.text =
        if (orderDirection.terminalTo != null) orderDirection.terminalTo.address else view.context.getString(
            R.string.direction_no_address
        )
}

@BindingAdapter("listItem")
fun bindRecyclerView(recyclerView: RecyclerView, terminals: List<Terminal>?) {
    val adapter = recyclerView.adapter as TerminalsAdapter
    adapter.submitList(terminals) {
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("workHours")
fun bindWorkHours(textView: TextView, terminal: Terminal) {
    textView.text =
        terminal.workHours.ifEmpty { textView.context.getString(R.string.terminal_item_no_workHours) }
}