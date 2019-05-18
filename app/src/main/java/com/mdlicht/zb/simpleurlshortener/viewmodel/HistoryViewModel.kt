package com.mdlicht.zb.simpleurlshortener.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.mdlicht.zb.simpleurlshortener.model.Result
import com.mdlicht.zb.simpleurlshortener.repository.ShortenRepository

class HistoryViewModel(repository: ShortenRepository) : ViewModel() {
    val history: LiveData<PagedList<Result>> = LivePagedListBuilder<Int, Result>(
        repository.getPagedHistory(),
        PagedList.Config.Builder().setInitialLoadSizeHint(10).setEnablePlaceholders(false).setPageSize(10).build()
    ).build()
}