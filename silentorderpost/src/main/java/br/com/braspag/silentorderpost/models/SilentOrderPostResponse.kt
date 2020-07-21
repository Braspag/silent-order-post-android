package br.com.braspag.silentorderpost.models

import com.google.gson.annotations.SerializedName

data class SilentOrderPostResponse(
    @SerializedName("ForeignCard")
    val foreignCard: Boolean? = null,

    @SerializedName("Brand")
    val brand: String? = null,

    @SerializedName("BinQueryReturnCode")
    val binQueryReturnCode: String? = null,

    @SerializedName("BinQueryReturnMessage")
    val binQueryReturnMessage: String? = null,

    @SerializedName("VerifyCardStatus")
    val verifyCardStatus: Long? = null,

    @SerializedName("VerifyCardReturnCode")
    val verifyCardReturnCode: String? = null,

    @SerializedName("VerifyCardReturnMessage")
    val verifyCardReturnMessage: String? = null,

    @SerializedName("CardBin")
    val cardBin: String? = null,

    @SerializedName("CardLast4Digits")
    val cardLast4Digits: String? = null,

    @SerializedName("CardToken")
    val cardToken: String? = null,

    @SerializedName("PaymentToken")
    val paymentToken: String? = null
)