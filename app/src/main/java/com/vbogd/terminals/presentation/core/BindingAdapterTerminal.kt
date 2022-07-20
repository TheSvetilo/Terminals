package com.vbogd.terminals.presentation.core

import android.net.Uri
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vbogd.terminals.R
import com.vbogd.terminals.domain.model.Terminal
import com.vbogd.terminals.presentation.screen.terminals.TerminalFilter
import com.vbogd.terminals.presentation.screen.terminals.TerminalsAdapter

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

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, terminal: Terminal) {
    terminal.imageAddress.let {
        val imgUri = Uri.parse(it).buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_outline_image_placeholder_24)
                    .error(R.drawable.ic_baseline_broken_image_24)
            )
            .into(imageView)
    }
}

@BindingAdapter("distance")
fun bindDistance(textView: TextView, terminal: Terminal) {
    if (terminal.distance == 0) {
        textView.visibility = View.INVISIBLE
        textView.text = ""
    } else {
        textView.visibility = View.VISIBLE
        textView.text =
            terminal.distance.toString() + textView.context.getString(R.string.terminal_item_distance_postfix)
    }
}