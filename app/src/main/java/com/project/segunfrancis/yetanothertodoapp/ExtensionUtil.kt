package com.project.segunfrancis.yetanothertodoapp

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by SegunFrancis
 */

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> {
    return this
}