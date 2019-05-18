package com.mdlicht.zb.simpleurlshortener.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.mdlicht.zb.simpleurlshortener.database.Database
import com.mdlicht.zb.simpleurlshortener.model.Result
import com.mdlicht.zb.simpleurlshortener.network.ShortenService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShortenRepository(private val service: ShortenService, private val db: Database) {
    val result: MutableLiveData<String> = MutableLiveData()

    fun shortenUrl(url: String) {
        service.shortenUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.result?.let { r ->
                    result.value = r.url
                    insertHistory(r)
                }
            }, {
                result.value = null
            })
    }

    fun insertHistory(result: Result) {
        Single.just(result)
            .map {
                db.resultDao().insertHistory(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {
                it.printStackTrace()
            })
    }

    fun deleteHistory(result: Result) {
        Single.just(result)
            .map {
                db.resultDao().deleteHistory(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {
                it.printStackTrace()
            })
    }

    fun getAllHistory(): LiveData<List<Result>> {
        return db.resultDao().getAllHistory()
    }

    fun getPagedHistory(): DataSource.Factory<Int, Result> {
        return db.resultDao().getPagedHistory()
    }
}