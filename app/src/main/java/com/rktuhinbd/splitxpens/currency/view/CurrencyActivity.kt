package com.rktuhinbd.splitxpens.currency.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rktuhinbd.splitxpens.R
import com.rktuhinbd.splitxpens.Utils.readJsonFile
import com.rktuhinbd.splitxpens.currency.model.Currency
import com.rktuhinbd.splitxpens.databinding.ActivityCurrencyBinding

class CurrencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyBinding

    private lateinit var recyclerAdapter: CurrencyRvAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getExtras()
        initComponents()
        initListeners()

    }

    private fun getExtras() {
        val extraData = intent.getParcelableExtra<Currency>("data_extra")

        if(extraData?.currency != "USD"){
            setCurrency(extraData?.currency, extraData?.name, extraData?.symbol)
        }
    }

    private fun initComponents() {

        binding.toolbar.toolbarTitle.text = getString(R.string.choose_currency)

        val data = readJsonFile(this, "currencies.json")

        recyclerAdapter = CurrencyRvAdapter(data!!)
        binding.currencyListRV.adapter = recyclerAdapter
    }

    private fun initListeners() {

        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }

        binding.currencyUSDLayout.setOnClickListener {

            binding.currencySelectionLayout.visibility = View.GONE

            val currencyData = Currency("USD", "$", "United States Dollar")

            setResult(RESULT_OK, Intent().putExtra("extra", currencyData))
            finish()
        }

        recyclerAdapter.onItemClick = {
            val currencyData = Currency(it.currency, it.symbol, it.name)

            setResult(RESULT_OK, Intent().putExtra("extra", currencyData))
            finish()
        }
    }

    private fun setCurrency(
        shortFormCurrency: String?,
        fullFormCurrency: String?,
        symbol: String?
    ) {
        if (!shortFormCurrency.isNullOrEmpty() || !fullFormCurrency.isNullOrEmpty() || !symbol.isNullOrEmpty()) {
            binding.shortFormSelectedCurrencyTV.text = shortFormCurrency
            binding.fullFormSelectedCurrencyTV.text = fullFormCurrency
            binding.selectedCurrencyRateTV.text = symbol

            binding.currencySelectionLayout.visibility = View.VISIBLE
        } else {
            binding.currencySelectionLayout.visibility = View.GONE
        }
    }

}