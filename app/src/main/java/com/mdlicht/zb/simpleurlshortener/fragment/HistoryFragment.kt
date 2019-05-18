package com.mdlicht.zb.simpleurlshortener.fragment


import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdlicht.zb.simpleurlshortener.R
import com.mdlicht.zb.simpleurlshortener.adapter.HistoryRvAdapter
import com.mdlicht.zb.simpleurlshortener.databinding.FragmentHistoryBinding
import com.mdlicht.zb.simpleurlshortener.viewmodel.HistoryViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment() {
    lateinit var binding: FragmentHistoryBinding

    val vm: HistoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        binding.apply {
            this.vm = this@HistoryFragment.vm
            this.vm?.apply {
                history.observe(this@HistoryFragment, Observer {
                    (rvHistory.adapter as HistoryRvAdapter).submitList(it)
                })
            }
            rvHistory.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = HistoryRvAdapter()
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
            lifecycleOwner = this@HistoryFragment
        }
        return binding.root
    }

    override fun getFragmentTitle(context: Context): String = context.getString(R.string.menu_history)

    companion object {
        @JvmStatic
        fun newInstance() =
            HistoryFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
