package com.rktuhinbd.splitxpens.currency.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rktuhinbd.splitxpens.Utils.readJsonFile
import com.rktuhinbd.splitxpens.currency.model.Currency
import com.rktuhinbd.splitxpens.databinding.ActivityCurrencyBinding

class CurrencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyBinding

    private lateinit var recyclerAdapter: CurrencyRvAdapter

    var callBackListener: ((Currency) -> Unit)? = null

    val TAG: String = "CurrencyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = readJsonFile(this, "currencies.json")

        if (data?.isNotEmpty() == true) {
            recyclerAdapter = CurrencyRvAdapter(data)
            binding.currencyListRV.adapter = recyclerAdapter

            recyclerAdapter.onItemClick = {
                binding.currencySelectionLayout.visibility = View.VISIBLE
                binding.shortFormSelectedCurrencyTV.text = it.currency
                binding.fullFormSelectedCurrencyTV.text = it.name
                binding.selectedCurrencyRateTV.text = it.symbol
            }
        }

        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }

        binding.currencyUSDLayout.setOnClickListener {
            binding.currencySelectionLayout.visibility = View.GONE
            val data = Currency("USD", "$", "United States Dollar")
            callBackListener?.invoke(data)
            finish()
        }
    }

}