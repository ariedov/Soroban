package com.dleibovych.soroban.numpad

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.dleibovych.soroban.R
import com.dleibovych.soroban.action.ActionActivity
import kotlinx.android.synthetic.main.numpad_view.view.*
import javax.inject.Inject

class NumpadView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    @Inject lateinit var presenter: NumpadPresenter

    lateinit var valueListener: (Int) -> Unit
    lateinit var submitListener: () -> Unit
    lateinit var cancelListener: () -> Unit

    init {
        LayoutInflater.from(context).inflate(R.layout.numpad_view, this, true)

        if (context is ActionActivity) {
            context.component.inject(this)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        one.setOnClickListener({ presenter.append(1); valueListener(presenter.value) })
        two.setOnClickListener({ presenter.append(2); valueListener(presenter.value) })
        three.setOnClickListener({ presenter.append(3); valueListener(presenter.value) })
        four.setOnClickListener({ presenter.append(4); valueListener(presenter.value) })
        five.setOnClickListener({ presenter.append(5); valueListener(presenter.value) })
        six.setOnClickListener({ presenter.append(6); valueListener(presenter.value) })
        seven.setOnClickListener({ presenter.append(7); valueListener(presenter.value) })
        eight.setOnClickListener({ presenter.append(8); valueListener(presenter.value) })
        nine.setOnClickListener({ presenter.append(9); valueListener(presenter.value) })
        discard.setOnClickListener({ presenter.discard(); valueListener(presenter.value); cancelListener() })
        apply.setOnClickListener({ presenter.apply(); valueListener(presenter.value); submitListener() })
    }
}