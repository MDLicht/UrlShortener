package com.mdlicht.zb.simpleurlshortener.viewholder

import android.databinding.DataBindingUtil
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdlicht.zb.simpleurlshortener.R
import com.mdlicht.zb.simpleurlshortener.databinding.ItemHistoryBinding
import com.mdlicht.zb.simpleurlshortener.model.Result
import com.mdlicht.zb.simpleurlshortener.repository.ShortenRepository
import com.mdlicht.zb.simpleurlshortener.viewmodel.HistoryItemViewModel
import com.mdlicht.zb.simpleurlshortener.viewmodel.HistoryViewModel
import org.koin.android.viewmodel.getViewModel
import org.koin.android.viewmodel.injectViewModel
import org.koin.core.context.GlobalContext.get
import org.koin.core.parameter.parametersOf

class HistoryViewHolder private constructor(itemView: View) : BaseViewHolder<Result>(itemView) {
    var binding: ItemHistoryBinding = DataBindingUtil.bind(itemView)!!
    private val vm: HistoryItemViewModel by get().koin.inject()

    companion object {
        fun newInstance(parent: ViewGroup): HistoryViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
            return HistoryViewHolder(view)
        }
    }

    override fun onBindView(position: Int, item: Result) {
        binding.apply {
            this.item = item
            this.vm = this@HistoryViewHolder.vm
        }
    }
}