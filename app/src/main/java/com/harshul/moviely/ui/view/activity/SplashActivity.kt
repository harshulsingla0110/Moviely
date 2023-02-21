package com.harshul.moviely.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.harshul.moviely.ui.viewmodels.MainViewModel
import com.harshul.moviely.utils.Constants
import com.harshul.moviely.utils.SharedPrefUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        lifecycleScope.launch(Dispatchers.IO) {
            val hitApi = async { mainViewModel.getMoviesFromAPI() }
            hitApi.await()
            //delay(1000)
            val isTrue = SharedPrefUtility.getFromSharedPreference(
                this@SplashActivity,
                Constants.IS_APP_OPENED
            ).toBoolean()

            if (isTrue) {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
            finish()
        }
    }
}