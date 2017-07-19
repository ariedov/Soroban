package com.dleibovych.soroban.action

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dleibovych.soroban.R
import com.dleibovych.soroban.action.di.ActionComponent
import com.dleibovych.soroban.action.di.ActionModule
import com.dleibovych.soroban.action.di.DaggerActionComponent
import com.dleibovych.soroban.data.Sign
import kotlinx.android.synthetic.main.action_activity.*
import javax.inject.Inject

@ActionScope
class ActionActivity: AppCompatActivity(), ActionView {

    @Inject lateinit var presenter: ActionPresenter
    var component: ActionComponent = DaggerActionComponent
            .builder()
            .actionModule(ActionModule(this))
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.action_activity)

        component.inject(this)

        numpad.valueListener = { presenter.valueUpdated(it) }
        numpad.submitListener = { presenter.apply() }
        numpad.cancelListener = { presenter.discard() }

        val difficulty = intent.getIntExtra(keyDifficulty, 1)
        val signs = intent.getParcelableArrayListExtra<Sign>(keySigns)
        presenter.updateUiWithData(difficulty, signs)
    }

    override fun showSuccess() {
        display.setTextColor(R.color.green)
        display.setText(R.string.correct)
    }

    override fun showError() {
        display.setTextColor(R.color.red)
        display.setText(R.string.wrong)
    }

    override fun getContext(): Context {
        return this
    }

    override fun updateDisplay(value: String) {
        display.setTextColor(android.R.color.black)
        display.text = value
    }

    companion object {
        private val keyDifficulty = "Difficulty"
        private val keySigns = "Signs"

        fun getIntent(context: Context, difficulty: Int, signs: ArrayList<Sign>): Intent {
            val intent = Intent(context, ActionActivity::class.java)
            intent.putExtra(keyDifficulty, difficulty)
            intent.putParcelableArrayListExtra(keySigns, signs)
            return intent
        }
    }
}