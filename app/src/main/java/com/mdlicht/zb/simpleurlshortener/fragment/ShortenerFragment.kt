package com.mdlicht.zb.simpleurlshortener.fragment


import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdlicht.zb.simpleurlshortener.R
import com.mdlicht.zb.simpleurlshortener.databinding.FragmentShortenerBinding
import com.mdlicht.zb.simpleurlshortener.viewmodel.ShortenViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ShortenerFragment : BaseFragment() {
    lateinit var binding: FragmentShortenerBinding

    val vm: ShortenViewModel by viewModel()

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shortener, container, false)
        binding.apply {
            this.vm = this@ShortenerFragment.vm
            lifecycleOwner = this@ShortenerFragment
        }
        return binding.root
    }

    override fun getFragmentTitle(context: Context): String = context.getString(R.string.menu_url_shortener)

    companion object {
        @JvmStatic
        fun newInstance() =
            ShortenerFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
