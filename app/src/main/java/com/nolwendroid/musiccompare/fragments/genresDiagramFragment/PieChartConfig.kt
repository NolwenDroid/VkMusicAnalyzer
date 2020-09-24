package com.nolwendroid.musiccompare.fragments.genresDiagramFragment

import android.graphics.Color
import android.graphics.Typeface
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*

class PieChartConfig(val pie_chart: PieChart) {

    init {
        //pie_chart.setUsePercentValues(true)
        pie_chart.getDescription().setEnabled(false)
        pie_chart.setExtraOffsets(5f, 10f, 5f, 5f)
        pie_chart.setDragDecelerationFrictionCoef(0.95f)
        pie_chart.setDrawHoleEnabled(true)
        pie_chart.setHoleColor(Color.WHITE)
        pie_chart.setTransparentCircleColor(Color.WHITE)
        pie_chart.setTransparentCircleAlpha(110)
        pie_chart.setHoleRadius(0f)
        pie_chart.setTransparentCircleRadius(35f)
        pie_chart.setDrawCenterText(true)
        pie_chart.setRotationAngle(0f)
        pie_chart.setRotationEnabled(true)
        pie_chart.setHighlightPerTapEnabled(true)
        pie_chart.getLegend().setEnabled(false);
    }

    fun setData(genresMap: Map<String, Int>) {
        val entries = ArrayList<PieEntry>()
        var count = 6
        run breaker@ {
            genresMap.forEach{
                entries.add(
                    PieEntry(it.value.toFloat(), it.key, "")
                )
                if (entries.size == count) {
                    return@breaker
                }
            }
        }
        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f
        val colors = ArrayList<Int>()
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        colors.add(ColorTemplate.getHoloBlue())
        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        data.setValueTypeface(Typeface.DEFAULT)
        pie_chart.setData(data)
        pie_chart.highlightValues(null)
        pie_chart.invalidate()
    }
}