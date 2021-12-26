package com.nsystem.features.usersearch.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "user")
@JsonClass(generateAdapter = true)
data class User(

    @PrimaryKey
    @Json(name = "login")
    val login: String,

    @Json(name = "id")
    val id: Long,

    @Json(name = "node_id")
    @ColumnInfo(name = "node_id")
    val nodeId: String,

    @Json(name = "avatar_url")
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,

    @Json(name = "gravatar_id")
    @ColumnInfo(name = "gravatar_id")
    val gravatarId: String,

    @Json(name = "url")
    val url: String,

    @Json(name = "html_url")
    @ColumnInfo(name = "html_url")
    val htmlUrl: String,

    @Json(name = "followers_url")
    @ColumnInfo(name = "followers_url")
    val followersUrl: String,

    @Json(name = "following_url")
    @ColumnInfo(name = "following_url")
    val followingUrl: String,

    @Json(name = "gists_url")
    @ColumnInfo(name = "gists_url")
    val gistsUrl: String,

    @Json(name = "starred_url")
    @ColumnInfo(name = "starred_url")
    val starredUrl: String,

    @Json(name = "subscriptions_url")
    @ColumnInfo(name = "subscriptions_url")
    val subscriptionsUrl: String,

    @Json(name = "organizations_url")
    @ColumnInfo(name = "organizations_url")
    val organizationsUrl: String,

    @Json(name = "repos_url")
    @ColumnInfo(name = "repos_url")
    val reposUrl: String,

    @Json(name = "events_url")
    @ColumnInfo(name = "events_url")
    val eventsUrl: String,

    @Json(name = "received_events_url")
    @ColumnInfo(name = "received_events_url")
    val receivedEventsUrl: String,

    @Json(name = "type")
    val type: String,

    @Json(name = "site_admin")
    @ColumnInfo(name = "site_admin")
    val siteAdmin: Boolean,

    @Json(name = "name")
    val name: String?,

    @Json(name = "company")
    val company: String?,

    @Json(name = "blog")
    val blog: String?,

    @Json(name = "location")
    val location: String?,

    @Json(name = "email")
    val email: String?,

    @Json(name = "hireable")
    val hireable: Boolean?,

    @Json(name = "bio")
    val bio: String?,

    @Json(name = "twitter_username")
    @ColumnInfo(name = "twitter_username")
    val twitterUsername: String?,

    @Json(name = "public_repos")
    @ColumnInfo(name = "public_repos")
    val publicRepos: Int,

    @Json(name = "public_gists")
    @ColumnInfo(name = "public_gists")
    val publicGists: Int,

    @Json(name = "followers")
    val followers: Long,

    @Json(name = "following")
    val following: Long,

    @Json(name = "score")
    val score: Double?
)
