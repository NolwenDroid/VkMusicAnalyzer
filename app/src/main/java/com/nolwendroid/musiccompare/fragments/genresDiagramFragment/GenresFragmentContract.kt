package com.nolwendroid.musiccompare.fragments.genresDiagramFragment

import com.nolwendroid.musiccompare.base.BasePresenter
import com.nolwendroid.musiccompare.base.BaseView
import java.util.*

interface GenresFragmentContract {

    interface GenresFragmentView : BaseView {
        fun onGenresReceived(genresMap: Map<String, Int>)
        fun onProgressUpdate(current: Int,count: Int )
        fun onProgressStart()
        fun onProgressDone()
    }

    interface GenresFragmentPresenter : BasePresenter<GenresFragmentView> {
        fun onGenresDemand(artistsHash: HashMap<String, Int>)
    }
}