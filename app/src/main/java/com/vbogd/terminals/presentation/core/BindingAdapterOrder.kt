package com.vbogd.terminals.presentation.core

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.vbogd.terminals.R
import com.vbogd.terminals.domain.model.Order

@BindingAdapter("orderTerminalNameFrom")
fun bindDirectionFromView(view: TextView, order: Order?) {
    if (order != null)
        view.text =
            if (order.terminalFrom != null) order.terminalFrom.name else view.context.getString(
                R.string.direction_no_name
            )
}

@BindingAdapter("orderTerminalNameTo")
fun bindDirectionToView(view: TextView, order: Order?) {
    if (order != null)
        view.text =
            if (order.terminalTo != null) order.terminalTo.name else view.context.getString(
                R.string.direction_no_name
            )
}

@BindingAdapter("orderTerminalAddressFromVisibility")
fun bindTerminalAddressFromVisibility(view: TextView, order: Order?) {
    if (order != null)
        view.text =
            if (order.terminalFrom != null) order.terminalFrom.address else view.context.getString(
                R.string.direction_no_address
            )
}

@BindingAdapter("orderTerminalAddressToVisibility")
fun bindTerminalAddressToVisibility(view: TextView, order: Order?) {
    if (order != null)
        view.text =
            if (order.terminalTo != null) order.terminalTo.address else view.context.getString(
                R.string.direction_no_address
            )
}

@BindingAdapter("order")
fun bindOrderTitle(view: TextView, order: Order?) {
    if (order != null) {
        view.text =
            view.context.getString(R.string.direction_order_title) + order.id + " (статус: ${order.status})"
    } else {
        view.text = view.context.getString(R.string.direction_order_title_not_found)
    }
}