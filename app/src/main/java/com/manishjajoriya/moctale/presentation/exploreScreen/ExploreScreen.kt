package com.manishjajoriya.moctale.presentation.exploreScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavHostController
import com.manishjajoriya.moctale.presentation.exploreScreen.component.Section
import com.manishjajoriya.moctale.ui.theme.DarkPurple
import com.manishjajoriya.moctale.ui.theme.LightPurple
import com.manishjajoriya.moctale.ui.theme.MediumPurple
import com.manishjajoriya.moctale.ui.theme.Purple

@Composable
fun ExploreScreen(
  exploreViewModel: ExploreViewModel,
  navController: NavHostController,
  modifier: Modifier
) {
  val exploreList = exploreViewModel.exploreList
  val loading = exploreViewModel.loading

  Box(
      modifier =
          modifier
              .fillMaxSize()
              .background(
                  brush =
                      Brush.verticalGradient(
                          colors = listOf(Purple, LightPurple, MediumPurple, DarkPurple),
                          startY = 0f,
                          endY = Float.POSITIVE_INFINITY,
                      )
              )
  ) {
    if (loading) {
      Text(text = "Loading…")
    } else if (exploreList.isEmpty()) {
      Text(text = "No items found")
    } else {
      Section(exploreList,navController)
    }
  }
}
