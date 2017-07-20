package com.dleibovych.soroban.preference

import android.app.Activity
import com.dleibovych.soroban.action.ActionActivity

@PreferenceScope class PreferencePresenter {
    fun moveToAction(activity: Activity, difficulty: Int, operations: Int) {
        val intent = ActionActivity.getIntent(activity, difficulty, operations)
        activity.startActivity(intent)
    }
}