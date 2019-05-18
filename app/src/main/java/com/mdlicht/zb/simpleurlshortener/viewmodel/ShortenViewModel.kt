package com.mdlicht.zb.simpleurlshortener.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.mdlicht.zb.simpleurlshortener.R
import com.mdlicht.zb.simpleurlshortener.common.showToast
import com.mdlicht.zb.simpleurlshortener.repository.ShortenRepository

class ShortenViewModel(private val repository: ShortenRepository): ViewModel() {
    val url: MutableLiveData<String> = MutableLiveData()
    var shortenUrl: MutableLiveData<String> = repository.result

    fun shortenUrl(url: String) {
        repository.shortenUrl(url)
    }

    fun copyToClipboard(view: View, shortenUrl: String?) {
        if(TextUtils.isEmpty(shortenUrl)) {
            view.context.let {
                it.showToast(it.getString(R.string.msg_empty_short_url))
            }
        } else {
            val clipboard = view.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(view.context.getString(R.string.common_short_url), shortenUrl)
            clipboard.primaryClip = clip
            view.context.let{
                it.showToast(it.getString(R.string.msg_copy_to_clipboard))
            }
        }
    }

    fun share(view: View, shortenUrl: String?) {
        if(TextUtils.isEmpty(shortenUrl)) {
            view.context.let {
                it.showToast(it.getString(R.string.msg_empty_short_url))
            }
        } else {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shortenUrl)
                type = "text/plain"
            }
            view.context.startActivity(intent)
        }
    }
}