package com.rktuhinbd.splitxpens.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExpensesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Expenses Fragment"
    }
    val text: LiveData<String> = _text
}