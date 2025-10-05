package com.manishjajoriya.moctale.domain.model.reviews

import com.google.gson.annotations.SerializedName

data class User(
  @SerializedName("first_name") val firstName: String,
  @SerializedName("is_verified") val isVerified: Boolean,
  @SerializedName("last_name") val lastName: String,
  @SerializedName("photo_thumbnail") val photoThumbnail: String,
  val username: String
)