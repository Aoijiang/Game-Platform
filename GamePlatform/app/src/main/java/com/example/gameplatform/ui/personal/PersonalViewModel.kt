package com.example.gameplatform.ui.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonalViewModel:ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Personal Fragment"
    }
    val text: LiveData<String> = _text
}