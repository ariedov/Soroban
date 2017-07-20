package com.dleibovych.soroban.preference

import android.app.Activity
import android.content.Context
import com.dleibovych.soroban.R
import com.dleibovych.soroban.action.ActionActivity

@PreferenceScope class PreferencePresenter(val view: PreferenceView) {

    fun applyDelay(context: Context, delay: Int) {
        view.displayProgress("${(delay.toFloat() / 1000)} ${context.getString(R.string.seconds)}")
    }

    fun moveToAction(activity: Activity, difficulty: Int, operations: Int, delay: Long) {
        val intent = ActionActivity.getIntent(activity, difficulty, operations, delay)
        activity.startActivity(intent)
    }
}