package com.dleibovych.soroban.action

import android.os.Handler
import com.dleibovych.soroban.data.Sign
import java.util.*

@ActionScope class ActionPresenter(val view: ActionView) {

    var delay = 1000L
    var inputValue: Int? = null
    var result: Int? = null
    var inputEnabled = true

    fun valueUpdated(value: Int) {
        if (result == null) {
            throw IllegalStateException("The equation is not ready yet. Run updateUiWithData")
        }

        if (inputEnabled) {
            inputValue = value
            view.updateDisplay(value.toString())
        }
    }

    fun apply() {
        if (inputValue == result) {
            view.showSuccess()
        } else {
            view.showError()
        }
    }

    fun discard() {
        inputValue = null
    }

    fun updateUiWithData(difficulty: Int, signs: ArrayList<Sign>) {
        val rand = Random()
        val (first, second) = when (difficulty) {
            1 -> Pair(1 + rand.nextInt(9), 1 + rand.nextInt(9))
            2 -> Pair(10 + rand.nextInt(90), 10 + rand.nextInt(90))
            3 -> Pair(100 + rand.nextInt(900), 100 + rand.nextInt(900))
            4 -> Pair(1000 + rand.nextInt(9000), 1000 + rand.nextInt(9000))
            5 -> Pair(10_000 + rand.nextInt(90_000), 10_000 + rand.nextInt(90_000))
            else -> throw IllegalStateException("Unexpected difficulty: ${difficulty}")
        }

        val sign = signs[rand.nextInt(signs.size)]
        result = sign.perform(first, second)

        val display = arrayOf(first.toString(), sign.getDisplay(view.getContext()), second.toString(), "" /* clean the screen after job is done */)

        inputEnabled = false
        var currentId = 0
        view.updateDisplay(display[currentId++])
        val handler = Handler()
        handler.postDelayed(object: Runnable {
            override fun run() {
                view.updateDisplay(display[currentId++])
                if (currentId < display.size) {
                    handler.postDelayed(this, delay)
                } else {
                    inputEnabled = true
                }
            }
        }, delay)
    }
}