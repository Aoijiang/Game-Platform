package com.example.gameplatform

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.gameplatform.databinding.ActivitySpecificBinding

class SpecificActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpecificBinding
    private lateinit var buttonlist:ArrayList<Button>

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)

        binding= ActivitySpecificBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //获取其他页面传来的参数（游戏名字），用于数据库查询
        val gameName=intent.getStringExtra("gameName")

        //四个按钮，控制状态
        var button1:Button = binding.detailbutton
        var button2:Button = binding.evaluatebutton
        var horizontalview:HorizontalScrollView = binding.horizontalscrollview1
        var detaillayout:LinearLayout=binding.detaillayout
        var evaluationlayout:LinearLayout = binding.evaluationlayout


        button2.setOnClickListener{
            button2.setTextColor(R.color.black)
            button1.setTextColor(R.color.gray)
            horizontalview.visibility=(View.GONE)
            evaluationlayout.visibility=(View.VISIBLE)
            //detaillayout.visibility=(View.GONE)
        }

        button1.setOnClickListener{
            button2.setTextColor(R.color.gray)
            button1.setTextColor(R.color.black)
            horizontalview.visibility=(View.VISIBLE)
            evaluationlayout.visibility=(View.GONE)
            //detaillayout.visibility=(View.VISIBLE)
        }

        when(gameName){
            "异度神剑2" ->{
                binding.TextGameType.setText("不知道呢")
                binding.SpecificImageView1.setImageResource(R.drawable.game_1)
            }
            "Zelda" -> {
                binding.TextGameType.setText("RPG")
                binding.SpecificImageView1.setImageResource(R.drawable.game_2)
            }
            "原神" -> {
                binding.TextGameType.setText("骗钱")
                binding.SpecificImageView1.setImageResource(R.drawable.game_3)
            }
            "Ori" ->{
                binding.TextGameType.setText("横板跳跃")
                binding.SpecificImageView1.setImageResource(R.drawable.game_4)
            }
            else -> {
                print("出错了")
            }
        }

    }
}