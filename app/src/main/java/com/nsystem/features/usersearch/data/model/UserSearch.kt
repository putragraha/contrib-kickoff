package com.nsystem.features.usersearch.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// TODO: 05/01/22 Remove on no longer used
@JsonClass(generateAdapter = true)
data class UserSearch(
    @Json(name = "total_count") val totalCount: Long,
    @Json(name = "incomplete_results") val incompleteResults: Boolean,
    @Json(name = "items") val items: List<UserSearchItems>
)
