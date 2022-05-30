package com.example.gameplatform.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gameplatform.SpecificActivity
import com.example.gameplatform.databinding.FragmentHomeBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var gameList: ArrayList<CardView>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        gameList = ArrayList()

        val game_1 = binding.cardGame1
        val game_2 = binding.cardGame2
        val game_3 = binding.cardGame3
        val game_4 = binding.cardGame4
        gameList.add(game_1)
        gameList.add(game_2)
        gameList.add(game_3)
        gameList.add(game_4)

        for (i in 0..3)
            gameList[i].setOnClickListener {
                //传递游戏名字参数并跳转
                val name = gameList[i].tag
                val intent: Intent = Intent(activity, SpecificActivity::class.java)
                intent.putExtra("gameName", name.toString())
                startActivity(intent)
            }

        return root
    }

    override fun onStart() {
        super.onStart()
        //获取游戏信息
        getGame()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getGame() {
        val url = "http://106.15.6.161/allGame"
        //创建请求对象
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        OkHttpClient().newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("错误", e.stackTraceToString())
                }

                override fun onResponse(call: Call, response: Response) {
                    val result = response.body?.string().toString()
                    val formResult = JSONObject(result)
                    for(i in 0..3)
                        Log.e(i.toString(),formResult[i.toString()].toString())
                }
            })
    }
}