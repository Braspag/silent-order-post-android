package br.com.braspag.silentorderpost.models

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse (
    @SerializedName("MerchantId")
    val merchantId: String,

    @SerializedName("AccessToken")
    val accessToken: String,

    @SerializedName("Issued")
    val issued: String,

    @SerializedName("ExpiresIn")
    val expiresIn: String
)