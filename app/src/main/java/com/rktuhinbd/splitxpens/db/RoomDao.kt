package com.rktuhinbd.splitxpens.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rktuhinbd.splitxpens.add_member.model.MemberEntityData
import com.rktuhinbd.splitxpens.add_member.model.MemberTable

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMember(data: MemberEntityData)

    @Query("SELECT * FROM ${MemberTable.TABLE_NAME}")
    fun getMembers(): LiveData<MemberEntityData>

}