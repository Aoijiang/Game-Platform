package com.example.gameplatform

import android.content.Context
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.gameplatform.databinding.ActivitySpecificBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

class SpecificActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpecificBinding
    private lateinit var buttonlist: ArrayList<Button>
    // 存储返回的json结果
    private lateinit var jsonresult: ArrayList<JSONObject>
    private lateinit var jsoncomment:ArrayList<JSONObject>
    // 存储四张图片
    private lateinit var coverList: ArrayList<ImageView>
    // 存储textview
    private lateinit var textList: ArrayList<TextView>
    // 存储图片
    private lateinit var imageList:ArrayList<ImageView>
    // 存储comment
    private lateinit var commentList:ArrayList<TextView>


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySpecificBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonlist = ArrayList()
        textList = ArrayList()
        jsonresult = ArrayList()
        jsoncomment = ArrayList()
        imageList = ArrayList()
        commentList = ArrayList()
        // button 保存需要修改文字的button
        // 下载量
        val _button1 = binding.downloadbutton
        buttonlist.add(_button1)

        // 所有textview
        // 推荐类型
        val text0 = binding.TextGameTypeRecommend
        textList.add(text0)
        // 游戏类型
        val text1 = binding.TextGameType
        textList.add(text1)
        // 下载数量
        val text2 = binding.thedownloadnum
        textList.add(text2)
        // 关注数
        val text3 = binding.theconcernnum
        textList.add(text3)
        // 评分
        val text4 = binding.gameScore
        textList.add(text4)
        // 简介内容
        val text5 = binding.briefContent
        textList.add(text5)

        // 所有评论相关
        val username1 = binding.userName1
        commentList.add(username1)
        val commenttime1 = binding.comment1Time
        commentList.add(commenttime1)
        val commentcontent1 = binding.comment1Content
        commentList.add(commentcontent1)

        val username2 = binding.userName2
        commentList.add(username2)
        val commenttime2 = binding.comment2Time
        commentList.add(commenttime2)
        val commentcontent2 = binding.comment2Content
        commentList.add(commentcontent2)

        // 所以ImageView
        val image0 = binding.SpecificImageView1
        imageList.add(image0)
        val image1 = binding.ImageDetail1
        val image2 = binding.ImageDetail2
        val image3 = binding.ImageDetail3
        imageList.add(image1)
        imageList.add(image2)
        imageList.add(image3)
        val headimage1 = binding.userHeadportait1
        val headimage2 = binding.userHeadportait2
        imageList.add(headimage1)
        imageList.add(headimage2)

        //获取其他页面传来的参数（游戏名字），用于数据库查询
        val gameName:String = intent.getStringExtra("gameName").toString()
        getRequest(gameName);
        Log.e("这个会输出在哪",gameName)
        //四个按钮，控制状态
        val button1: Button = binding.detailbutton
        val button2: Button = binding.evaluatebutton
        val horizontalview: HorizontalScrollView = binding.horizontalscrollview1
        var detaillayout: LinearLayout = binding.detaillayout
        val evaluationlayout: LinearLayout = binding.evaluationlayout

        Log.e("json",jsonresult.toString())
//        textList[0].setText(jsonresult[0]["type"].toString())
//        text1test = binding.TextGameType
//        text1test.setText(jsonresult["type"].toString())

        button2.setOnClickListener {
            button2.setTextColor(R.color.black)
            button1.setTextColor(R.color.gray)
            horizontalview.visibility = (View.GONE)
            evaluationlayout.visibility = (View.VISIBLE)
            //detaillayout.visibility=(View.GONE)
        }

        button1.setOnClickListener {
            button2.setTextColor(R.color.gray)
            button1.setTextColor(R.color.black)
            horizontalview.visibility = (View.VISIBLE)
            evaluationlayout.visibility = (View.GONE)
            //detaillayout.visibility=(View.VISIBLE)
        }
    }

    override fun onStart() {
        super.onStart()

    }
    fun getRequest(gameName:String) {

        val url = "http://106.15.6.161/gamedetail/" + gameName.toString()
        val url_image = "http://106.15.6.161/getImage/"
//        val url = "http://192.168.2.104:5000/game/" + gameName.toString()
//        val url2 = "http://192.168.2.104:5000/getComment/" +gameName.toString()
        // 创建request请求对象
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("错误",e.stackTraceToString())
            }

            override fun onResponse(call: Call, response: Response) {
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO){
                        val result = response.body?.string().toString()
                        val result1 = JSONObject(result)
                        val gamedetail = JSONObject(result1["gamedetail"].toString())
                        jsonresult.add(gamedetail)

                        val formResult = JSONObject(result1["comment"].toString())
                        for (i in 0..1){
                            val comment = JSONObject(formResult[i.toString()].toString())
                            jsoncomment.add(comment)
                        }
                        Log.e("jsoncomment",jsoncomment.toString())
                        Log.e("jsonresult",jsonresult.toString())
                        Log.e("type",jsonresult[0]["type"].toString())
                    }
                    try{
                        buttonlist[0].setText("下载 "+ jsonresult[0]["downloadsize"].toString())

                        textList[0].setText(jsonresult[0]["recommend"].toString())
                        textList[1].setText(jsonresult[0]["type"].toString())
                        textList[2].setText(jsonresult[0]["downloadnum"].toString())
                        textList[3].setText(jsonresult[0]["concernnum"].toString())
                        textList[4].setText(jsonresult[0]["score"].toString())
                        textList[5].setText(jsonresult[0]["brief"].toString())

                        for (i in 0..1){
                            val comment = jsoncomment[i]
                            commentList[3*i].setText(comment["username"].toString())
                            commentList[3*i+1].setText(comment["commenttime"].toString())
                            commentList[3*i+2].setText(comment["content"].toString())

                            Glide.with(this@SpecificActivity)
                                .load(url_image+comment["usericon"].toString())
                                .into(imageList[4+i])
                        }

                        Glide.with(this@SpecificActivity)
                            .load(url_image+jsonresult[0]["gamemainimage"].toString())
                            .into(imageList[0])
                        Glide.with(this@SpecificActivity)
                            .load(url_image+jsonresult[0]["gamedetailimage1"].toString())
                            .into(imageList[1])
                        Glide.with(this@SpecificActivity)
                            .load(url_image+jsonresult[0]["gamedetailimage2"].toString())
                            .into(imageList[2])
                        Glide.with(this@SpecificActivity)
                            .load(url_image+jsonresult[0]["gamedetailimage3"].toString())
                            .into(imageList[3])
                    }catch (e:Exception){
                        Log.e("错误",e.stackTraceToString())
                    }
                }
            }
        })
    }
}