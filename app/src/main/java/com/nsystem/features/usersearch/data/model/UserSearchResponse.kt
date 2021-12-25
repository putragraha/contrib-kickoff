package com.nsystem.features.usersearch.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserSearchResponse(
    @Json(name = "total_count") val totalCount: Long,
    @Json(name = "incomplete_results") val incompleteResults: String,
    @Json(name = "items") val items: List<UserSearchItemsResponse>
)
