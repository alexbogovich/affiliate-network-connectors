package net.besttoolbars.lomadee.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class Pagination (
    val page: Long,
    val size: Long,
    val totalSize: Long,
    val totalPage: Long
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class RequestInfo (
    val status: String,
    val message: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Token(
    val token: String
)