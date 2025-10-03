package com.manishjajoriya.moctale.presentation.navgraph

sealed class Routes(val route: String) {

  object Explore : Routes("exploreScreen")

  object Schedule : Routes("scheduleScreen")

  object Clubs : Routes("clubsScreen")

  object Profile : Routes("profileScreen")

  object Content : Routes("contentScreen")
}
