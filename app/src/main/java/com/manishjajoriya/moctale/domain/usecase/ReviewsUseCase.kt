package com.manishjajoriya.moctale.domain.usecase

import com.manishjajoriya.moctale.data.remote.MoctaleApi
import com.manishjajoriya.moctale.domain.model.reviews.Reviews

class ReviewsUseCase(private val moctaleApi: MoctaleApi) {
  suspend operator fun invoke(slug: String): Reviews {
    return moctaleApi.reviews(slug)
  }
}
