package com.rktuhinbd.splitxpens.currency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rktuhinbd.splitxpens.currency.model.Currency
import com.rktuhinbd.splitxpens.databinding.RvItemCurrencyBinding

class CurrencyRvAdapter(private val dataList: MutableList<Currency>) :
    RecyclerView.Adapter<CurrencyRvAdapter.ViewHolder>() {

    var onItemClick: ((Currency) -> Unit)? = null

    class ViewHolder(val binding: RvItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val binding = RvItemCurrencyBinding.inflate(inflater)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = dataList[position]

        holder.binding.currencyShortFormTV.text = data.currency
        holder.binding.currencyFullFormTV.text = data.name
        holder.binding.currencyRateTV.text = data.symbol

        holder.binding.body.setOnClickListener {
            onItemClick?.invoke(data)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}