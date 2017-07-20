package com.dleibovych.soroban.action

import java.util.*

@ActionScope class ActionPresenter(val view: ActionView, val display: ActionDisplay) {

    var inputValue: Int? = null
    var result: Int = 0
    var inputEnabled = true

    fun valueUpdated(value: Int) {
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
        inputEnabled = false
        view.disableInput()
    }

    fun discard() {
        if (inputEnabled) {
            inputValue = null
        }
    }

    fun updateUiWithData(difficulty: Int, operations: Int) {

        val sequence = generateSequence(difficulty, operations)

        inputEnabled = false
        view.disableInput()
        display.processSequence(sequence, listener = object: ActionDisplay.ActionDisplayListener {
            override fun processItem(item: String) {
                view.updateDisplay(item)
            }

            override fun finish() {
                view.updateDisplay("")
                inputEnabled = true
                view.enableInput()
            }
        })
    }

    fun generateSequence(difficulty: Int, operations: Int): ArrayList<Int> {
        val rand = Random()
        result = 0
        val sequence = ArrayList<Int>()
        for (i in 1 .. operations) {
            val item = randomNumber(rand, difficulty, i % 2 != 0)
            result = result + item
            sequence.add(item)
        }
        return sequence
    }

    fun randomNumber(rand: Random, difficulty: Int, positive: Boolean): Int {
        val r = result
        val value = when (difficulty) {
            1 -> 1 + rand.nextInt(if (positive) 9 else r)
            2 -> 10 + rand.nextInt(if (positive) 90 else r)
            3 -> 100 + rand.nextInt(if (positive) 900 else r)
            4 -> 1000 + rand.nextInt(if (positive) 9000 else r)
            else -> throw IllegalStateException("Unexpected difficulty: ${difficulty}")
        }
        return value * if (positive) 1 else -1
    }
}