package com.manishjajoriya.moctale.presentation.contentScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.R
import com.manishjajoriya.moctale.domain.model.content.TicketingSite
import com.manishjajoriya.moctale.ui.theme.Gray
import com.manishjajoriya.moctale.ui.theme.Inter

@Composable
fun TicketBook(ticketingSite: TicketingSite) {
  Row(
      Modifier.clip(RoundedCornerShape(8.dp)).background(Gray).padding(Constants.smallPadding),
      verticalAlignment = Alignment.CenterVertically,
  ) {
    AsyncImage(
        model = ticketingSite.image,
        modifier = Modifier.size(40.dp).clip(RoundedCornerShape(8.dp)),
        contentDescription = ticketingSite.name,
    )
    Text(
        modifier = Modifier.padding(horizontal = Constants.extraSmallPadding),
        text = ticketingSite.name,
        style = TextStyle(fontFamily = Inter, fontSize = Constants.smallFontSize),
    )
    Icon(painter = painterResource(R.drawable.ic_arrow_outward_icon), "book ticket")
  }
}
