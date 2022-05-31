package com.example.gameplatform.ui.rank

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gameplatform.R
import com.example.gameplatform.databinding.FragmentRankBinding
import com.example.gameplatform.entities.Game
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class RankFragment : Fragment() {
    private var layoutManager = LinearLayoutManager(activity)
    private var games: ArrayList<Game> = ArrayList()


    private var _binding: FragmentRankBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


//        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        val client = OkHttpClient()
        val request: Request = Request.Builder().url("http://127.0.0.1:5000/games").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(e.toString(),"Fail")

            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                games.clear()
                var str = response.body.toString()
                println(str)
                var arr = JsonParser().parse(str).asJsonArray
                var gson = Gson()
                for (js in arr) {
                    var g = gson.fromJson<Game>(str, Game::class.java)
                    games.add(g)
                }
                println(games)
            }
        })

        _binding = FragmentRankBinding.inflate(inflater, container, false)
        val root: RecyclerView = binding.root


        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        root.layoutManager = layoutManager
        var adapter: ArrayAdapter<Game> =
            ArrayAdapter<Game>(this.requireContext(), android.R.layout.simple_list_item_1, games)
        root.adapter = Adapter(games)


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    internal inner class Adapter(games: ArrayList<Game>) : RecyclerView.Adapter<Adapter.VH>() {
        private var games: ArrayList<Game> = games

        internal inner class VH(cardView: CardView) : RecyclerView.ViewHolder(cardView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.VH {
            val view = LayoutInflater.from(activity).inflate(R.layout.rank_card, parent, false)
            return VH(view as CardView)
        }

        override fun onBindViewHolder(holder: Adapter.VH, position: Int) {
        }

        override fun getItemCount(): Int {
            return games.size
        }
    }


}