package com.dleibovych.soroban.action

import android.os.Handler
import android.os.Message

@ActionScope class ActionDisplay(val delay: Long) {

    fun processSequence(sequence: ArrayList<Int>, listener: ActionDisplayListener) {
        TimerHandler(sequence, delay, listener).sendEmptyMessage(0)
    }

    class TimerHandler(val sequence: ArrayList<Int>, val delay: Long, val listener: ActionDisplayListener): Handler() {

        override fun handleMessage(msg: Message?) {
            if (msg!!.what < sequence.size) {
                val item = sequence[msg.what]
                val sign = if (item > 0) "+" else "-"
                listener.processItem("$sign ${Math.abs(item)}")
            } else {
                listener.finish()
            }

            if (msg.what < sequence.size) {
                sendEmptyMessageDelayed(msg.what + 1, delay)
            }
        }
    }

    interface ActionDisplayListener {
        fun processItem(item: String)
        fun finish()
    }
}