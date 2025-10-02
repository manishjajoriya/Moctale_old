package com.manishjajoriya.moctale.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.R
import com.manishjajoriya.moctale.ui.theme.Gray
import com.manishjajoriya.moctale.ui.theme.Inter

@Composable
fun TopBar() {
  Row(
      modifier = Modifier.background(Color.Black).fillMaxWidth().height(56.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
  ) {
    Row(
        Modifier.weight(.6f).padding(start = Constants.smallPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
          "MOCTALE",
          style =
              TextStyle(
                  fontFamily = Inter,
                  fontSize = Constants.largeFontSize,
                  fontWeight = FontWeight.SemiBold,
              ),
      )
      Spacer(Modifier.width(Constants.mediumPadding))
      Text(
          "β",
          style =
              TextStyle(
                  fontFamily = Inter,
                  fontSize = Constants.largeFontSize,
                  fontWeight = FontWeight.Light,
                  color = Gray,
              ),
      )
    }

    Row(
        Modifier.weight(.4f),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
      IconButton(onClick = {}) {
        Icon(
            painter = painterResource(R.drawable.ic_search_icon),
            contentDescription = "search",
            tint = Gray,
        )
      }
      IconButton(onClick = {}) {
        Icon(
            painter = painterResource(R.drawable.ic_bell_icon),
            contentDescription = "notification",
            tint = Gray,
        )
      }
      IconButton(onClick = {}) {
        Icon(
            painter = painterResource(R.drawable.ic_three_dots_vertical_icon),
            contentDescription = "menu",
            tint = Gray,
        )
      }
    }
  }
}
