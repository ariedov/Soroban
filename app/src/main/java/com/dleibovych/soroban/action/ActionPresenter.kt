package com.dleibovych.soroban.action

import java.util.*
import kotlin.collections.ArrayList

@ActionScope class ActionPresenter(val view: ActionView, val display: ActionDisplay) {

    var inputValue: Int? = null
    var result: Int = 0
    var inputEnabled = true

    fun updateValue(value: Int) {
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

        val (result, sequence) = generateSequence(difficulty, operations)
        this.result = result

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

    fun generateSequence(difficulty: Int, operations: Int): Pair<Int, ArrayList<Int>> {
        val rand = Random()
        val sequence = ArrayList<Int>()
        var result = 0
        for (i in 1 .. operations) {
            val item = randomNumber(rand, difficulty, i % 2 != 0, result)
            result += item
            sequence.add(item)
        }
        return Pair(result, sequence)
    }

    fun randomNumber(rand: Random, difficulty: Int, positive: Boolean, result: Int): Int {
        val value = when (difficulty) {
            1 -> 1 + rand.nextInt(if (positive) 9 else result)
            2 -> 10 + rand.nextInt(if (positive) 90 else result)
            3 -> 100 + rand.nextInt(if (positive) 900 else result)
            4 -> 1000 + rand.nextInt(if (positive) 9000 else result)
            else -> throw IllegalStateException("Unexpected difficulty: ${difficulty}")
        }
        return value * if (positive) 1 else -1
    }
}