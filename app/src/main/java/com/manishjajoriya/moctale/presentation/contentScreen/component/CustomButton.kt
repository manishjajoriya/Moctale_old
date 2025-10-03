package com.manishjajoriya.moctale.presentation.contentScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.manishjajoriya.moctale.Constants
import com.manishjajoriya.moctale.ui.theme.Inter
import com.manishjajoriya.moctale.ui.theme.White

@Composable
fun CustomButton(modifier: Modifier = Modifier, title: String, icon: Int, color: Color) {
  Button(
      onClick = {},
      modifier.fillMaxWidth(),
      colors = ButtonDefaults.buttonColors(containerColor = color),
  ) {
    Row(
        Modifier.fillMaxWidth().padding(horizontal = Constants.largePadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(painter = painterResource(icon), contentDescription = "", tint = White)
      Spacer(Modifier.width(Constants.mediumPadding))
      Text(
          text = title,
          style =
              TextStyle(
                  fontFamily = Inter,
                  fontSize = Constants.smallFontSize,
                  color = White,
                  fontWeight = FontWeight.SemiBold,
              ),
      )
    }
  }
}
