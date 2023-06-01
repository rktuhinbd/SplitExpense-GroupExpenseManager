package com.rktuhinbd.splitxpens.add_member.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.rktuhinbd.splitxpens.R
import com.rktuhinbd.splitxpens.add_member.model.MemberData
import com.rktuhinbd.splitxpens.databinding.RvItemAddMemberBinding
import com.rktuhinbd.splitxpens.utilities.Types

class AddMemberAdapter(
    private val context: Context,
    private val dataList: MutableList<MemberData>
) : RecyclerView.Adapter<AddMemberAdapter.ViewHolder>() {

    var onItemClick: ((String, MemberData) -> Unit)? = null

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

        holder.binding.root.setOnLongClickListener {
            showPopupMenu(it, data)
            true
        }
    }

    override fun getItemCount() = dataList.size


    private fun showPopupMenu(view: View, data: MemberData) {

        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.popup_menu)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true)
        }
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_rename -> {
                    onItemClick?.invoke(Types.Menu.rename.name, data)
                    true
                }

                R.id.menu_delete -> {
                    onItemClick?.invoke(Types.Menu.delete.name, data)
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }

    fun updateData(newData: List<MemberData>) {
        dataList.clear()
        dataList.addAll(newData)
        notifyDataSetChanged()
    }

}