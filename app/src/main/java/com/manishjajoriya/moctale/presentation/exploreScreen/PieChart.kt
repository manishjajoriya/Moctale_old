package com.manishjajoriya.moctale.presentation.exploreScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.toColorInt
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.manishjajoriya.moctale.domain.model.content.Genre
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

@Composable
fun GenrePieChart(modifier: Modifier = Modifier, genres: List<Genre>) {
  AndroidView(
    modifier = modifier,
    factory = { context ->
      PieChart(context).apply {
        setUsePercentValues(false)
        description.isEnabled = false
        isDrawHoleEnabled = true
        val bandWidth = 20f
        val outerRadius = 100f
        holeRadius = outerRadius - bandWidth
        transparentCircleRadius = outerRadius
        setHoleColor(android.graphics.Color.BLACK)

        setDrawEntryLabels(false)
        legend.isEnabled = false
        setExtraOffsets(0f, 0f, 0f, 0f)
        isRotationEnabled = false
        setDrawSlicesUnderHole(false)
        setDrawCenterText(true)
        setCenterTextSize(16f)
        setCenterTextColor(android.graphics.Color.WHITE)

        // Click listener
        setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
          override fun onValueSelected(e: Entry?, h: Highlight?) {
            if (e is PieEntry) {
              val name = e.label
              val percentage = e.value
              centerText = "$name\n${percentage.toInt()}%" // Show in center
            }
          }

          override fun onNothingSelected() {
            centerText = ""
          }
        })
      }
    },
    update = { chart ->
      val entries = genres.map { PieEntry(it.percentage.toFloat(), it.name) }
      val colors = genres.map { it.color.toColorInt() }

      val dataSet = PieDataSet(entries, "").apply {
        this.colors = colors
        sliceSpace = 0f
        setDrawValues(false)
        selectionShift = 5f
      }

      chart.data = PieData(dataSet)
      chart.invalidate()
    },
  )
}

