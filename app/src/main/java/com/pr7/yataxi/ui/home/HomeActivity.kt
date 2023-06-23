package com.pr7.yataxi.ui.home

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.pr7.kotlin_recyclerview_zoom_center.CenterZoomLayoutManager
import com.pr7.yataxi.CarAdapter
import com.pr7.yataxi.MyAdapter
import com.pr7.yataxi.R
import com.pr7.yataxi.databinding.ActivityHomeBinding
import com.pr7.yataxi.ui.ChooseActivity
import com.pr7.yataxi.ui.SplashActivity
import eightbitlab.com.blurview.RenderEffectBlur

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Transparent statusbar color
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val array= arrayOf(
            R.drawable.samarqand,
            R.drawable.tashkent,
            R.drawable.andijan,
            R.drawable.fergane,
            )
        val arrayname= arrayOf(
            "Samarqand",
            "Toshkent",
            "Andijon",
            "Farg`ona",
        )
        val arraycar= arrayOf(
            R.drawable.malibu,
            R.drawable.cobalt_red,
            R.drawable.captiva,
            R.drawable.nexia,
            R.drawable.matiz,
            R.drawable.damas,
            R.drawable.nexia3,
            R.drawable.tahoe,
            R.drawable.equinox,
            R.drawable.lacetti,
            R.drawable.tracker,
            R.drawable.gentra,
            R.drawable.traverse,
        )
        binding.recyclerviewcars.apply {
            layoutManager=GridLayoutManager(this@HomeActivity,3)
            val caradapter=CarAdapter(this@HomeActivity,arraycar)
            adapter=caradapter
        }
        binding.buttongo.setOnClickListener {
            startActivity(Intent(this@HomeActivity,SplashActivity::class.java))
        }
        binding.cardviewperson.setOnClickListener {
            startActivity(Intent(this@HomeActivity, ChooseActivity::class.java))
        }

        binding.apply {
            val radius=20f
            val decorView = window.decorView
            val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
            val windowBackground: Drawable = decorView.getBackground()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                blurviewprofile.setupWith(rootView, RenderEffectBlur()) // or RenderEffectBlur
                    .setFrameClearDrawable(windowBackground) // Optional
                    .setBlurRadius(radius)
                blurviewlogo.setupWith(rootView, RenderEffectBlur()) // or RenderEffectBlur
                    .setFrameClearDrawable(windowBackground) // Optional
                    .setBlurRadius(radius)
            }

            binding.recyclerview1.apply {
                val snapHelper=LinearSnapHelper()
                snapHelper.attachToRecyclerView(this)
                val linearlayoutManager=CenterZoomLayoutManager(this@HomeActivity,RecyclerView.HORIZONTAL, false)
                layoutManager=linearlayoutManager
                val myAdapter=MyAdapter(this@HomeActivity,array,arrayname)
                adapter=myAdapter
                setPadding(120,0,120,0)
                binding.recyclerview1.smoothScrollToPosition(1)
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.recyclerview1.smoothScrollToPosition(1)
                }, 200)
                binding.arIndicator.attachTo(this,false)
                //binding.arIndicator.numberOfIndicators=3
            }



        }
    }
}