package com.mdlicht.zb.simpleurlshortener.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View
import com.mdlicht.zb.simpleurlshortener.R
import com.mdlicht.zb.simpleurlshortener.common.showToast
import com.mdlicht.zb.simpleurlshortener.model.Result
import com.mdlicht.zb.simpleurlshortener.repository.ShortenRepository

class HistoryItemViewModel(private val repository: ShortenRepository) : ViewModel() {
    val item: LiveData<Result> = MutableLiveData()

    fun onItemCLick(view: View, shortenUrl: String) {
        val clipboard = view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(view.context.getString(R.string.common_short_url), shortenUrl)
        clipboard.primaryClip = clip
        view.context.let{
            it.showToast("${it.getString(R.string.msg_copy_to_clipboard)}\n$shortenUrl")
        }
    }

    fun onDeleteClick(view: View, item: Result) {
        AlertDialog.Builder(view.context).setTitle(R.string.common_delete).setMessage(R.string.msg_delete_history)
            .setPositiveButton(R.string.common_yes) { di, _ ->
                repository.deleteHistory(item)
                di.dismiss()
            }.setNegativeButton(R.string.common_no) { di, _ ->
                di.dismiss()
            }.show()
    }
}