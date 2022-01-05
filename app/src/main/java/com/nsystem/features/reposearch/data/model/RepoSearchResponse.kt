package com.nsystem.features.reposearch.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoSearchResponse(
    @Json(name = "total_count") val totalCount: Long,
    @Json(name = "incomplete_results") val incompleteResults: Boolean,
    @Json(name = "items") val items: List<RepoSearchItemsResponse>
)
