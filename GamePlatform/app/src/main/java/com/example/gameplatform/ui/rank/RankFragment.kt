package com.example.gameplatform.ui.rank

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gameplatform.R
import com.example.gameplatform.SpecificActivity
import com.example.gameplatform.databinding.FragmentRankBinding
import com.example.gameplatform.databinding.RankCardBinding
import com.example.gameplatform.entities.Game
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type


class RankFragment : Fragment() {


    private var _binding: FragmentRankBinding? = null
    private final var handler: Handler = Handler(Looper.myLooper()!!)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRankBinding.inflate(inflater, container, false)
        val root: RecyclerView = binding.root

        var layoutManager = LinearLayoutManager(activity)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        root.layoutManager = layoutManager
        root.adapter = Adapter(DiffUtilGameCallBack())


        val client = OkHttpClient()
        val request: Request =
            Request.Builder().url("http://106.15.6.161/games").get().build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Fail", e.stackTraceToString())
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val gson = Gson()
                val listType: Type = object : TypeToken<List<Game?>?>() {}.type
                val t: List<Game> = gson.fromJson(response.body?.string(), listType)
                Log.d("Games", t.toString())
                handler.post {
                    (root.adapter as Adapter).submitList(t)
                }
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    internal inner class Adapter(@NonNull diffCallback: DiffUtil.ItemCallback<Game>) :
        ListAdapter<Game, Adapter.VH>(diffCallback) {

        internal inner class VH(@NonNull itemView: RankCardBinding) :
            RecyclerView.ViewHolder(itemView.root) {
            var rankCardBinding: RankCardBinding = itemView

            public fun bindData(game: Game) {
                rankCardBinding.game = game
                Glide.with(requireActivity())
                    .load(game.icon)
                    .into(rankCardBinding.imageView)
                rankCardBinding.root.setOnClickListener{
                    val name=game.name
                    val intent: Intent = Intent(activity, SpecificActivity::class.java)
                    intent.putExtra("gameName",name)
                    Log.e("名字", name)
                    startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.VH {
            var binding = DataBindingUtil.inflate<RankCardBinding>(
                LayoutInflater.from(parent.context),
                R.layout.rank_card,
                parent,
                false
            )
            return VH(binding)
        }

        override fun onBindViewHolder(holder: Adapter.VH, position: Int) {
            var t = getItem(position)
            t.index = (position + 1).toString()
            t.icon = "http://106.15.6.161/getImage/" + t.icon
            holder.bindData(t)
        }
    }

    internal inner class DiffUtilGameCallBack : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.name == newItem.name
        }
    }
}