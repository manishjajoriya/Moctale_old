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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.R
import com.manishjajoriya.moctale.ui.theme.Gray
import com.manishjajoriya.moctale.ui.theme.Inter

@Composable
fun BottomBar(onIconClick: (String) -> Unit) {
  Row(
    modifier = Modifier.fillMaxWidth().height(88.dp).navigationBarsPadding(),
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically
  ) {
    BottomBarIcon(R.drawable.ic_compass_icon, "Explore") { onIconClick("Explore") }
    BottomBarIcon(R.drawable.ic_calendar_icon, "Schedule") { onIconClick("Schedule") }
    BottomBarIcon(R.drawable.ic_browse_icon, "Browse") { onIconClick("Browse") }
    BottomBarIcon(R.drawable.ic_club_icon, "Clubs") { onIconClick("Clubs") }
    BottomBarIcon(R.drawable.ic_person_icon, "Profile") { onIconClick("Profile") }
  }
}

@Composable
fun BottomBarIcon(iconRes: Int, description: String, onClick: () -> Unit = {}) {
  Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
  ) {
    IconButton(onClick = onClick) {
      Icon(painter = painterResource(iconRes), contentDescription = description)
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
