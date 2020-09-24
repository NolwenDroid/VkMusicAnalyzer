package com.nolwendroid.musiccompare.fragmentUtils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class FragmentReplacerImpl(var fragmentManager: FragmentManager, private val containerId: Int) :
    FragmentReplacer {

    override fun replaceFragmentWith(
        fragment: Fragment?,
        needAddToBackstack: Boolean,
        args: Bundle?
    ) {
        val fragmentTag = fragment?.javaClass?.simpleName
        if (args != null) {
            fragment!!.arguments = args
        }
        val fragmentTransaction: FragmentTransaction =
            fragmentManager.beginTransaction()
        if (needAddToBackstack) {
            fragmentTransaction.addToBackStack(fragmentTag)
        }
        if (fragment != null) {
            fragmentTransaction.replace(
                containerId,
                fragment,
                fragmentTag
            ).commit()
        }
    }

    override fun clearFragmentBackStack() {
        fragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    //rewrite to readable
    override fun isTypicalBack(): Boolean {
        val isTypical = (fragmentManager.backStackEntryCount == 0)
        if (!isTypical) fragmentManager.popBackStack()
        return isTypical
    }
}