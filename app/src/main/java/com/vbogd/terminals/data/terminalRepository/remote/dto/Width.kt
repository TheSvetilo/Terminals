package com.vbogd.terminals.data.terminalRepository.remote.dto

import com.google.gson.annotations.SerializedName

data class Width(
    @SerializedName("640")
    val x640: X640,
    @SerializedName("944")
    val x944: X944,
    @SerializedName("960")
    val x960: X960
)