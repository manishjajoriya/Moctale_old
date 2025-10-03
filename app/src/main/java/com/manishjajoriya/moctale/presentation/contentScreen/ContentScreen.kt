package com.manishjajoriya.moctale.presentation.contentScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.R
import com.manishjajoriya.moctale.presentation.contentScreen.component.CustomButton
import com.manishjajoriya.moctale.ui.theme.Gray
import com.manishjajoriya.moctale.ui.theme.Inter
import com.manishjajoriya.moctale.ui.theme.Pink
import com.manishjajoriya.moctale.ui.theme.White

@Composable
fun ContentScreen(
    slug: String?,
    contentViewModel: ContentViewModel,
    modifier: Modifier = Modifier,
) {

  contentViewModel.loadContent(slug)
  val content = contentViewModel.content

  content?.let {
    AsyncImage(
        model = content.banner,
        modifier = modifier.height(280.dp).fillMaxWidth(),
        contentDescription = "",
        contentScale = ContentScale.Crop,
    )
    Column(
        modifier.padding(top = 250.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Row {
        AsyncImage(
            model = content.image,
            contentDescription = "",
            Modifier.width(120.dp).padding(start = 8.dp),
        )
        val type = if (content.isShow) "Show" else if (content.isAnime) "Anime" else "Movie"
        Column {
          Text(text = "$type • ${content.year} • ${content.durationFormatted}")
          Text(text = content.name)
          Column(
              horizontalAlignment = Alignment.CenterHorizontally,
              modifier = Modifier.fillMaxWidth().padding(8.dp),
          ) {
            Row(Modifier.fillMaxWidth()) {
              Column(Modifier.weight(1f)) {
                Text(text = "Directed by")
                Text(
                    text = content.directorList[0].name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
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
              Column(Modifier.weight(1f)) {
                Text(text = "Age Rating")
                Text(text = content.ageRatingFormatted)
              }
            }
          }
        }
      }
      CustomButton(title = "Mark as Watched", icon = R.drawable.ic_open_eye_icon, color = Pink)
      Spacer(Modifier.height(Constants.extraSmallPadding))
      CustomButton(title = "Add to Collection", icon = R.drawable.ic_bookmark_icon, color = Gray)
      Text(
          text = "Overview",
          Modifier.fillMaxWidth().padding(top = Constants.mediumPadding),
          style = TextStyle(fontFamily = Inter, fontSize = Constants.mediumFontSize, color = White),
          textAlign = TextAlign.Start,
      )
      Text(
          text = content.description,
          Modifier.padding(top = Constants.mediumPadding),
          style = TextStyle(fontFamily = Inter, color = Gray),
      )
      HorizontalDivider(Modifier.padding(top = Constants.smallPadding))
      FlowRow(
          modifier = Modifier.fillMaxWidth().padding(8.dp),
          horizontalArrangement = Arrangement.Start,
          verticalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        content.genreList.forEach {
          GenrePill(genre = it.name, modifier = Modifier.padding(end = 8.dp))
        }
      }
    }
  }
}
