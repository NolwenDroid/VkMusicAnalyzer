package com.nolwendroid.musiccompare.fragments.urlfragment

import com.nolwendroid.musiccompare.base.BasePresenter
import com.nolwendroid.musiccompare.base.BaseView

interface WEbViewVkFragmentContract {

    interface URLFragmentView : BaseView {
        fun onArtistsReceived(artists: HashMap<String, Int>)
    }

    interface URLFragmentPresenter : BasePresenter<URLFragmentView> {
        fun onAudioPageOpened(cookies: String)
    }
}