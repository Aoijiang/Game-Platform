package com.example.gameplatform.ui.personal

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.gameplatform.SpecificActivity
import com.example.gameplatform.databinding.FragmentPersonalBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

class PersonalFragment : Fragment() {

    private var _binding: FragmentPersonalBinding? = null
    private lateinit var gameList: ArrayList<LinearLayout>
    private lateinit var nameList: ArrayList<TextView>
    private lateinit var iconList: ArrayList<ImageView>
    private lateinit var resultList: ArrayList<JSONObject>
    private lateinit var btList: ArrayList<Button>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(PersonalViewModel::class.java)

        _binding = FragmentPersonalBinding.inflate(inflater, container, false)
        val root: View = binding.root
        gameList = ArrayList()
        nameList = ArrayList()
        iconList = ArrayList()
        resultList = ArrayList()
        btList= ArrayList()
        val packageManager = context?.packageManager

        val game_1 = binding.game1
        val game_2 = binding.game2
        val game_3 = binding.game3
        val game_4 = binding.game4
        gameList.add(game_1)
        gameList.add(game_2)
        gameList.add(game_3)
        gameList.add(game_4)

        val name_1 = binding.name1
        val name_2 = binding.name2
        val name_3 = binding.name3
        val name_4 = binding.name4
        nameList.add(name_1)
        nameList.add(name_2)
        nameList.add(name_3)
        nameList.add(name_4)

        val icon_1 = binding.gameImage1
        val icon_2 = binding.gameImage2
        val icon_3 = binding.gameImage3
        val icon_4 = binding.gameImage4
        iconList.add(icon_1)
        iconList.add(icon_2)
        iconList.add(icon_3)
        iconList.add(icon_4)

        val bt1 = binding.bt1
        val bt2 = binding.bt2
        val bt3 = binding.bt3
        val bt4 = binding.bt4
        btList.add(bt1)
        btList.add(bt2)
        btList.add(bt3)
        btList.add(bt4)

        return root
    }

    override fun onStart() {
        super.onStart()
        context?.let { getGame(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openTartgetApp(packagename:String){
        val pm:PackageManager;
    }
    private fun getGame(context: Context) {
        val baseUrl = "http://106.15.6.161/"
        //??????????????????
        val request = Request.Builder()
            .url(baseUrl + "allGame")
            .get()
            .build()

        OkHttpClient().newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("??????", e.stackTraceToString())
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
                                    .load(baseUrl + "getImage/" + child["icon"].toString())
                                    .into(iconList[i])
                                nameList[i].text = child["name"].toString()
                                gameList[i].setOnClickListener {
                                    //?????????????????????????????????
                                    val name = child["name"].toString()
                                    val intent = Intent(activity, SpecificActivity::class.java)
                                    intent.putExtra("gameName", name)
                                    Log.e("??????", name)
                                    startActivity(intent)
                                }
                                btList[i].setOnClickListener{
                                    val packageName=child["packagename"].toString()
                                    Log.e("??????",packageName)
                                    try{
                                        val intent = context.packageManager.getLaunchIntentForPackage(packageName)!!;
                                        startActivity(intent)
                                    }catch (e:Exception) {
                                        Toast.makeText(context, "?????????????????????", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                        } catch (e: Exception) {
                            Log.e("??????", e.stackTraceToString())
                        }
                    }
                }
            })
    }
}