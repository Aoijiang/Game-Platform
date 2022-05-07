package com.example.gameplatform.ui.personal

import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gameplatform.SpecificActivity
import com.example.gameplatform.databinding.FragmentPersonalBinding
import com.example.gameplatform.ui.explore.ExploreViewModel

class PersonalFragment : Fragment() {

    private var _binding: FragmentPersonalBinding? = null
    private lateinit var gameList:ArrayList<LinearLayout>
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
        gameList= ArrayList()


        val game_1=binding.game1
        val game_2=binding.game2
        val game_3=binding.game3
        val game_4=binding.game4
        gameList.add(game_1)
        gameList.add(game_2)
        gameList.add(game_3)
        gameList.add(game_4)

        for(i in 0..3)
            gameList[i].setOnClickListener{
                val name=gameList[i].tag
                startActivity(Intent(activity, SpecificActivity::class.java))
            }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}