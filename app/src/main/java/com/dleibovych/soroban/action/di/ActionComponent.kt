package com.dleibovych.soroban.action.di

import com.dleibovych.soroban.action.ActionActivity
import com.dleibovych.soroban.action.ActionScope
import com.dleibovych.soroban.action.countdown.CountdownView
import com.dleibovych.soroban.action.countdown.di.CountdownModule
import com.dleibovych.soroban.numpad.NumpadView
import com.dleibovych.soroban.numpad.di.NumpadModule
import dagger.Component

@ActionScope @Component(modules = arrayOf(NumpadModule::class, ActionModule::class, CountdownModule::class))
interface ActionComponent {
    fun inject(numpadView: NumpadView)
    fun inject(activity: ActionActivity)
    fun inject(countdownView: CountdownView)
}