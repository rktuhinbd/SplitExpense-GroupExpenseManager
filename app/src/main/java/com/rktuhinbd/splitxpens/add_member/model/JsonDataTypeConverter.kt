package com.rktuhinbd.splitxpens.add_member.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rktuhinbd.splitxpens.add_member.model.MemberData

class JsonDataTypeConverter {

    @TypeConverter
    fun fromJson(json: String): List<MemberData> {
        // Convert the JSON string to a List<JsonData>
        val type = object : TypeToken<List<MemberData>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(data: List<MemberData>): String {
        // Convert the List<JsonData> to a JSON string
        return Gson().toJson(data)
    }
}