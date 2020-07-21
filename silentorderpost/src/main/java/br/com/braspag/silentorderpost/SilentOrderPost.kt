package br.com.braspag.silentorderpost

import br.com.braspag.silentorderpost.extensions.HttpStatusCode
import br.com.braspag.silentorderpost.models.SilentOrderPostEnvironment
import br.com.braspag.silentorderpost.models.SilentOrderPostRequest
import br.com.braspag.silentorderpost.models.SilentOrderPostResponse
import br.com.braspag.silentorderpost.models.SilentOrderPostResult
import br.com.braspag.silentorderpost.network.SilentOrderPostClient

class SilentOrderPost(
    private val merchantId: String,
    private val environment: SilentOrderPostEnvironment = SilentOrderPostEnvironment.SANDBOX
) {
    var binQuery = false
    var verifyCard = false
    var tokenize = false

    fun sendCardData(
        request: SilentOrderPostRequest,
        callback: (SilentOrderPostResult<SilentOrderPostResponse>) -> Unit
    ) {
        val client =
            SilentOrderPostClient(
                merchantId,
                environment
            )

        client.getAccessToken { accessTokenResult ->
            if (accessTokenResult.statusCode == HttpStatusCode.Created) {
                accessTokenResult.result?.accessToken?.let { accessToken ->
                    client.sendCardData(
                        accessToken = accessToken,
                        request = request,
                        binQuery = binQuery,
                        verifyCard = verifyCard,
                        tokenize = tokenize
                    ) {
                        callback.invoke(it)
                    }
                }
            } else {
                callback.invoke(
                    SilentOrderPostResult(
                        result = null,
                        statusCode = accessTokenResult.statusCode,
                        errors = accessTokenResult.errors
                    )
                )
            }
        }
    }
}