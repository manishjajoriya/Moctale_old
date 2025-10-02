package com.manishjajoriya.moctale.domain.usecase

import com.manishjajoriya.moctale.data.remote.MoctaleApi
import com.manishjajoriya.moctale.domain.model.ExploreItem
import jakarta.inject.Inject

class ExploreUseCase @Inject constructor(private val moctaleApi: MoctaleApi) {
  suspend operator fun invoke(): List<ExploreItem> {
    return moctaleApi.explore()
  }
  
}
