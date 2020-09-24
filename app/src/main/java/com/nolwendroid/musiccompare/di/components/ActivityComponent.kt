package com.nolwendroid.musiccompare.di.components

import com.nolwendroid.musiccompare.activities.UIActivity
import com.nolwendroid.musiccompare.di.modules.ActivityModule
import dagger.Component

@Component (modules = [ActivityModule::class])
interface ActivityComponent {
    fun injectUiActivity (activity: UIActivity)
}