package net.besttoolbars.city.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityAdsGeo(
    val id: Long? = null,
    val name: String? = null,
    val code: String? = null,
    val timezone: String? = null,
    @JsonProperty("country_id")
    val countryId: String? = null,
    @JsonProperty("area_id")
    val areaID: String? = null,
    @JsonProperty("iso")
    val iso: String? = null,
    @JsonProperty("parent_id")
    val parentId: String? = null,
    @JsonProperty("city_id")
    val cityId: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityAdsActivationLinks(
    val title: String? = null,
    @JsonProperty("deep_link")
    val link: String? = null,
    @field:JsonProperty("is_default")
    val isDefault: Boolean = false
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityAdsCategory(
    val id: String? = null,
    val title: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityAdsMainCategory(
    @JsonProperty("main_id")
    val id: String? = null,
    @JsonProperty("main_title")
    val title: String? = null,
    @JsonProperty("other_categories")
    val subCategories: List<CityAdsCategory> = emptyList()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityAdsOffer(
    val id: Long = 0,
    val name: String? = null,
    @JsonProperty("offer_logo")
    val logo: String? = null,
    @JsonProperty("offer_logo_svg")
    val logoSvg: String? = null,
    val favicon: String? = null,
    val categories: List<CityAdsMainCategory> = emptyList(),
    val site: String? = null,
    val geo: List<CityAdsGeo> = emptyList(),
    @JsonProperty("items")
    val links: List<CityAdsActivationLinks> = emptyList(),
    val cpa: String? = null,
    val cpl: String? = null,
    val screen: String? = null,
    @JsonProperty("shop_id")
    val shopId: String? = null,
    @JsonProperty("epc7days")
    val sevenDayEpc: Double = 0.0,
    @JsonProperty("epc90days")
    val threeMonthEpc: Double = 0.0
)