package br.com.braspag.silentorderpost.network

import br.com.braspag.silentorderpost.extensions.HttpStatusCode
import br.com.braspag.silentorderpost.extensions.toStatusCode
import br.com.braspag.silentorderpost.models.*
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

internal class SilentOrderPostClient(
    private val merchantId: String,
    private val environment: SilentOrderPostEnvironment = SilentOrderPostEnvironment.SANDBOX
) {
    internal fun getAccessToken(callback: (model: AccessTokenResult<AccessTokenResponse>) -> Unit) {
        val webClient = WebClient(
            getEnvironmentUrl(environment)
        )

        val call = webClient.createService(SilentOrderPostApi::class.java).getAccessToken(merchantId)

        call.enqueue(object : Callback<AccessTokenResponse> {
            override fun onFailure(call: Call<AccessTokenResponse>, t: Throwable) {
                t.localizedMessage?.let {
                    callback.invoke(
                        AccessTokenResult(
                            result = null,
                            statusCode = HttpStatusCode.Unknown,
                            errors = listOf(
                                ErrorResponse(
                                    message = t.localizedMessage ?: "Unknown Error"
                                )
                            )
                        )
                    )
                }
            }

            override fun onResponse(
                call: Call<AccessTokenResponse>,
                response: Response<AccessTokenResponse>
            ) {
                when (response.isSuccessful) {
                    true ->
                        callback.invoke(
                            AccessTokenResult(
                                result = response.body(),
                                statusCode = response.code().toStatusCode()
                            )
                        )

                    false ->
                        callback.invoke(
                            AccessTokenResult(
                                result = null,
                                statusCode = response.code().toStatusCode(),
                                errors = listOf(
                                    ErrorResponse(
                                        message = if (response.message() != "") response.message() else "Unknown error"
                                    )
                                )
                            )
                        )
                }
            }
        })
    }

    internal fun sendCardData(
        accessToken: String,
        request: SilentOrderPostRequest,
        binQuery: Boolean,
        verifyCard: Boolean,
        tokenize: Boolean,
        callback: (model: SilentOrderPostResult<SilentOrderPostResponse>) -> Unit
    ) {
        val webClient = WebClient(
            getEnvironmentUrl(environment)
        )

        val call = webClient.createService(SilentOrderPostApi::class.java).sendCardData(
            accessToken = accessToken,
            holderName = request.cardHolder,
            rawNumber = request.cardNumber,
            expiration = request.cardExpirationDate,
            securityCode = request.cardSecurityCode,
            enableBinQuery = binQuery,
            enableVerifyCard = verifyCard,
            enableTokenize = tokenize
        )

        call.enqueue(object : Callback<SilentOrderPostResponse> {
            override fun onFailure(call: Call<SilentOrderPostResponse>, t: Throwable) {
                t.localizedMessage?.let {
                    callback.invoke(
                        SilentOrderPostResult(
                            result = null,
                            statusCode = HttpStatusCode.Unknown,
                            errors = null
                        )
                    )
                }
            }

            override fun onResponse(
                call: Call<SilentOrderPostResponse>,
                response: Response<SilentOrderPostResponse>
            ) {
                when (response.isSuccessful) {
                    true ->
                        callback.invoke(
                            SilentOrderPostResult(
                                result = response.body(),
                                statusCode = response.code().toStatusCode()
                            )
                        )

                    false -> {
                        val errorBody = response.errorBody()?.string()

                        var errors: List<ErrorResponse>?
                        var validations: ValidationResponse?

                        try {
                            errors = Gson().fromJson(
                                errorBody,
                                Array<ErrorResponse>::class.java
                            ).toList()
                        } catch (ex: Exception) {
                            errors = null
                        }

                        try {
                            validations = Gson().fromJson(
                                errorBody,
                                ValidationResponse::class.java
                            )
                        } catch (ex: Exception) {
                            validations = null
                        }

                        callback.invoke(
                            SilentOrderPostResult(
                                result = null,
                                statusCode = response.code().toStatusCode(),
                                errors = errors,
                                validations = validations
                            )
                        )
                    }
                }
            }
        })
    }

    private fun getEnvironmentUrl(environment: SilentOrderPostEnvironment): String {
        return if (environment == SilentOrderPostEnvironment.SANDBOX)
            "https://transactionsandbox.pagador.com.br/post/api/public/v1/"
        else
            "https://transaction.pagador.com.br/post/api/public/v1/"
    }
}