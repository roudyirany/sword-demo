package com.sword.demo.ui.activity.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sword.demo.R
import com.sword.demo.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.title_details)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}