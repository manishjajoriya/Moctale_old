package com.manishjajoriya.moctale.presentation.exploreScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.manishjajoriya.moctale.ui.theme.DarkPurple
import com.manishjajoriya.moctale.ui.theme.LightPurple
import com.manishjajoriya.moctale.ui.theme.MediumPurple
import com.manishjajoriya.moctale.ui.theme.Purple
import kotlin.math.exp

@Composable
fun ExploreScreen(exploreViewModel: ExploreViewModel) {
  Box(
      modifier =
          Modifier.fillMaxSize()
              .background(
                  brush =
                      Brush.verticalGradient(
                          colors = listOf(Purple, LightPurple, MediumPurple, DarkPurple),
                          startY = 0f,
                          endY = Float.POSITIVE_INFINITY,
                      )
              )
  ) {
    Text(text = "This is Explore Screen ${exploreViewModel.exploreList.size}")
  }
}
