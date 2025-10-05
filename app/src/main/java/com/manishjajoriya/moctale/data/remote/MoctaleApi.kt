package com.manishjajoriya.moctale.data.remote

import com.manishjajoriya.moctale.domain.model.content.Content
import com.manishjajoriya.moctale.domain.model.explore.ExploreItem
import com.manishjajoriya.moctale.domain.model.reviews.Reviews
import retrofit2.http.GET
import retrofit2.http.Path

interface MoctaleApi {

  @GET("explore") suspend fun explore(): List<ExploreItem>

  @GET("library/content/{slug}")
  suspend fun content(@Path("slug") slug: String): Content

  @GET("activity/content/{slug}/reviews")
  suspend fun reviews(@Path("slug") slug: String): Reviews
}
