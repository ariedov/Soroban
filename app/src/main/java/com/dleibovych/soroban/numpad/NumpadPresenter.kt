package com.dleibovych.soroban.numpad

class NumpadPresenter {

    var value: Int = 0

    fun apply(): Int {
        return value
    }

    fun discard(): Int {
        value = 0
        return value
    }

    fun append(param: Int): Int {
        value = value * 10 + param
        return value
    }

}