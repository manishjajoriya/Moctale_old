package com.manishjajoriya.moctale.presentation.contentScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.ui.theme.Gray
import com.manishjajoriya.moctale.ui.theme.Inter

@Composable
fun ProfileCircle(modifier: Modifier = Modifier, image: String?, name: String, character: String) {
  Column(modifier.width(140.dp), horizontalAlignment = Alignment.CenterHorizontally) {
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
          modifier = Modifier.size(130.dp).clip(CircleShape).background(Gray.copy(alpha = 0.3f)),
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
          modifier = Modifier.size(130.dp).clip(CircleShape),
          contentDescription = name,
      )
    }

    Text(
        modifier = Modifier.width(140.dp),
        text = name,
        style =
            TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            ),
        textAlign = TextAlign.Center,
    )
    Text(
        modifier = Modifier.width(140.dp),
        text = character,
        style =
            TextStyle(
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                fontSize = Constants.extraSmallFontSize,
                color = Gray,
            ),
        textAlign = TextAlign.Center,
    )
  }
}
