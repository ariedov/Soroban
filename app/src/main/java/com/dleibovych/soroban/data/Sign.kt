package com.dleibovych.soroban.data

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import com.dleibovych.soroban.R
import kotlinx.android.synthetic.main.preference_activity.*

enum class Sign : Parcelable {

    Plus, Minus, Multiply, Divide;

    fun perform(a: Int, b: Int): Int {
        return when (this) {
            Plus -> a + b
            Minus -> a - b
            Multiply -> a * b
            Divide -> a / b
        }
    }

    fun getDisplay(context: Context): String {
        return when (this) {
            Plus -> context.getString(R.string.plus)
            Minus -> context.getString(R.string.minus)
            Multiply -> context.getString(R.string.multiply)
            Divide -> context.getString(R.string.divide)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ordinal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sign> {
        override fun createFromParcel(parcel: Parcel): Sign {
            return Sign.values()[parcel.readInt()]
        }

        override fun newArray(size: Int): Array<Sign?> {
            return arrayOfNulls(size)
        }
    }
}