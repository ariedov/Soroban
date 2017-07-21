package com.dleibovych.soroban.preference

import android.app.Activity
import android.content.Context
import com.dleibovych.soroban.R
import com.dleibovych.soroban.action.ActionActivity

@PreferenceScope class PreferencePresenter(val view: PreferenceView) {

    fun applyDelay(context: Context, level: Int) {
        val delay = getDelayFromLevel(level)
        view.displayProgress("${(delay.toFloat() / 1000)} ${context.getString(R.string.seconds)}")
    }

    fun getDelayFromLevel(level: Int): Int = when (level) {
            0 -> 0
            1 -> 100
            2 -> 300
            3 -> 500
            4 -> 800
            5 -> 1000
            6 -> 1100
            7 -> 1300
            8 -> 1500
            9 -> 1800
            10 -> 2000
            else -> throw IllegalArgumentException("Unknown level: $level")
        }

    fun moveToAction(activity: Activity, difficulty: Int, operations: Int, delay: Long) {
        val intent = ActionActivity.getIntent(activity, difficulty, operations, delay)
        activity.startActivity(intent)
    }
}