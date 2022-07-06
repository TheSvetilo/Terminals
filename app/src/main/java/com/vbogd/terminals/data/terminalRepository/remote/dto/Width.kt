package com.vbogd.terminals.data.terminalRepository.remote.dto

import com.google.gson.annotations.SerializedName

data class Width(
    @SerializedName("width640")
    val `640`: X640,
    @SerializedName("width944")
    val `944`: X944,
    @SerializedName("width960")
    val `960`: X960
)