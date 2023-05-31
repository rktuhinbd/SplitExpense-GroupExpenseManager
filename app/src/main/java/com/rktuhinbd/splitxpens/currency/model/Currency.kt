package com.rktuhinbd.splitxpens.currency.model

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("cc") val currency : String? = null,
    @SerializedName("symbol") val symbol   : String? = null,
    @SerializedName("name") val name     : String? = null
)
