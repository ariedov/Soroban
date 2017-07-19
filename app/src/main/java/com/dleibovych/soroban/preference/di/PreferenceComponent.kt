package com.dleibovych.soroban.preference.di

import com.dleibovych.soroban.preference.PreferenceActivity
import com.dleibovych.soroban.preference.PreferenceScope
import dagger.Component

@PreferenceScope @Component(modules = arrayOf(PreferenceModule::class))
interface PreferenceComponent {
    fun inject(activity: PreferenceActivity)
}