package com.nolwendroid.musiccompare.activities

import android.os.Bundle
import com.nolwendroid.musiccompare.R
import com.nolwendroid.musiccompare.fragmentUtils.FragmentReplacer
import com.nolwendroid.musiccompare.fragments.genresDiagramFragment.GenresDiagramFragment
import com.nolwendroid.musiccompare.fragments.urlfragment.WebViewVkFragment
import java.util.*

class MainActivity : UIActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setupFragmentContainer(): Int {
        return R.id.container
    }

    override fun onInitContainerDone(fragmentReplacer: FragmentReplacer) {
        this.fragmentReplacer = fragmentReplacer
        openUrlFragment()
    }

    private fun openUrlFragment() {
        fragmentReplacer.replaceFragmentWith(WebViewVkFragment(), false, null)
    }

    fun showDiagramFragment(artists: HashMap<String, Int>) {
        val bundle = Bundle()
        bundle.putSerializable(GenresDiagramFragment.ARTISTS_HASH, artists)
        fragmentReplacer.replaceFragmentWith(GenresDiagramFragment(), true, bundle)
    }
}