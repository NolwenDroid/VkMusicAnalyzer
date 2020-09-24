package com.nolwendroid.musiccompare.fragments.urlfragment

import com.nolwendroid.musiccompare.net.Requests
import com.nolwendroid.musiccompare.utils.IncrementHashMap
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

//implement base presenter later
class WebViewVkPresenterImp @Inject constructor(var requests: Requests) : WEbViewVkFragmentContract.URLFragmentPresenter {

    private lateinit var view: WEbViewVkFragmentContract.URLFragmentView

    override fun onAudioPageOpened(cookies: String) {
        val config: Observable<ResponseBody> = requests.getAudio(cookies)
        val subscribe = config.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribe { response ->
                parseArtists(response)
            }
    }


    override fun bindView(view: WEbViewVkFragmentContract.URLFragmentView) {
        this.view = view;
    }

    override fun unbindView() {

    }

    private fun parseArtists(response: ResponseBody?) {
        val string = response?.string()
        if (string != null) {
            if (string.contains("ai_artist")) {
                val artistHash = IncrementHashMap()
                val pattern = Pattern.compile(
                    "<span class=\"ai_artist\">(.+?)</span>",
                    Pattern.DOTALL
                );
                val matcher: Matcher = pattern.matcher(string)
                while (matcher.find()) {
                    val artist = matcher.group(1).trim()
                    artistHash.put(artist)
                }
                view.onArtistsReceived(artistHash)

            }
        }
    }
}