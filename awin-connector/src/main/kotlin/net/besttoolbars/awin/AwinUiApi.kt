package net.besttoolbars.awin

import net.besttoolbars.connectors.shared.RateLimitConfig
import net.besttoolbars.connectors.shared.provideHttpClientWithRateLimit
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming
import java.time.Duration
import java.util.concurrent.CompletableFuture

interface AwinUiApi {
    @Streaming
    @GET("/export-promotions/{publisher-id}/{token}")
    fun promotions(
        @Path("publisher-id") publisherId: Int,
        @Path("token") promotionToken: String,
        @Query("promotionType") type: AwinPromotionType? = null,
        @Query("advertiserIds") advertiserIds: CompressedArray<Int>? = null,
        @Query("membershipStatus") membershipStatus: AwinRelationship? = null,
        @Query("promotionStatus") promotionStatus: AwinPromotionStatus? = null,
        @Query("categoryIds") categoryIds: CompressedArray<Int>? = null,
        @Query("regionIds") regionIds: CompressedArray<Int>? = null,
        @Query("exclusiveOnly") exclusiveOnly: Boolean? = null
    ): CompletableFuture<ResponseBody>

    companion object {
        fun provider(
            url: String = "https://ui.awin.com",
            client: OkHttpClient? = null,
            config: RateLimitConfig = RateLimitConfig(20, Duration.ofMinutes(1))
        ): AwinUiApi {
            val httpClient = provideHttpClientWithRateLimit(config, client)
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(httpClient)
            return retrofit.build().create(AwinUiApi::class.java)
        }
    }
}