package com.dleibovych.soroban.preference

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dleibovych.soroban.R
import com.dleibovych.soroban.action.ActionActivity
import com.dleibovych.soroban.data.Sign
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
            val id = digits.checkedRadioButtonId
            val difficulty = when (id) {
                R.id.one -> 1
                R.id.two -> 2
                R.id.three -> 3
                R.id.four -> 4
                R.id.five -> 5
                else -> throw IllegalStateException("Unknown difficulty ${id}")
            }

            val signs = ArrayList<Sign>()
            if (plus.isChecked) signs.add(Sign.Plus)
            if (minus.isChecked) signs.add(Sign.Minus)
            if (multiply.isChecked) signs.add(Sign.Multiply)
            if (divide.isChecked) signs.add(Sign.Divide)

            presenter.moveToAction(this, difficulty, signs)
        }
    }
}