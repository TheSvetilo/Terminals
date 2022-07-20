package com.vbogd.terminals.utils

import com.google.android.material.tabs.TabLayout

abstract class OnTabItemSelectedListener : TabLayout.OnTabSelectedListener {

    override fun onTabSelected(tab: TabLayout.Tab?) {}

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {}
}