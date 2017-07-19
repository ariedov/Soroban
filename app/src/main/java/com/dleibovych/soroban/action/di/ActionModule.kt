package com.dleibovych.soroban.action.di

import com.dleibovych.soroban.action.ActionPresenter
import com.dleibovych.soroban.action.ActionScope
import com.dleibovych.soroban.action.ActionView
import com.dleibovych.soroban.data.Sign
import dagger.Module
import dagger.Provides

@Module
class ActionModule(val view: ActionView) {

    @Provides @ActionScope fun actionPresenter(): ActionPresenter {
        return ActionPresenter(view)
    }
}