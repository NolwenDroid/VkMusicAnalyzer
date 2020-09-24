package com.nolwendroid.musiccompare.di.components

import com.nolwendroid.musiccompare.di.modules.FragmentsModule
import com.nolwendroid.musiccompare.fragments.genresDiagramFragment.GenresDiagramFragment
import com.nolwendroid.musiccompare.fragments.urlfragment.WebViewVkFragment
import dagger.Component

/**
 * Created by Nolwe on 05.03.2018.
 */
@Component(modules = [FragmentsModule::class])
interface FragmentsComponent {
    fun injectWebViewVkFragment(urlVkFragment: WebViewVkFragment?)
    fun injectGenresFragment(genresFragment: GenresDiagramFragment?)
}