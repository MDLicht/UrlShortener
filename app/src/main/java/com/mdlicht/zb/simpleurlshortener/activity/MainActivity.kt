package com.mdlicht.zb.simpleurlshortener.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mdlicht.zb.simpleurlshortener.R
import com.mdlicht.zb.simpleurlshortener.adapter.MainVpAdapter
import com.mdlicht.zb.simpleurlshortener.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            vpContainer.apply {
                adapter = MainVpAdapter(this@MainActivity, supportFragmentManager)
            }
            tlMenu.apply {
                setupWithViewPager(vpContainer)
            }
            lifecycleOwner = this@MainActivity
        }
    }
}
