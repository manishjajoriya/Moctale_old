package com.manishjajoriya.moctale.presentation.contentScreen.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.domain.model.reviews.Data
import com.manishjajoriya.moctale.ui.theme.Black
import com.manishjajoriya.moctale.ui.theme.Inter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun Review(modifier: Modifier = Modifier, data: Data, reviewColors: List<Color>) {
  // date formatter for - 2025-10-02T11:15:39.401437
  val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
  val createAt = data.createdAt
  val parsecData = LocalDateTime.parse(createAt, dateFormatter)
  val reviewColor =
    when (data.verdict) {
      "NEGATIVE" -> reviewColors[3]
      "NEUTRAL" -> reviewColors[2]
      "POSITIVE" -> reviewColors[1]
      "PERFECT" -> reviewColors[0]
      else -> reviewColors[3]
    }
  Column(modifier.fillMaxWidth()) {
    Row {
      AsyncImage(
        modifier = Modifier
          .size(40.dp)
          .clip(CircleShape),
        model = data.user.photoThumbnail,
        contentDescription = data.user.username,
      )
      Column(Modifier.padding(start = Constants.extraSmallPadding)) {
        Text(text = data.user.username)
        Text(
          text =
            "${parsecData.dayOfMonth} ${parsecData.month.name.capitalize(Locale.ROOT).take(3)}"
        )
      }

      Text(
        text = data.verdict,
        modifier = Modifier
          .clip(RoundedCornerShape(4.dp))
          .background(reviewColor),
        color = Black,
      )
    }

    data.text?.let { ExpandableText(text = it) }
  }

  HorizontalDivider(modifier
    .fillMaxWidth()
    .padding(Constants.smallPadding))
}

@Composable
fun ExpandableText(
  text: String,
  minimizedMaxLines: Int = 3
) {
  var expanded by remember { mutableStateOf(false) }
  var isOverflow by remember { mutableStateOf(false) }

  Column(
    modifier = Modifier
      .animateContentSize()
      .padding(bottom = 4.dp)
  ) {
    Text(
      text = text,
      style = TextStyle(
        fontFamily = Inter,
        fontSize = Constants.extraSmallFontSize
      ),
      maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
      overflow = TextOverflow.Ellipsis,
      onTextLayout = { textLayoutResult ->
        // Detect overflow only once when not expanded
        if (!expanded) {
          isOverflow = textLayoutResult.hasVisualOverflow
        }
      }
    )

    if (isOverflow || expanded) {
      Text(
        text = if (expanded) "Show less" else "Show more",
        style = TextStyle(
          fontFamily = Inter,
          fontSize = Constants.extraSmallFontSize,
          color = Color(0xFF1E88E5)
        ),
        modifier = Modifier
          .padding(top = 4.dp)
          .clickable { expanded = !expanded }
      )
    }
  }
}


