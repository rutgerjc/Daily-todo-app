package com.runitrut.dailytodoapp

import android.widget.CheckBox

// This data class holds the modeled information that each individual recycler-view item will hold
data class Todo(
    val title: String,
    var isChecked: Boolean = false
)