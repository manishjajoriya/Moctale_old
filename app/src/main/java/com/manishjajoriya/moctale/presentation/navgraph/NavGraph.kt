package com.manishjajoriya.moctale.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manishjajoriya.moctale.presentation.component.ScreenStructure
import com.manishjajoriya.moctale.presentation.exploreScreen.ExploreScreen
import com.manishjajoriya.moctale.presentation.exploreScreen.ExploreViewModel

@Composable
fun NavGraph(modifier: Modifier) {
  val navController = rememberNavController()
  val exploreViewModel: ExploreViewModel = hiltViewModel()
  val startDestination = Routes.Explore.route
  NavHost(navController, startDestination) {
    composable(Routes.Explore.route) { ScreenStructure(modifier) { ExploreScreen(exploreViewModel) } }
  }
}
