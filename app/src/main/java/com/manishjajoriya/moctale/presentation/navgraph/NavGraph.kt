package com.manishjajoriya.moctale.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.manishjajoriya.moctale.presentation.contentScreen.ContentScreen
import com.manishjajoriya.moctale.presentation.contentScreen.ContentViewModel
import com.manishjajoriya.moctale.presentation.exploreScreen.ExploreScreen
import com.manishjajoriya.moctale.presentation.exploreScreen.ExploreViewModel

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {
  val exploreViewModel: ExploreViewModel = hiltViewModel()
  val contentViewModel: ContentViewModel = hiltViewModel()
  val startDestination = Routes.Explore.route
  NavHost(navController, startDestination) {
    composable(Routes.Explore.route) { ExploreScreen(exploreViewModel, navController, modifier) }
    composable(Routes.Content.route + "/{slug}") {
      val slug = it.arguments?.getString("slug")
      ContentScreen(slug, contentViewModel, modifier)
    }
  }
}
