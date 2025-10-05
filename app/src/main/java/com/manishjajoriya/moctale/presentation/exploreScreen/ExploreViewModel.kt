package com.manishjajoriya.moctale.presentation.exploreScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manishjajoriya.moctale.domain.model.explore.ExploreItem
import com.manishjajoriya.moctale.domain.usecase.MoctaleApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val moctaleApiUseCase: MoctaleApiUseCase) :
    ViewModel() {
  var exploreList by mutableStateOf<List<ExploreItem>>(emptyList())
    private set

  var error by mutableStateOf<Exception?>(null)
  var loading by mutableStateOf(false)

  init {
    fetchExplore()
  }

  fun fetchExplore() {
    loading = true
    viewModelScope.launch(Dispatchers.IO) {
      try {
        exploreList = moctaleApiUseCase.exploreUseCase()
      } catch (e: Exception) {
        error = e
      } finally {
        loading = false
      }
    }
  }
}
