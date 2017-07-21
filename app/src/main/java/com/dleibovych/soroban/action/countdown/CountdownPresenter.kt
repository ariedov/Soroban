package com.dleibovych.soroban.action.countdown

import com.dleibovych.soroban.action.ActionScope
import javax.inject.Inject

@ActionScope class CountdownPresenter @Inject constructor(val view: ICountdownView, val dataProvider: CountdownProvider) {

    fun startCountdown() {
        dataProvider.startCountdown(object: CountdownProviderListener {
            override fun onCountdown(item: Int) {
                view.updateCountdown("$item!")
            }

            override fun finish() {
                view.finish()
            }
        })
    }
}