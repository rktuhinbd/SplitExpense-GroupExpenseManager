package com.rktuhinbd.splitxpens.currency.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    @SerializedName("cc") val currency: String? = null,
    @SerializedName("symbol") val symbol: String? = null,
    @SerializedName("name") val name: String? = null
) : Parcelable
