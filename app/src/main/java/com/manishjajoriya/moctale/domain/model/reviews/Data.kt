package com.manishjajoriya.moctale.domain.model.reviews

import com.google.gson.annotations.SerializedName

data class Data(
  @SerializedName("comment_count") val commentCount: Int,
  @SerializedName("created_at") val createdAt: String,
  @SerializedName("has_spoilers") val hasSpoilers: Boolean,
  val id: String,
  @SerializedName("like_count") val likeCount: Int,
  @SerializedName("liked_by_user") val likedByUser: Boolean,
  val text: String?,
  val user: User,
  val verdict: String,
)
