package com.manishjajoriya.moctale.presentation.contentScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manishjajoriya.moctale.domain.model.content.Content
import com.manishjajoriya.moctale.domain.usecase.MoctaleApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(private val moctaleApiUseCase: MoctaleApiUseCase) :
    ViewModel() {
  var content by mutableStateOf<Content?>(null)
    private set

  var loading by mutableStateOf(false)
    private set

  var error by mutableStateOf<Exception?>(null)

  fun loadContent(slug: String?) {
    loading = true
    viewModelScope.launch {
      try {
        slug?.let {
          content = moctaleApiUseCase.contentUseCase(slug)
          Log.i("LOG", "API Call : $content")
        }
        Log.i("LOG", "API Call : $content")
      } catch (e: Exception) {
        error = e
      } finally {
        loading = false
      }
    }
  }
}
