package com.dleibovych.soroban.action

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.dleibovych.soroban.R
import com.dleibovych.soroban.action.di.ActionComponent
import com.dleibovych.soroban.action.di.ActionModule
import com.dleibovych.soroban.action.di.DaggerActionComponent
import kotlinx.android.synthetic.main.action_activity.*
import javax.inject.Inject

@ActionScope
class ActionActivity: AppCompatActivity(), ActionView {

    @Inject lateinit var presenter: ActionPresenter
    var component: ActionComponent = DaggerActionComponent
            .builder()
            .actionModule(ActionModule(this, ActionDisplay()))
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.action_activity)

        component.inject(this)

        numpad.valueListener = { presenter.valueUpdated(it) }
        numpad.submitListener = { presenter.apply() }
        numpad.cancelListener = { presenter.discard() }

        val difficulty = intent.getIntExtra(keyDifficulty, 1)
        val operations = intent.getIntExtra(keyOperations, 2)
        presenter.updateUiWithData(difficulty, operations)
    }

    override fun showSuccess() {
        display.setTextColor(ContextCompat.getColor(this, R.color.green))
        display.setText(R.string.correct)
    }

    override fun showError() {
        display.setTextColor(ContextCompat.getColor(this, R.color.red))
        display.setText(R.string.wrong)
    }

    override fun enableInput() {
        numpad.enableInput()
    }

    override fun disableInput() {
        numpad.disableInput()
    }

    override fun getContext(): Context {
        return this
    }

    override fun updateDisplay(value: String) {
        display.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        display.text = value
    }

    companion object {
        private val keyDifficulty = "Difficulty"
        private val keyOperations = "Operations"

        fun getIntent(context: Context, difficulty: Int, operations: Int): Intent {
            val intent = Intent(context, ActionActivity::class.java)
            intent.putExtra(keyDifficulty, difficulty)
            intent.putExtra(keyOperations, operations)
            return intent
        }
    }
}