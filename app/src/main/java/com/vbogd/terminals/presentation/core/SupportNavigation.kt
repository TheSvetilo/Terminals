package com.vbogd.terminals.presentation.core


interface HasCustomAction {

    fun getCustomAction(): CustomAction

}

interface HasCustomTitle {

    fun getTitleRes(): Int

}

data class CustomAction(
    val iconRes: Int,
    val descriptionRes: Int,
    val action: Runnable
) {

}