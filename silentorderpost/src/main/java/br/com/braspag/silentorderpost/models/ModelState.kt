package br.com.braspag.silentorderpost.models

import com.google.gson.annotations.SerializedName

data class ModelState (
    @SerializedName("request.HolderName")
    val holderName: List<String>? = null,

    @SerializedName("request.RawNumber")
    val cardNumber: List<String>? = null,

    @SerializedName("request.Expiration")
    val expiration: List<String>? = null,

    @SerializedName("request.SecurityCode")
    val securityCode: List<String>? = null,

    @SerializedName("request.EnableBinQuery")
    val binQuery: List<String>? = null,

    @SerializedName("request.EnableVerifyCard")
    val verifyCard: List<String>? = null,

    @SerializedName("request.EnableTokenize")
    val tokenize: List<String>? = null
)