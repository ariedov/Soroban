package com.dleibovych.soroban.preference

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dleibovych.soroban.R
import com.dleibovych.soroban.preference.di.DaggerPreferenceComponent
import com.dleibovych.soroban.preference.di.PreferenceComponent
import kotlinx.android.synthetic.main.preference_activity.*
import javax.inject.Inject

class PreferenceActivity: AppCompatActivity() {

    @Inject lateinit var presenter: PreferencePresenter

    val component: PreferenceComponent = DaggerPreferenceComponent.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preference_activity)

        component.inject(this)

        start.setOnClickListener {
            val digitsId = digits.checkedRadioButtonId
            val difficulty = when (digitsId) {
                R.id.digitOne -> 1
                R.id.digitTwo -> 2
                R.id.digitThree -> 3
                R.id.digitFour -> 4
                else -> throw IllegalStateException("Unknown difficulty")
            }

            val operationsId = operations.checkedRadioButtonId
            val operations = when (operationsId) {
                R.id.operationTwo -> 2
                R.id.operationThree -> 3
                R.id.operationFour -> 4
                R.id.operationFive -> 5
                R.id.operationTen -> 10
                else -> throw IllegalStateException("Unknown operation count")
            }

            presenter.moveToAction(this, difficulty, operations)
        }
    }
}