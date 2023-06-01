package com.example.nanopost

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nanopost.databinding.ActivityMainBinding
import com.example.nanopost.presentation.feed.FeedFragment
import com.example.nanopost.presentation.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel: ActivityViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       if (Build.VERSION.SDK_INT in 21..29) {
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        } else if (Build.VERSION.SDK_INT >= 30) {
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        viewModel.getToken()

        val feedFragment = FeedFragment()
        val profileFragment = ProfileFragment()


        navController.addOnDestinationChangedListener { _, dest, _ ->
            when (dest.id) {
                R.id.authFragment, R.id.createPostFragment, R.id.imageFragment -> binding.navView.visibility = GONE
                else ->{
                    binding.navView.visibility = VISIBLE
                    binding.navView.setOnItemSelectedListener {
                        when(it.itemId){
                            R.id.profile -> {
                                setCurrentFragment(profileFragment)
                            }
                            R.id.feed -> {
                                setCurrentFragment(feedFragment)
                            }
                        }
                        true
                    }
                }
            }
        }

        viewModel.tokenLiveData.observe(this) {
            if (it != null) {
                navController.graph.setStartDestination(R.id.feedFragment)
            }
            else {
                navController.graph.setStartDestination(R.id.authFragment)
            }

        }

    }
    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_fragment,fragment)
            commit()
        }
}