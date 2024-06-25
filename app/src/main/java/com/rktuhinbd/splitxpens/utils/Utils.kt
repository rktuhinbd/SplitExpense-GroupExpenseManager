package com.rktuhinbd.splitxpens.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rktuhinbd.splitxpens.currency.model.Currency
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object Utils {

    fun readJsonFile(context: Context, fileName: String): ArrayList<Currency>? {
        var arrayList: ArrayList<Currency> = arrayListOf()

        try {
            val inputStream: InputStream = context.assets.open(fileName)

            val reader = BufferedReader(InputStreamReader(inputStream))

            val stringBuilder = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            val jsonString = stringBuilder.toString()

            val gson = Gson()
            arrayList = gson.fromJson(jsonString, object : TypeToken<ArrayList<Currency>>() {}.type)

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return arrayList
    }
}