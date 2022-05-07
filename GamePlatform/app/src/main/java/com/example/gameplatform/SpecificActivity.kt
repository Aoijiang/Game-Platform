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

        //获取其他页面传来的参数（游戏名字），用于数据库查询
        val gameName=intent.getStringExtra("gameName")
        val text=binding.textView
        text.text=gameName
    }
}