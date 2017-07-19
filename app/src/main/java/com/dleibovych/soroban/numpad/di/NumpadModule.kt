package com.dleibovych.soroban.numpad.di

import com.dleibovych.soroban.action.ActionScope
import com.dleibovych.soroban.numpad.NumpadPresenter
import dagger.Module
import dagger.Provides

@Module class NumpadModule {

    @Provides @ActionScope fun numpadPresenter(): NumpadPresenter {
        return NumpadPresenter()
    }
}