package com.nolwendroid.musiccompare.di.modules

import com.nolwendroid.musiccompare.fragments.genresDiagramFragment.GenresFragmentContract
import com.nolwendroid.musiccompare.fragments.genresDiagramFragment.GenresFragmentPresenterImp
import com.nolwendroid.musiccompare.fragments.urlfragment.WEbViewVkFragmentContract.URLFragmentPresenter
import com.nolwendroid.musiccompare.fragments.urlfragment.WebViewVkPresenterImp
import dagger.Module
import dagger.Provides

/**
 * Created by Nolwe on 07.03.2018.
 */
@Module(includes = [NetworkModule::class])
class FragmentsModule {
    @Provides
    fun getWebViewVkFragmentPresenter(webViewVkFragmentPresenter: WebViewVkPresenterImp): URLFragmentPresenter {
        return webViewVkFragmentPresenter
    }
    @Provides
    fun getGenresFragmentPresenter(genresPresenter: GenresFragmentPresenterImp): GenresFragmentContract.GenresFragmentPresenter {
        return genresPresenter
    }
}