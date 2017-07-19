package com.dleibovych.soroban.preference.di

import com.dleibovych.soroban.preference.PreferencePresenter
import com.dleibovych.soroban.preference.PreferenceScope
import dagger.Module
import dagger.Provides

@Module
class PreferenceModule {

    @Provides @PreferenceScope fun preferencePresenter(): PreferencePresenter {
        return PreferencePresenter()
    }
}