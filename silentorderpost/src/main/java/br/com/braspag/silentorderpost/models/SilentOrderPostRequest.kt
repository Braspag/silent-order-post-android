package br.com.braspag.silentorderpost.models

data class SilentOrderPostRequest(
    val cardHolder: String,
    val cardNumber: String,
    val cardExpirationDate: String,
    val cardSecurityCode: String
)