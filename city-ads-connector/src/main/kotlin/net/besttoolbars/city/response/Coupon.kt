package net.besttoolbars.city.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
data class CityAdsCoupon(
    val id: String,
    val name: String? = null,
    @JsonProperty("start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd HH:mm:ss")
    val startDate: LocalDateTime? = null,
    @JsonProperty("active_to")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd HH:mm:ss")
    val activeTo: LocalDateTime? = null,
    @JsonProperty("promo_code")
    val promoCode: String? = null,
    val description: String? = null,
    val image: String? = null,
    val domain: String? = null,
    @JsonProperty("offer_id")
    val offerId: String,
    @JsonProperty("offer_name")
    val offerName: String? = null,
    @JsonProperty("coupon_lang")
    val couponLang: String? = null,
    @JsonProperty("coupon_type")
    val couponType: String? = null,
    val status: String? = null,
    val scope: String? = null,
    @JsonProperty("status_id")
    val statusId: Long? = null,
    @JsonProperty("scope_id")
    val scopeId: Long? = null,
    @JsonProperty("category_id")
    val categoryId: Long,
    @JsonProperty("coupon_category_id")
    val couponCategoryId: Long,
    @JsonProperty("action_category_name")
    val categoryName: String? = null,
    @JsonProperty("coupon_category_name")
    val couponCategoryName: String? = null,
    val url: String,
    val discount: String? = null,
    @JsonProperty("offer_logo")
    val offerLogo: String? = null,
    val geo: List<CityAdsGeo> = emptyList()
)