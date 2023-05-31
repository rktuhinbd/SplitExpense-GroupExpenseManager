package com.rktuhinbd.splitxpens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.rktuhinbd.splitxpens.add_member.view.AddMemberActivity
import com.rktuhinbd.splitxpens.currency.model.Currency
import com.rktuhinbd.splitxpens.currency.view.CurrencyActivity
import com.rktuhinbd.splitxpens.databinding.ActivityCreateGroupBinding

class CreateGroupActivity : AppCompatActivity() {

    val TAG = "CreateGroupActivity"

    private lateinit var binding: ActivityCreateGroupBinding

    var currency: Currency ?= Currency()

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){

            currency = it.data?.getParcelableExtra("extra")
            binding.currencyTV.text = currency?.currency
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        setupListeners()
    }

    private fun initComponents() {

        binding.toolbar.toolbarTitle.text = getString(R.string.create_group)
    }

    private fun setupListeners() {

        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }

        binding.currencyCL.setOnClickListener {
            val intent = Intent(this, CurrencyActivity::class.java)
            intent.putExtra("data_extra", currency)
            launcher.launch(intent)
        }

        binding.nextBTN.setOnClickListener {
            startActivity(Intent(this, AddMemberActivity::class.java))
        }
    }
}