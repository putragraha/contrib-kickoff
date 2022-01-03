package com.nsystem.features.usersearch.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoOld(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val projectName: String,
    @Json(name = "description") val description: String?,
    @Json(name = "stargazers_count") val stars: Long,
    @Json(name = "updated_at") val updatedAt: String
)
