package com.manishjajoriya.moctale.domain.model.reviews

import com.google.gson.annotations.SerializedName

data class Reviews(
  val count: Int,
  @SerializedName("current_page") val currentPage: Int,
  val data: List<Data>,
  @SerializedName("next_page") val nextPage: Int?,
  @SerializedName("previous_page") val previousPage: Int?,
  @SerializedName("total_pages") val totalPages: Int
)