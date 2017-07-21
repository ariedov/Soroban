package com.dleibovych.soroban.action.countdown

import android.os.Handler
import android.os.Message
import com.dleibovych.soroban.action.ActionScope
import javax.inject.Inject

private const val CountdownDelay = 1000L
private const val TimerSize = 3

@ActionScope class CountdownProvider {

    fun startCountdown(listener: CountdownProviderListener) {
        TimerHandler(listener).sendEmptyMessage(TimerSize)
    }

    class TimerHandler(val listener: CountdownProviderListener): Handler() {

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)

            if (msg!!.what > 0) {
                listener.onCountdown(msg.what)
                val newId = msg.what - 1
                sendEmptyMessageDelayed(newId, CountdownDelay)
            } else {
                listener.finish()
            }
        }
    }
}

interface CountdownProviderListener {

    fun onCountdown(item: Int)
    fun finish()
}