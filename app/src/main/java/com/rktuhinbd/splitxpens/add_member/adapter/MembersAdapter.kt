package com.rktuhinbd.splitxpens.add_member.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.rktuhinbd.splitxpens.R
import com.rktuhinbd.splitxpens.add_member.model.MemberData
import com.rktuhinbd.splitxpens.databinding.RvItemMembersBinding
import com.rktuhinbd.splitxpens.utilities.Types

class MembersAdapter(
    private val context: Context,
    private val dataList: MutableList<MemberData>
) : RecyclerView.Adapter<MembersAdapter.ViewHolder>() {

    var onItemClick: ((String, MemberData) -> Unit)? = null

    inner class ViewHolder(val binding: RvItemMembersBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvItemMembersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val data = dataList[position]
        val binding = viewHolder.binding

        binding.nameTV.text = data.name

        if (dataList.size > 1 && position == dataList.size - 1) {
            binding.divider.visibility = View.GONE
        }

        binding.root.setOnLongClickListener {
            showPopupMenu(it, data)
            true
        }
    }

    override fun getItemCount() = dataList.size


    private fun showPopupMenu(view: View, data: MemberData) {

        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.gravity = android.view.Gravity.END
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


    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<MemberData>) {
        dataList.clear()
        dataList.addAll(newData)
        notifyDataSetChanged()
    }

}