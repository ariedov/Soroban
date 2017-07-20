package com.dleibovych.soroban.action.di

import com.dleibovych.soroban.action.*
import dagger.Module
import dagger.Provides

@Module
class ActionModule(val view: ActionView, val display: ActionDisplay) {

    @Provides @ActionScope fun actionPresenter(): ActionPresenter {
        return ActionPresenter(view, display)
    }
}