package com.rktuhinbd.splitxpens.add_member.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rktuhinbd.splitxpens.R
import com.rktuhinbd.splitxpens.add_member.adapter.MembersAdapter
import com.rktuhinbd.splitxpens.add_member.model.MemberData
import com.rktuhinbd.splitxpens.add_member.model.MemberEntityData
import com.rktuhinbd.splitxpens.add_member.viewmodel.AddMemberViewModel
import com.rktuhinbd.splitxpens.databinding.ActivityAddMemberBinding
import com.rktuhinbd.splitxpens.home.view.activity.HomeActivity
import com.rktuhinbd.splitxpens.utilities.Types
import com.rktuhinbd.splitxpens.utils.TimeUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMemberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMemberBinding

    private lateinit var rvAdapter: MembersAdapter

    private val viewModel: AddMemberViewModel by viewModels()

    private var memberList: MutableList<MemberData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        setupListeners()
    }

    private fun initComponents() {

        binding.toolbar.toolbarTitle.text = getString(R.string.add_member)

        binding.toolbar.ctaTV.text = getString(R.string.create)

        binding.toolbar.ctaTV.visibility = View.VISIBLE

        rvAdapter = MembersAdapter(context = this, dataList = arrayListOf())
        binding.rvMembers.adapter = rvAdapter
    }

    private fun setupListeners() {

        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }

        binding.toolbar.ctaTV.setOnClickListener {

            viewModel.addMember(
                MemberEntityData(
                    date = TimeUtil.getCurrentDateTime(),
                    memberData = memberList
                )
            )
            startActivity(Intent(this@AddMemberActivity, HomeActivity::class.java))
        }

        binding.addMemberBTN.setOnClickListener {
            if (!TextUtils.isEmpty(binding.nameTET.text.toString().trim())) {
                memberList.add(MemberData(binding.nameTET.text.toString().trim()))

                rvAdapter = MembersAdapter(context = this, dataList = memberList)
                binding.rvMembers.adapter = rvAdapter

                binding.nameTET.text?.clear()
            }
        }

        rvAdapter.onItemClick = { type: String, data: MemberData ->
            if (type == Types.Menu.rename.name) {
                Toast.makeText(this, "Rename ${data.name}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Delete ${data.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}