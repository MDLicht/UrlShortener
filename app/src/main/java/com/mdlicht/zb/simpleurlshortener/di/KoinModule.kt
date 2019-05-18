package com.mdlicht.zb.simpleurlshortener.di

import android.arch.persistence.room.Room
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mdlicht.zb.simpleurlshortener.fragment.HistoryFragment
import com.mdlicht.zb.simpleurlshortener.fragment.ShortenerFragment
import com.mdlicht.zb.simpleurlshortener.network.ShortenService
import com.mdlicht.zb.simpleurlshortener.repository.ShortenRepository
import com.mdlicht.zb.simpleurlshortener.viewmodel.HistoryItemViewModel
import com.mdlicht.zb.simpleurlshortener.viewmodel.HistoryViewModel
import com.mdlicht.zb.simpleurlshortener.viewmodel.ShortenViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val request: Request = chain.request().newBuilder()
                    //                            .header("Content-Type", "application/json; charset=UTF-8")
                    //                            .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("X-Naver-Client-Id", getProperty("api_client"))
                    .header("X-Naver-Client-Secret", getProperty("api_secret"))
                    .build()
                chain.proceed(request)
            }
        }.build()
    }

    single {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .baseUrl(getProperty("api_url") as String)
            .build()
            .create(ShortenService::class.java)
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            com.mdlicht.zb.simpleurlshortener.database.Database::class.java,
            "ShortenDatabase"
        ).build()
    }

    single {
        ShortenRepository(get(), get())
    }

    factory {
        ShortenerFragment()
    }

    factory {
        HistoryFragment()
    }

    viewModel {
        ShortenViewModel(get())
    }

    viewModel {
        HistoryViewModel(get())
    }

    viewModel {
        HistoryItemViewModel(get())
    }
}