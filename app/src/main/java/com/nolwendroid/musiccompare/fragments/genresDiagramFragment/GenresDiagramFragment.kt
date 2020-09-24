package com.nolwendroid.musiccompare.fragments.genresDiagramFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nolwendroid.musiccompare.R
import com.nolwendroid.musiccompare.di.components.DaggerFragmentsComponent
import kotlinx.android.synthetic.main.fragment_genres.*
import java.util.*
import javax.inject.Inject

class GenresDiagramFragment : Fragment(), GenresFragmentContract.GenresFragmentView {

    private lateinit var pieChartConfig: PieChartConfig

    @Inject
    lateinit var genresFragmentPresenter: GenresFragmentContract.GenresFragmentPresenter

    companion object {
        const val ARTISTS_HASH = "artists_hashmap"
    }

    private lateinit var artistsHash: java.util.HashMap<String, Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        artistsHash = bundle?.getSerializable(ARTISTS_HASH) as HashMap<String, Int>
        return inflater.inflate(R.layout.fragment_genres, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val build = DaggerFragmentsComponent.builder().build()
        setupChart()
        build.injectGenresFragment(this)
        genresFragmentPresenter.bindView(this)
        genresFragmentPresenter.onGenresDemand(artistsHash)
        super.onActivityCreated(savedInstanceState)
    }

    private fun setupChart() {
        pieChartConfig = PieChartConfig(pie_chart)
    }

    override fun onGenresReceived(genresMap: Map<String, Int>) {
        pieChartConfig.setData(genresMap)
    }

    @SuppressLint("SetTextI18n")
    override fun onProgressUpdate(current: Int, count: Int) {
        progressText.text = "$current/$count"

    }

    override fun onProgressStart() {
        loading_layout.visibility = View.VISIBLE
    }

    override fun onProgressDone() {
        loading_layout.visibility = View.GONE
        pie_chart.visibility = View.VISIBLE
    }


}