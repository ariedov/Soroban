package com.dleibovych.soroban.preference

import android.app.Activity
import com.dleibovych.soroban.action.ActionActivity
import com.dleibovych.soroban.data.Sign

@PreferenceScope class PreferencePresenter {
    fun moveToAction(activity: Activity, difficulty: Int, signs: ArrayList<Sign>) {
        val intent = ActionActivity.getIntent(activity, difficulty, signs)
        activity.startActivity(intent)
    }
}