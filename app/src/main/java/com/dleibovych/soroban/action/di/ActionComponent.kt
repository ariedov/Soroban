package com.dleibovych.soroban.action.di

import com.dleibovych.soroban.action.ActionActivity
import com.dleibovych.soroban.action.ActionScope
import com.dleibovych.soroban.numpad.NumpadView
import com.dleibovych.soroban.numpad.di.NumpadModule
import dagger.Component

@ActionScope @Component(modules = arrayOf(NumpadModule::class, ActionModule::class))
interface ActionComponent {
    fun inject(view: NumpadView)
    fun inject(activity: ActionActivity)
}