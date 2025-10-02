package com.manishjajoriya.moctale.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.R
import com.manishjajoriya.moctale.ui.theme.Gray
import com.manishjajoriya.moctale.ui.theme.Inter

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
  Row(
      modifier.fillMaxWidth().height(88.dp).navigationBarsPadding().drawBehind {
        val strokeWidth = 2.dp.toPx()
        val y = strokeWidth / 2
        drawLine(
            color = Color.Gray,
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = strokeWidth,
        )
      },
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically,
  ) {
    BottomBarIcon(R.drawable.ic_compass_icon, "Explore")
    BottomBarIcon(R.drawable.ic_calendar_icon, "Schedule")
    BottomBarIcon(R.drawable.ic_browse_icon, "Browse")
    BottomBarIcon(R.drawable.ic_club_icon, "Clubs")
    BottomBarIcon(R.drawable.ic_person_icon, "Profile")
  }
}

@Composable
fun BottomBarIcon(iconRes: Int, description: String) {
  Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
  ) {
    IconButton(onClick = {}, modifier = Modifier) {
      Icon(
          painter = painterResource(iconRes),
          contentDescription = description,
      )
    }
    Text(
        text = description,
        style =
            androidx.compose.ui.text.TextStyle(
                fontFamily = Inter,
                fontSize = Constants.extraSmallFontSize,
                color = Gray,
            ),
    )
  }
}
