package com.nolwendroid.musiccompare.activities

import android.os.Bundle
import com.nolwendroid.musiccompare.di.components.ActivityComponent
import com.nolwendroid.musiccompare.di.components.DaggerActivityComponent
import com.nolwendroid.musiccompare.di.modules.ActivityModule
import com.nolwendroid.musiccompare.fragmentUtils.FragmentReplacer
import javax.inject.Inject

abstract class UIActivity : BaseActivity() {
    var fragmentContainer: Int = 0

    abstract fun setupFragmentContainer(): Int

    abstract fun onInitContainerDone(fragmentReplacer: FragmentReplacer)

    @Inject
    lateinit var fragmentReplacer: FragmentReplacer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentContainer = setupFragmentContainer()
        setupFragmentReplacer()
    }

    private fun setupFragmentReplacer() {
        if (fragmentContainer != 0) {
            val activityComponent: ActivityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(supportFragmentManager, fragmentContainer)).build()
            activityComponent.injectUiActivity(this)
        }
        onInitContainerDone(fragmentReplacer);
    }

}