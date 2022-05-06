package com.example.gameplatform.ui.rank

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RankViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is rank Fragment"
    }
    val text: LiveData<String> = _text
}