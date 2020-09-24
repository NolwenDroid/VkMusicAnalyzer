package com.nolwendroid.musiccompare.base

interface BasePresenter<V : BaseView> {
    fun bindView(baseView: V)
    fun unbindView()
}