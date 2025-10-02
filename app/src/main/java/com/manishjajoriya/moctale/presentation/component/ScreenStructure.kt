package com.manishjajoriya.moctale.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScreenStructure(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Box(modifier = modifier.fillMaxSize()) {
    Column {
      TopBar()
      content()
    }
  }
}
