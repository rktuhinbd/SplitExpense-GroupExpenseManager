package com.rktuhinbd.splitxpens.add_member.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = MemberTable.TABLE_NAME)
@TypeConverters(JsonDataTypeConverter::class)
@Parcelize
data class MemberEntityData(

    @PrimaryKey(autoGenerate = true)
    @SerializedName(MemberTable.ID)
    val id: Int = 0,

    @ColumnInfo(name = MemberTable.DATE_TIME)
    @SerializedName(MemberTable.DATE_TIME)
    var date: String = "",

    @ColumnInfo(name = MemberTable.MEMBER_DATA)
    @SerializedName(MemberTable.MEMBER_DATA)
    var memberData: MutableList<MemberData> = arrayListOf()

) : Parcelable
