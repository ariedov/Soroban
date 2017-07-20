package com.dleibovych.soroban.action

import android.content.Context

interface ActionView {

    fun updateDisplay(value: String)
    fun showSuccess()
    fun showError()
    fun enableInput()
    fun disableInput()
    fun getContext(): Context
}