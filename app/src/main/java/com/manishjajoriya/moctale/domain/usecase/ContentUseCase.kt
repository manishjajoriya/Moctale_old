package com.manishjajoriya.moctale.domain.usecase

import com.manishjajoriya.moctale.data.remote.MoctaleApi
import com.manishjajoriya.moctale.domain.model.content.Content
import javax.inject.Inject

class ContentUseCase @Inject constructor(private val moctaleApi: MoctaleApi) {
  suspend operator fun invoke(slug : String): Content {
    return moctaleApi.content(slug)
  }
}
