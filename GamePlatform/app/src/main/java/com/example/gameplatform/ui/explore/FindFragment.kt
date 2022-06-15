package com.example.gameplatform.ui.explore

import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.gameplatform.R
import androidx.recyclerview.widget.RecyclerView
import com.example.gameplatform.ui.explore.FindFragment.Adapter1
import com.example.gameplatform.ui.explore.FindFragment.Adapter2
import com.example.gameplatform.ui.explore.FindFragment.VH1

class FindFragment : Fragment() {
    var layoutManager = LinearLayoutManager(activity)
    var layoutManager2 = LinearLayoutManager(activity)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_explore, container, false)
        val mRv1 = view.findViewById<RecyclerView>(R.id.mRv1)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        mRv1.layoutManager = layoutManager
        mRv1.adapter = Adapter1()
        val mRv2 = view.findViewById<RecyclerView>(R.id.mRv2)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL
        mRv2.layoutManager = layoutManager2
        mRv2.adapter = Adapter2()
        return view
    }

    internal inner class Adapter1 : RecyclerView.Adapter<VH1>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH1 {
            val view = LayoutInflater.from(activity).inflate(R.layout.item1, parent, false)
            return VH1(view)
        }

        override fun onBindViewHolder(holder: VH1, position: Int) {}
        override fun getItemCount(): Int {
            return 3
        }
    }

    internal inner class Adapter2 : RecyclerView.Adapter<VH1>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH1 {
            val view = LayoutInflater.from(activity).inflate(R.layout.item2, parent, false)
            return VH1(view)
        }

        override fun onBindViewHolder(holder: VH1, position: Int) {}
        override fun getItemCount(): Int {
            return 3
        }
    }

    internal inner class VH1(itemView: View) : RecyclerView.ViewHolder(itemView)
}