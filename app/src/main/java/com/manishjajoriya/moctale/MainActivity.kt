package com.manishjajoriya.moctale

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.manishjajoriya.moctale.presentation.browseScreen.BrowseSheetContent
import com.manishjajoriya.moctale.presentation.component.BottomBar
import com.manishjajoriya.moctale.presentation.component.TopBar
import com.manishjajoriya.moctale.presentation.navgraph.NavGraph
import com.manishjajoriya.moctale.ui.theme.MoctaleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MoctaleTheme {
        val navController = rememberNavController()

        var showBrowseSheet by remember { mutableStateOf(false) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar() },
            bottomBar = {
              BottomBar { clickedDescription ->
                if (clickedDescription == "Browse") {
                  showBrowseSheet = true
                }
              }
            },
        ) { innerPadding ->
          NavGraph(navController, modifier = Modifier.padding(innerPadding))
        }

        if (showBrowseSheet) {
          ModalBottomSheet(onDismissRequest = { showBrowseSheet = false }) {
            BrowseSheetContent()
          }
        }
      }
    }
  }
}
