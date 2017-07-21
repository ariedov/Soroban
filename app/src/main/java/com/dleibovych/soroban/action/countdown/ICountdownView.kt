package com.dleibovych.soroban.action.countdown

import com.dleibovych.soroban.action.ActionScope

@ActionScope interface ICountdownView {

    fun updateCountdown(cd: String)
    fun finish()
}