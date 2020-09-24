package com.nolwendroid.musiccompare.di.modules

import androidx.fragment.app.FragmentManager
import com.nolwendroid.musiccompare.fragmentUtils.FragmentReplacer
import com.nolwendroid.musiccompare.fragmentUtils.FragmentReplacerImpl
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var fm: FragmentManager, private val container: Int) {

    @Provides
    fun provideFragmentReplacer(): FragmentReplacer {
        return FragmentReplacerImpl(fm, container)
    }


}