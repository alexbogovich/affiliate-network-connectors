package net.besttoolbars.city

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.city.response.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.CompletableFuture

interface CityAdsApi {
    @GET("/api/rest/webmaster/json/offers/{type}")
    fun offers(
        @Path("type") offerType: CityAdsOfferType = CityAdsOfferType.WEB,
        @Query("remote_auth") token: String,
        @Query("start") page: Int = 0,
        @Query("limit") limit: Int = 100,
        @Query("geo") geo: IdSet? = null,
        @Query("user_has_offer") hasOffer: Boolean = true,
        @Query("lang") responseLang: String = "en"
    ): CompletableFuture<CityAdsPaginationMapResponse<CityAdsOffer>>

    @GET("/api/rest/webmaster/json/coupons")
    fun coupons(
        @Query("remote_auth") token: String,
        @Query("start") page: Int = 0,
        @Query("limit") limit: Int = 100,
        @Query("geo") geo: IdSet? = null,
        @Query("lang") responseLang: String = "en",
        @Query("coupon_lang") couponLang: String? = null
    ): CompletableFuture<CityAdsPaginationListResponse<CityAdsCoupon>>

    companion object {
        fun provider(url: String = "https://cityads.com", client: OkHttpClient? = null): CityAdsApi {
            val objectMapper = jacksonObjectMapper().apply { registerModule(JavaTimeModule()) }
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            client?.let { retrofit.client(client) }
            return retrofit.build().create(CityAdsApi::class.java)
        }
    }
}

data class IdSet(val ids: Set<Int>) {
    override fun toString(): String = ids.joinToString(",")
    companion object {
        fun of(vararg ids: Int): IdSet = IdSet(ids.toSet())
    }
}

enum class CityAdsOfferType{
    WEB,
    WEB_FAVOURITE,
    MOBILE,
    MOBILE_FAVOURITE,
    API,
    API_FAVOURITE,
    ALL,
    ALL_FAVOURITE;
    override fun toString(): String = name.toLowerCase()
}