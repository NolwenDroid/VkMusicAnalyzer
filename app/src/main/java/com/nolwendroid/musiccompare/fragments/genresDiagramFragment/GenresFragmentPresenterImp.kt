package com.nolwendroid.musiccompare.fragments.genresDiagramFragment

import android.annotation.SuppressLint
import com.nolwendroid.musiccompare.model.AuthResponse
import com.nolwendroid.musiccompare.model.searchResult.Search
import com.nolwendroid.musiccompare.net.Requests
import com.nolwendroid.musiccompare.net.Requests.Companion.AUTH_BODY
import com.nolwendroid.musiccompare.utils.IncrementHashMap
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.*
import javax.inject.Inject

//implement base presenter later
class GenresFragmentPresenterImp @Inject constructor(var requests: Requests) :
    GenresFragmentContract.GenresFragmentPresenter {

    private lateinit var artistsHash: HashMap<String, Int>
    private lateinit var view: GenresFragmentContract.GenresFragmentView

    override fun onGenresDemand(artistsHash: HashMap<String, Int>) {
        authSpotify()
        this.artistsHash = artistsHash;
    }

    private fun authSpotify() {
        view.onProgressStart()
        val config: Observable<AuthResponse> = requests.authSpotify(AUTH_BODY)
        val subscribe = config.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ response ->
                val accessToken = response?.access_token
                println(accessToken)
                searchGenres(accessToken);
            }, { e ->
                println(e.message)
            })
    }

    @SuppressLint("CheckResult")
    private fun searchGenres(accessToken: String?) {
        val genreRequests = ArrayList<Observable<Response<Search>>>()
        val genresMap = IncrementHashMap()
        artistsHash.forEach { it ->
            val request = requests.getGenre("Bearer $accessToken", it.key, "artist")
            genreRequests.add(request)
        }
        val size = genreRequests.size
        var currentStep = 0
        Observable.merge(genreRequests).subscribeOn(Schedulers.newThread())
            .onErrorResumeNext(Observable.empty())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({ response ->
                currentStep++
                view.onProgressUpdate(currentStep, size)
                val responseBody = response.body()
                if (responseBody != null && responseBody.artists.items.isNotEmpty()) {
                    responseBody.artists.items.forEach { it ->
                        val queryParameterValue = response.raw().request.url.queryParameterValue(0)
                        if (it.name == queryParameterValue) {
                            val valueForBand = artistsHash[queryParameterValue]
                            val genres = it.genres
                            genres.forEach {
                                if (valueForBand != null) {
                                    genresMap.putWithIncrement(it, valueForBand)
                                }
                            }
                        }
                    }

                }
            }, { e ->
                println(e.message)
            }, {
                view.onProgressDone()
                val unmodifyWithSort = genresMap.unmodifyWithSort()
                view.onGenresReceived(unmodifyWithSort)
            })
        println(genresMap)
    }

    override fun bindView(baseView: GenresFragmentContract.GenresFragmentView) {
        view = baseView
    }


    override fun unbindView() {
        //todo
    }


}