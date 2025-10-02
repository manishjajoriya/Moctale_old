package com.manishjajoriya.moctale.data.remote

import com.manishjajoriya.moctale.domain.model.ExploreItem
import retrofit2.http.GET

interface MoctaleApi {

  @GET("explore")
  suspend fun explore() : List<ExploreItem>
}
