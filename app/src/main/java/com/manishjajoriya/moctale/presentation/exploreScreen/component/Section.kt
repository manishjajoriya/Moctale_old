package com.manishjajoriya.moctale.presentation.exploreScreen.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
  LazyColumn {
    exploreList.forEach { exploreItem ->
      item {
        Row(Modifier.padding(Constants.smallPadding)) {
          AsyncImage(
              model = exploreItem.icon,
              contentDescription = exploreItem.name,
              Modifier.size(28.dp),
          )
          Text(
              text = exploreItem.name,
              Modifier.padding(start = Constants.extraSmallPadding),
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
        FlowRow(
            horizontalArrangement = Arrangement.Start,
        ) {
          exploreItem.contentList.forEach {
            CardItem(
                it,
                onClick = { navController.navigate(Routes.Content.route + "/${it.slug}") },
            )
          }
        }
      }
    }
  }
}

@Composable
fun CardItem(content: Content, onClick: () -> Unit) {
  Column(
      Modifier.padding(Constants.smallPadding).clickable(onClick = onClick),
      horizontalAlignment = Alignment.Start,
  ) {
    var isCaption = false
    val tag =
        if (content.caption.isNullOrBlank()) {
          if (content.isShow) "Show • ${content.year}" else "Movie • ${content.year}"
        } else {
          isCaption = true
          content.caption
        }

    AsyncImage(
        model = content.image,
        contentDescription = content.image,
        Modifier.height(300.dp).width(200.dp),
        alignment = Alignment.TopStart,
        contentScale = ContentScale.Crop,
    )
    Text(
        modifier = Modifier.width(200.dp),
        text = content.name,
        style = TextStyle(fontFamily = Inter, fontSize = Constants.smallFontSize),
        fontWeight = FontWeight.Medium,
        color = Gray,
        maxLines = 1,
    )

    val style =
        TextStyle(
            fontFamily = Inter,
            fontSize = Constants.extraSmallFontSize,
            fontWeight = FontWeight.Normal,
            color = Gray,
        )
    if (isCaption) {
      ShimmerText(
          text = tag,
          style =
              TextStyle(
                  fontFamily = Inter,
                  fontSize = Constants.extraSmallFontSize,
                  fontWeight = FontWeight.Normal,
                  color = Gray,
              ),
      )
    } else {
      Text(text = tag, style = style)
    }
  }
}

@Composable
fun ShimmerText(text: String, style: TextStyle) {
  val transition = rememberInfiniteTransition()
  val shimmerTranslate by
      transition.animateFloat(
          initialValue = -1f,
          targetValue = 2f,
          animationSpec =
              infiniteRepeatable(
                  animation = tween(durationMillis = 3000, easing = LinearEasing),
                  repeatMode = RepeatMode.Restart,
              ),
      )

  val brush =
      Brush.linearGradient(
          colors = listOf(Color.Gray, Color.White, Color.Gray),
          start = Offset(x = shimmerTranslate * 300, y = 0f),
          end = Offset(x = (shimmerTranslate + 0.5f) * 300, y = 0f),
      )
  val newStyle = style.copy(brush = brush)
  Text(text = text, style = newStyle)
}
