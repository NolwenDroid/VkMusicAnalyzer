package com.nolwendroid.musiccompare.fragments.urlfragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nolwendroid.musiccompare.R
import com.nolwendroid.musiccompare.activities.MainActivity
import com.nolwendroid.musiccompare.di.components.DaggerFragmentsComponent
import kotlinx.android.synthetic.main.fragment_url.*
import javax.inject.Inject

class WebViewVkFragment : Fragment(), WEbViewVkFragmentContract.URLFragmentView {

    @Inject
    lateinit var uRLFragmentPresenter: WEbViewVkFragmentContract.URLFragmentPresenter
    private lateinit var webViewClient: WebViewClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_url, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val build = DaggerFragmentsComponent.builder().build()
        build.injectWebViewVkFragment(this)
        uRLFragmentPresenter.bindView(this)
        //todo logic move to webClient class
        webViewClient = object : WebViewClient() {
            //simple logic could stay here
            override fun onPageFinished(view: WebView?, url: String?) {
                if (url != null) {
                    if (url.contains("vk.com/audio")) {
                        val cookieManager: CookieManager = CookieManager.getInstance()
                        val cookie: String = cookieManager.getCookie(url)
                        uRLFragmentPresenter.onAudioPageOpened(cookie)
                    }
                    if (url.contains("vk.com/feed")) {
                        search_button.visibility = View.VISIBLE
                    }
                }
                super.onPageFinished(view, url)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                if (url != null) {
                    if (url.contains("vk.com/audio") || url.contains("login")) {
                        search_button.visibility = View.GONE
                    }
                }
                super.onPageStarted(view, url, favicon)
            }
        }
        search_web_view.webViewClient = webViewClient
        search_web_view.loadUrl("https://vk.com")
        search_button.setOnClickListener {searchClicked()}
    }

    private fun searchClicked() {
       search_web_view.loadUrl("https://vk.com/audio")
    }

    override fun onArtistsReceived(artists: HashMap<String, Int>) {
        var message:String
        if (artists.isNotEmpty()) {
            message = "Список аудизаписей получен"
        } else {
            message = "Список записей пуст или произошла ошибка"
        }
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        //should create interface for more flexability
        val mainActivity = activity as MainActivity
        mainActivity.showDiagramFragment(artists)
    }
}