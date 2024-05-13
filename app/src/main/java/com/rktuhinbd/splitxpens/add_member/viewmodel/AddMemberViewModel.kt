package com.rktuhinbd.splitxpens.add_member.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rktuhinbd.splitxpens.add_member.model.MemberEntityData
import com.rktuhinbd.splitxpens.db.RoomDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddMemberViewModel @Inject constructor(
    private val roomDao: RoomDao
) : ViewModel() {

    val dataObserver: LiveData<MemberEntityData> = roomDao.getMembers()

    fun addMember(data: MemberEntityData) {
        viewModelScope.launch {
            roomDao.addMember(data)
        }
    }
}