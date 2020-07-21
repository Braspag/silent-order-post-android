package br.com.braspag.silentorderpost.models

import br.com.braspag.silentorderpost.extensions.HttpStatusCode

data class SilentOrderPostResult <SilentOrderPostResponse>    (
    val result: SilentOrderPostResponse?,
    val statusCode: HttpStatusCode,
    val errors: List<ErrorResponse>? = null,
    val validations: ValidationResponse? = null
)