package com.mdlicht.zb.simpleurlshortener.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.mdlicht.zb.simpleurlshortener.model.Result
import com.mdlicht.zb.simpleurlshortener.viewholder.HistoryViewHolder

class HistoryRvAdapter: PagedListAdapter<Result, HistoryViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.uid == newItem.uid
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HistoryViewHolder {
        return HistoryViewHolder.newInstance(p0)
    }

    override fun onBindViewHolder(p0: HistoryViewHolder, p1: Int) {
        getItem(p1)?.let { p0.onBindView(p1, it) }
    }
}