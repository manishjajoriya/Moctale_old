package com.manishjajoriya.moctale.presentation.browseScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.manishjajoriya.moctale.R

@Composable
fun BrowseSheetContent() {
  val list =
      listOf(
          BrowserItem(R.drawable.ic_category_icon, "Category"),
          BrowserItem(R.drawable.ic_genre_icon, "Genre"),
          BrowserItem(R.drawable.ic_country_icon, "Country"),
          BrowserItem(R.drawable.ic_lanuage_icon, "Language"),
          BrowserItem(R.drawable.ic_family_icon, "Family Friendly"),
          BrowserItem(R.drawable.ic_award_icon, "Award Winners"),
      )

  Text(text = "Browse by", modifier = Modifier.padding(8.dp))

  FlowRow(
      horizontalArrangement = Arrangement.spacedBy(24.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    list.forEach { Item(icon = it.icon, title = it.title) }
  }
}

@Composable
fun Item(icon: Int, title: String) {
  Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
      modifier = Modifier.padding(8.dp),
  ) {
    Icon(
        painter = painterResource(icon),
        contentDescription = title,
        modifier = Modifier.size(24.dp),
    )
    Text(text = title, modifier = Modifier.padding(top = 4.dp))
  }
}

data class BrowserItem(val icon: Int, val title: String)
