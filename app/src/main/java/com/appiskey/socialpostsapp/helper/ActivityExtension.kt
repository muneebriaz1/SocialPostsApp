package com.appiskey.socialpostsapp.helper

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyBoard(view: View? = null) {
    var passedView = view
    if(passedView == null){
        passedView = currentFocus
    }
    if (passedView != null) {
        val imm = getSystemService(
            Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(passedView.windowToken, 0)
    }
}