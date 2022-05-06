package com.example.gameplatform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gameplatform.databinding.ActivitySpecificBinding

class SpecificActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpecificBinding

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)

        binding= ActivitySpecificBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val text=binding.textView
        text.text="游戏主页"
    }
}