package com.manishjajoriya.moctale.presentation.contentScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.ui.theme.Gray
import com.manishjajoriya.moctale.ui.theme.Inter

@Composable
fun ProfileCircle(modifier: Modifier = Modifier, image: String?, name: String, character: String) {
  Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
    if (image.isNullOrBlank()) {
      // Fallback: show initials
      val initials =
          name
              .split(" ")
              .filter { it.isNotBlank() }
              .take(2)
              .map { it.first().uppercaseChar() }
              .joinToString("")

      Box(
          modifier = Modifier.size(120.dp).clip(CircleShape).background(Gray.copy(alpha = 0.3f)),
          contentAlignment = Alignment.Center,
      ) {
        Text(
            text = initials,
            style =
                TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = Constants.largeFontSize,
                ),
        )
      }
    } else {
      // Normal: load image
      AsyncImage(
          model = image,
          modifier = Modifier.size(120.dp).clip(CircleShape),
          contentDescription = name,
      )
    }

    Text(
        text = name,
        style =
            TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                fontSize = Constants.smallFontSize,
            ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
    Text(
        text = character,
        style =
            TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                fontSize = Constants.extraSmallFontSize,
                color = Gray,
            ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
  }
}
