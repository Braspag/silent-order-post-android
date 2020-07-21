package br.com.braspag.silentorderpost.models

import br.com.braspag.silentorderpost.models.ModelState
import com.google.gson.annotations.SerializedName

data class ValidationResponse (
    @SerializedName("Message")
    val message: String,

    @SerializedName("ModelState")
    val modelState: ModelState
)