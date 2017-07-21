package com.dleibovych.soroban.action.countdown.di

import com.dleibovych.soroban.action.ActionScope
import com.dleibovych.soroban.action.countdown.CountdownPresenter
import com.dleibovych.soroban.action.countdown.CountdownProvider
import com.dleibovych.soroban.action.countdown.ICountdownView
import dagger.Module
import dagger.Provides

@Module class CountdownModule(val countdownView: ICountdownView) {

    @Provides @ActionScope fun provideView(): ICountdownView {
        return countdownView
    }

    @Provides @ActionScope fun provideDataProvider(): CountdownProvider {
        return CountdownProvider()
    }

    @Provides @ActionScope fun providePresenter(view: ICountdownView, provider: CountdownProvider): CountdownPresenter {
        return CountdownPresenter(view, provider)
    }
}