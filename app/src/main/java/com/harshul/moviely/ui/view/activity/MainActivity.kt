package com.harshul.moviely.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.harshul.moviely.R
import com.harshul.moviely.data.models.OnBoardingItemModel
import com.harshul.moviely.databinding.ActivityMainBinding
import com.harshul.moviely.ui.adapter.OnBoardingAdapter
import com.harshul.moviely.utils.Constants
import com.harshul.moviely.utils.SharedPrefUtility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SharedPrefUtility.saveToSharedPreference(this, Constants.IS_APP_OPENED, true)

        val list = listOf(
            OnBoardingItemModel(
                R.drawable.banner_database,
                getString(R.string.on_board_title_1),
                getString(R.string.on_board_desc_1)
            ),
            OnBoardingItemModel(
                R.drawable.banner_review,
                getString(R.string.on_board_title_2),
                getString(R.string.on_board_desc_2)
            ),
            OnBoardingItemModel(
                R.drawable.banner_rating,
                getString(R.string.on_board_title_3),
                getString(R.string.on_board_desc_3)
            )
        )

        val adapter = OnBoardingAdapter(list)

        binding.viewPager.adapter = adapter
        binding.dotIndicator.attachTo(binding.viewPager)

        binding.tvSkip.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        binding.buttonProceed.setOnClickListener {
            val viewPager = binding.viewPager
            if (viewPager.currentItem + 1 < list.size) viewPager.currentItem += 1
            else {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

    }
}