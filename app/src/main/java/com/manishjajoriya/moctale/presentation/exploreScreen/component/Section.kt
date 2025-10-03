package com.manishjajoriya.moctale.presentation.exploreScreen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.domain.model.explore.Content
import com.manishjajoriya.moctale.domain.model.explore.ExploreItem
import com.manishjajoriya.moctale.presentation.navgraph.Routes
import com.manishjajoriya.moctale.ui.theme.Gray
import com.manishjajoriya.moctale.ui.theme.Inter

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun Section(exploreList: List<ExploreItem>, navController: NavHostController) {
  val configuration = LocalConfiguration.current
  val screenWidthDp = configuration.screenWidthDp.dp
  LazyColumn {
    exploreList.forEach { exploreItem ->
      val height = calculateHeight(screenWidthDp, exploreItem.contentList.size)
      item {
        Row(Modifier.padding(Constants.smallPadding)) {
          AsyncImage(
              model = exploreItem.icon,
              contentDescription = exploreItem.name,
              Modifier.size(28.dp),
          )
          Text(
              text = exploreItem.name,
            Modifier.padding(start = Constants.extraSmallPadding ),
              style =
                  TextStyle(
                      fontFamily = Inter,
                      fontSize = Constants.largeFontSize,
                      fontWeight = FontWeight.SemiBold,
                  ),
          )
        }
      }
      item {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            modifier = Modifier.heightIn(max = height),
        ) {
          items(exploreItem.contentList.size, key = { exploreItem.contentList[it].slug }) { index ->
            CardItem(exploreItem.contentList[index], onClick = {
              navController.navigate(Routes.Content.route+"/${exploreItem.contentList[index].slug}")
            })
          }
        }
      }
    }
  }
}

fun calculateHeight(maxWidth: Dp, len: Int): Dp {
  var height = maxWidth / len
  height = height * 350
  return height
}

@Composable
fun CardItem(content: Content, onClick: () -> Unit) {

  Column(
      Modifier.padding(Constants.smallPadding).clickable(onClick = onClick),
      horizontalAlignment = Alignment.Start,
  ) {
    AsyncImage(
        model = content.image,
        contentDescription = content.image,
        Modifier.size(300.dp),
    )
    Text(
        text = content.name,
        style = TextStyle(fontFamily = Inter, fontSize = Constants.mediumFontSize),
        fontWeight = FontWeight.Medium,
        color = Gray,
        maxLines = 1,
    )
    Text(
        text = content.caption ?: if (content.isShow) "Show" else "Movie • ${content.year}",
        style = TextStyle(fontFamily = Inter, fontSize = Constants.extraSmallFontSize),
        fontWeight = FontWeight.ExtraLight,
        color = Gray,
    )
  }
}
