package com.manishjajoriya.moctale.presentation.contentScreen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.ui.theme.Inter
import com.manishjajoriya.moctale.ui.theme.White

@Composable
fun ContentSection(
    modifier: Modifier = Modifier,
    title: String,
    showHorizontalDivider: Boolean = true,
    content: @Composable () -> Unit,
) {
  Text(
      text = title,
      Modifier.fillMaxWidth().padding(top = Constants.mediumPadding),
      style =
          TextStyle(
              fontFamily = Inter,
              fontWeight = FontWeight.SemiBold,
              fontSize = Constants.largeFontSize,
              color = White,
          ),
      textAlign = TextAlign.Start,
  )
  content()
  if (showHorizontalDivider) {
    HorizontalDivider(modifier.padding(top = Constants.smallPadding))
  }
}
