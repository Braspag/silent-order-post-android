package br.com.braspag.silentorderpost.models

import br.com.braspag.silentorderpost.extensions.HttpStatusCode

data class AccessTokenResult <T>    (
    val result: T?,
    val statusCode: HttpStatusCode,
    val errors: List<ErrorResponse>? = null
)