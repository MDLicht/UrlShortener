package com.mdlicht.zb.simpleurlshortener.fragment

import android.content.Context
import android.support.v4.app.Fragment

abstract class BaseFragment: Fragment() {
    abstract fun getFragmentTitle(context: Context): String
}