package com.nolwendroid.musiccompare.di.modules


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nolwendroid.musiccompare.BuildConfig
import com.nolwendroid.musiccompare.ConstantsNet
import com.nolwendroid.musiccompare.net.Requests
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Nolwe on 26.04.2017.
 */

@Module
class NetworkModule {
    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    fun retrofit(okHttpClient: OkHttpClient?, gson: Gson?): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Provides
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(ConstantsNet.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(ConstantsNet.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(ConstantsNet.READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun getRequests(retrofit: Retrofit): Requests {
        return retrofit.create(Requests::class.java)
    }
}