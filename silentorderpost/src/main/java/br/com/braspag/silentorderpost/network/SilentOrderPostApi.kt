package br.com.braspag.silentorderpost.network

import br.com.braspag.silentorderpost.models.AccessTokenResponse
import br.com.braspag.silentorderpost.models.SilentOrderPostResponse
import retrofit2.Call
import retrofit2.http.*

internal interface SilentOrderPostApi {
    @POST("accesstoken")
    fun getAccessToken(
        @Query("merchantId") merchantId: String,
        @Header("Content-Type") content_type: String = "application/json",
        @Header("Accept") accept: String = "application/json"
    ): Call<AccessTokenResponse>

    @FormUrlEncoded
    @POST("card")
    fun sendCardData(
        @Field("AccessToken") accessToken: String,
        @Field("HolderName") holderName: String,
        @Field("RawNumber") rawNumber: String,
        @Field("Expiration") expiration: String,
        @Field("SecurityCode") securityCode: String,
        @Field("EnableBinQuery") enableBinQuery: Boolean = false,
        @Field("EnableVerifyCard") enableVerifyCard: Boolean = false,
        @Field("EnableTokenize") enableTokenize: Boolean = false,
        @Header( "Content-Type") content_type: String = "application/x-www-form-urlencoded",
        @Header("Accept") accept: String = "application/json",
        @Header("Charset") charset: String = "UTF-8"
    ): Call<SilentOrderPostResponse>
}