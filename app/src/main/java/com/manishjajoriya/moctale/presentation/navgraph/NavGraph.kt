package com.manishjajoriya.moctale.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manishjajoriya.moctale.presentation.exploreScreen.ExploreScreen

@Composable
fun NavGraph(modifier: Modifier) {
  val navController = rememberNavController()
  val startDestination = Routes.Explore.route
  NavHost(navController, startDestination){
    composable(Routes.Explore.route){
      ExploreScreen(modifier)
    }
  }
}
