package com.nolwendroid.musiccompare.net

import com.nolwendroid.musiccompare.model.AuthResponse
import com.nolwendroid.musiccompare.model.searchResult.Search
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface Requests {
    companion object {
        const val AUTH_BODY = "client_credentials"
    }

    @GET("https://m.vk.com/audio")
    fun getAudio(@Header("Cookie") cookies: String): Observable<ResponseBody>

    @FormUrlEncoded
    @Headers(
        "Authorization: Basic ZjU1MzRiYWRlNzQyNDUyNDk2N2RlMGRiYTNiMzRlMTU6YTUxZWUyZTVmNjhmNGJiZmJjZmM0N2QwZGM5NWQzMzQ="
    )
    @POST("https://accounts.spotify.com/api/token")
    fun authSpotify(@Field("grant_type") grantType: String): Observable<AuthResponse>

    @GET("https://api.spotify.com/v1/search")
    fun getGenre(
        @Header("Authorization") token: String?,
        @Query("q") searchString: String,
        @Query("type") type: String
    ): Observable<Response<Search>>
}