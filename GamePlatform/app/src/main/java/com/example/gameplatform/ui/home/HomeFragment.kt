package com.example.gameplatform.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.gameplatform.SpecificActivity
import com.example.gameplatform.databinding.FragmentHomeBinding
import kotlinx.coroutines.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var gameList: ArrayList<CardView>
    private lateinit var coverList: ArrayList<ImageView>
    private lateinit var typeList: ArrayList<TextView>
    private lateinit var nameList: ArrayList<TextView>
    private lateinit var disList: ArrayList<TextView>
    private lateinit var scoreList: ArrayList<TextView>
    private lateinit var resultList: ArrayList<JSONObject>


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
        coverList = ArrayList()
        typeList = ArrayList()
        nameList = ArrayList()
        disList = ArrayList()
        scoreList = ArrayList()
        resultList = ArrayList()

        val game_1 = binding.cardGame1
        val game_2 = binding.cardGame2
        val game_3 = binding.cardGame3
        val game_4 = binding.cardGame4
        gameList.add(game_1)
        gameList.add(game_2)
        gameList.add(game_3)
        gameList.add(game_4)

        val cover_1 = binding.cover1
        val cover_2 = binding.cover2
        val cover_3 = binding.cover3
        val cover_4 = binding.cover4
        coverList.add(cover_1)
        coverList.add(cover_2)
        coverList.add(cover_3)
        coverList.add(cover_4)

        val type_1 = binding.gameType1
        val type_2 = binding.gameType2
        val type_3 = binding.gameType3
        val type_4 = binding.gameType4
        typeList.add(type_1)
        typeList.add(type_2)
        typeList.add(type_3)
        typeList.add(type_4)

        val name_1 = binding.gameName1
        val name_2 = binding.gameName2
        val name_3 = binding.gameName3
        val name_4 = binding.gameName4
        nameList.add(name_1)
        nameList.add(name_2)
        nameList.add(name_3)
        nameList.add(name_4)

        val dis_1 = binding.gameDisc1
        val dis_2 = binding.gameDisc2
        val dis_3 = binding.gameDisc3
        val dis_4 = binding.gameDisc4
        disList.add(dis_1)
        disList.add(dis_2)
        disList.add(dis_3)
        disList.add(dis_4)

        val score_1 = binding.score1
        val score_2 = binding.score2
        val score_3 = binding.score3
        val score_4 = binding.score4
        scoreList.add(score_1)
        scoreList.add(score_2)
        scoreList.add(score_3)
        scoreList.add(score_4)

        return root
    }

    override fun onStart() {
        super.onStart()
        //获取游戏信息
        context?.let { getGame(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getGame(context: Context) {
        val baseUrl = "http://106.15.6.161/"
        //创建请求对象
        val request = Request.Builder()
            .url(baseUrl + "allGame")
            .get()
            .build()

        OkHttpClient().newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("错误", e.stackTraceToString())
                }

                override fun onResponse(call: Call, response: Response) {
                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            val result = response.body?.string().toString()
                            val formResult = JSONObject(result)
                            for (i in 0..3) {
                                val child = JSONObject(formResult[i.toString()].toString())
                                resultList.add(child)
                            }
                        }
                        try {
                            for (i in 0..3) {
                                val child = resultList[i]
                                Glide.with(context)
                                    .load(baseUrl + "getImage/" + child["cover"].toString())
                                    .into(coverList[i])
                                typeList[i].text = child["type"].toString()
                                disList[i].text = child["describe"].toString()
                                scoreList[i].text = child["rating"].toString()
                                nameList[i].text = child["name"].toString()
                                gameList[i].setOnClickListener {
                                    //传递游戏名字参数并跳转
                                    val name = child["name"].toString()
                                    val intent = Intent(activity, SpecificActivity::class.java)
                                    intent.putExtra("gameName", name)
                                    Log.e("名字", name)
                                    startActivity(intent)
                                }
                            }
                        } catch (e: Exception) {
                            Log.e("错误", e.stackTraceToString())
                        }
                    }
                }
            })
    }
}

