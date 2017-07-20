package com.dleibovych.soroban.preference.di

import com.dleibovych.soroban.preference.PreferencePresenter
import com.dleibovych.soroban.preference.PreferenceScope
import com.dleibovych.soroban.preference.PreferenceView
import dagger.Module
import dagger.Provides

@Module
class PreferenceModule(val view: PreferenceView) {

    @Provides @PreferenceScope fun preferencePresenter(): PreferencePresenter {
        return PreferencePresenter(view)
    }
}