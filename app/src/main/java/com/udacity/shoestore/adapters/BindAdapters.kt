package com.udacity.shoestore.adapters

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

@BindingAdapter("android:text")
fun setDoubleInEditText(editText: EditText, double: Double) {
    editText.setText(double.toString())
}

@InverseBindingAdapter(attribute = "android:text")
fun getDoubleFromEditText(editText: EditText): Double? {
    return try {
        editText.text.toString().toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }
}