package com.nolwendroid.musiccompare.fragmentUtils


import android.os.Bundle
import androidx.fragment.app.Fragment

interface FragmentReplacer {

    fun replaceFragmentWith(fragment: Fragment?, needAddToBackstack: Boolean, args: Bundle?)

    fun clearFragmentBackStack()

    fun isTypicalBack(): Boolean
}