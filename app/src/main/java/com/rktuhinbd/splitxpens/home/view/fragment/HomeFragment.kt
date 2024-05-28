package com.rktuhinbd.splitxpens.home.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.GsonBuilder
import com.rktuhinbd.splitxpens.add_member.viewmodel.AddMemberViewModel
import com.rktuhinbd.splitxpens.databinding.FragmentHomeBinding
import com.rktuhinbd.splitxpens.home.viewmodel.HomeViewModel
import com.rktuhinbd.splitxpens.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewModel: AddMemberViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel = ViewModelProvider(this)[AddMemberViewModel::class.java]

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome

        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dataObserver.observe(requireActivity()) { data ->
            if (data != null) {
                if (!NetworkUtils.isInternetAvailable(requireActivity())) {
                    Toast.makeText(
                        requireActivity(),
                        "Internet Connection Unavailable!",
                        Toast.LENGTH_LONG
                    ).show()
                }

                Log.d("HomeTAG", GsonBuilder().setPrettyPrinting().create().toJson(data))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}