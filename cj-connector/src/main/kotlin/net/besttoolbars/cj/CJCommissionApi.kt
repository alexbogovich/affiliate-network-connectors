package net.besttoolbars.cj

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import net.besttoolbars.cj.response.CjGraphQlResponse
import net.besttoolbars.cj.response.CjPublisherCommissionResponse
import net.besttoolbars.connectors.shared.graphql.GraphQLBodyConverter
import net.besttoolbars.connectors.shared.graphql.GraphQL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.CompletableFuture

interface CJCommissionApi {
    @POST("/query")
    fun commissions(
        @Header("Authorization") authorization: String,
        @Body @GraphQL query: String
    ): CompletableFuture<CjGraphQlResponse<CjPublisherCommissionResponse>>

    companion object {
        private fun provideJsonMapper() = jacksonObjectMapper().apply {
            enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        }

        fun provider(url: String = "https://commissions.api.cj.com", client: OkHttpClient? = null): CJCommissionApi {
            val objectMapper = provideJsonMapper()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GraphQLBodyConverter())
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .baseUrl(url)
            if (client != null) {
                retrofit.client(client)
            }
            return retrofit.build().create(CJCommissionApi::class.java)
        }
    }
}