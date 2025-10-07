package com.manishjajoriya.moctale.presentation.contentScreen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.manishjajoriya.moctale.domain.model.content.Content

@Composable
fun ContentInfo(modifier: Modifier = Modifier, content: Content) {
  Row(modifier) {
    // Poster images
    AsyncImage(
      model = content.image,
      contentDescription = "",
      Modifier
        .width(120.dp)
        .padding(start = 8.dp),
    )
    val type = if (content.isShow) "Show" else if (content.isAnime) "Anime" else "Movie"
    Column {
      Text(text = "$type • ${content.year} • ${content.durationFormatted}")
      Text(text = content.name)
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp),
      ) {
        Row(Modifier.fillMaxWidth()) {
          Column(Modifier.weight(1f)) {
            if (content.directorList.isNotEmpty()) {
              Text(text = "Directed by")
              Text(
                text = content.directorList[0].name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
              )
            } else {
              Text(text = "Showrunner")
              Text(
                text = content.crewList[0].name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
              )
            }
          }
          Column(Modifier.weight(1f)) {
            Text(text = "Country")
            Text(text = content.countryOfOrigin.name)
          }
        }
        Row(Modifier.fillMaxWidth()) {
          Column(Modifier.weight(1f)) {
            Text(text = "Language")
            Text(
              text =
                content.languageList[0].name +
                  if (content.languageList.size > 1) " +${content.languageList.size - 1}"
                  else ""
            )
          }
          content.ageRatingFormatted?.let {
            (Column(Modifier.weight(1f)) {
              Text(text = "Age Rating")
              Text(text = content.ageRatingFormatted)
            })
          }
        }
      }
    }
  }
}
