package com.manishjajoriya.moctale.presentation.contentScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manishjajoriya.moctale.domain.model.content.Content
import com.manishjajoriya.moctale.domain.model.reviews.Reviews
import com.manishjajoriya.moctale.domain.usecase.MoctaleApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(private val moctaleApiUseCase: MoctaleApiUseCase) :
    ViewModel() {
  var content by mutableStateOf<Content?>(null)
    private set

  var reviews by mutableStateOf<Reviews?>(null)

  var loading by mutableStateOf(false)
    private set

  var error by mutableStateOf<Exception?>(null)

  fun loadContent(slug: String?) {
    loading = true
    viewModelScope.launch(Dispatchers.IO) {
      try {
        slug?.let { content = moctaleApiUseCase.contentUseCase(slug) }
      } catch (e: Exception) {
        error = e
      } finally {
        loading = false
      }
    }
  }

  fun loadReview(slug: String?) {
    loading = true
    viewModelScope.launch(Dispatchers.IO) {
      try {
        slug?.let { reviews = moctaleApiUseCase.reviewsUseCase(slug) }
      } catch (e: Exception) {
        error = e
      } finally {
        loading = false
      }
    }
  }
}
