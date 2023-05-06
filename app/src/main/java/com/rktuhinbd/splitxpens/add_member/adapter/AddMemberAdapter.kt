package com.rktuhinbd.splitxpens.add_member.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rktuhinbd.splitxpens.add_member.model.MemberData
import com.rktuhinbd.splitxpens.databinding.RvItemAddMemberBinding

class AddMemberAdapter(private val dataList: MutableList<MemberData>) :
    RecyclerView.Adapter<AddMemberAdapter.ViewHolder>() {

    var onItemClick: ((MemberData) -> Unit)? = null

    inner class ViewHolder(val binding: RvItemAddMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvItemAddMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = dataList[position]

        holder.binding.nameTV.text = data.name

//        if (position == dataList.size - 1) {
//            holder.binding.divider.visibility = View.GONE
//        }

        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(data)
        }
    }

    override fun getItemCount() = dataList.size

    fun updateData(newData: List<MemberData>) {
        dataList.clear()
        dataList.addAll(newData)
        notifyDataSetChanged()
    }

}