package net.besttoolbars.awin.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import net.besttoolbars.awin.AwinCommissionType

typealias AwinAdvertiserResponse = List<AwinAdvertiser>

/**
 *
 * @property displayUrl URL of the advertiser
 * @property primaryRegion Object that contains the name and the countryCode of the primary programme region
 * @property currencyCode ISO code of the currency of the programme
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class AwinAdvertiser(
    val id: Long,
    val name: String,
    val displayUrl: String,
    val clickThroughUrl: String,
    val logoUrl: String?,
    val primaryRegion: AwinPrimaryRegion? = null,
    val currencyCode: String? = null,
    val validDomains: List<AwinAdvertiserDomains> = emptyList()
)

data class AwinAdvertiserDomains(
    val domain: String? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AwinPrimaryRegion (
    val name: String,
    val countryCode: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AwinAdvertiserDetailsResponse(
    @JsonProperty("programmeInfo")
    val merchant: AwinAdvertiser,
    val kpi: AwinKpi?,
    val commissionRange: List<AwinCommissionRange> = emptyList()
)

data class AwinCommissionRange (
    val min: Double,
    val max: Double,
    val type: AwinCommissionType
)

/**
 * @property approvalPercentage	the approval percentage relates to the proportion of pending transactions which are then validated as approved
 * @property epc earnings per click
 * @property conversionRate	the conversion rate is the total number of sales divided by the total number of clicks
 * @property validationDays	the validation period represents the average amount of time an advertiser takes to validate pending transactions as either Approved or Declined
 * @property awinIndex	a score out of hundred, calculated from an algorithm of EPC, approval percentage, conversion rate and validation period
 */

data class AwinKpi (
    val averagePaymentTime: String,
    val approvalPercentage: Double,
    val epc: Double,
    val conversionRate: Double,
    val validationDays: Long,
    val awinIndex: Double
)