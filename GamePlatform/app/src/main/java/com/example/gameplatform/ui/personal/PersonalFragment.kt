package com.example.gameplatform.ui.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gameplatform.databinding.FragmentPersonalBinding
import com.example.gameplatform.ui.explore.ExploreViewModel

class PersonalFragment : Fragment() {

    private var _binding: FragmentPersonalBinding? = null

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

        val textView: TextView = binding.textPersonal
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}