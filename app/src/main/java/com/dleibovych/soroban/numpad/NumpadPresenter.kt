package com.dleibovych.soroban.numpad

class NumpadPresenter {

    var value = 0
    var inputEnabled = true

    fun apply(): Int {
        return value
    }

    fun discard(): Int {
        value = 0
        return value
    }

    fun append(param: Int): Int {
        if (inputEnabled) {
            value = value * 10 + param
        }
        return value
    }
}