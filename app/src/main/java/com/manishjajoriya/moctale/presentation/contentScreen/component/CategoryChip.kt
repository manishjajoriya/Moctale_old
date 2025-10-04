package com.manishjajoriya.moctale.presentation.contentScreen.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.ui.theme.Inter
import com.manishjajoriya.moctale.ui.theme.White

@Composable
fun CategoryChip(genre: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
  TextButton(
      onClick = onClick,
      modifier = modifier,
      shape = RoundedCornerShape(50),
      colors =
          ButtonDefaults.textButtonColors(containerColor = Color(0xFF1F1F1F), contentColor = White),
      contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
  ) {
    Text(
        text = genre,
        style =
            TextStyle(
                fontFamily = Inter,
                fontSize = Constants.extraSmallFontSize,
                fontWeight = FontWeight.SemiBold,
            ),
    )
  }
}
