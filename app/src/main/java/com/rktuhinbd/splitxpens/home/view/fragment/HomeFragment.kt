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
import com.rktuhinbd.splitxpens.add_member.adapter.MembersAdapter
import com.rktuhinbd.splitxpens.add_member.model.MemberData
import com.rktuhinbd.splitxpens.add_member.viewmodel.AddMemberViewModel
import com.rktuhinbd.splitxpens.databinding.FragmentExpensesBinding
import com.rktuhinbd.splitxpens.databinding.FragmentHomeBinding
import com.rktuhinbd.splitxpens.home.viewmodel.HomeViewModel
import com.rktuhinbd.splitxpens.utilities.Types
import com.rktuhinbd.splitxpens.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: AddMemberViewModel

    private lateinit var rvAdapter: MembersAdapter


    fun newInstance(counter: Int?): HomeFragment {
        val fragment = HomeFragment()
        val args = Bundle()
        args.putInt("_home_", counter!!)
        fragment.arguments = args
        return fragment
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel = ViewModelProvider(this)[AddMemberViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome

        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAdapter = MembersAdapter(context = requireActivity(), dataList = arrayListOf())
        binding.rvMembers.adapter = rvAdapter

        rvAdapter.onItemClick = { type: String, data: MemberData ->
            if (type == Types.Menu.rename.name) {
                Toast.makeText(requireActivity(), "Rename ${data.name}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireActivity(), "Delete  ${data.name}", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.dataObserver.observe(requireActivity()) { data ->
            if (data != null) {
                rvAdapter.updateData(data.memberData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}