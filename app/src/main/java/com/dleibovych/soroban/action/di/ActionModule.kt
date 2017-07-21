package com.dleibovych.soroban.action.di

import com.dleibovych.soroban.action.*
import dagger.Module
import dagger.Provides

@Module
class ActionModule(val view: ActionView, val display: ActionDisplay) {

    @Provides @ActionScope fun provideActionView(): ActionView {
        return view
    }

    @Provides @ActionScope fun provideActionDisplay(): ActionDisplay {
        return display
    }

    @Provides @ActionScope fun provideActionPresenter(view: ActionView, display: ActionDisplay): ActionPresenter {
        return ActionPresenter(view, display)
    }
}