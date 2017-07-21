package com.dleibovych.soroban.action.countdown

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.dleibovych.soroban.R
import com.dleibovych.soroban.action.ActionScope
import com.dleibovych.soroban.action.ActionView
import kotlinx.android.synthetic.main.countdown_view.view.*
import javax.inject.Inject

@ActionScope class CountdownView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), ICountdownView {

    @Inject lateinit var presenter: CountdownPresenter
    @Inject lateinit var actionView: ActionView

    init {
        LayoutInflater.from(context).inflate(R.layout.countdown_view, this)
    }

    override fun updateCountdown(cd: String) {
        countdownValue.text = cd
    }

    override fun finish() {
        actionView.startGame()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        presenter.startCountdown()
    }
}