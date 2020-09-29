package net.besttoolbars.city.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityAdsResponse<T>(
    val status: Long? = null,
    val error: String? = null,
    val requestId: Long? = null,
    val data: T? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityAdsPaginationDataMap<T>(
    val total: Long = 0,
    val items: Map<String, T> = emptyMap()
) {
    fun hasNext(offset: Long): Boolean = total > offset + items.size
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityAdsPaginationDataList<T>(
    val total: Long = 0,
    val items: List<T> = emptyList()
) {
    fun hasNext(offset: Long): Boolean = total > offset + items.size
}

typealias CityAdsPaginationMapResponse<T> = CityAdsResponse<CityAdsPaginationDataMap<T>>
typealias CityAdsPaginationListResponse<T> = CityAdsResponse<CityAdsPaginationDataList<T>>
