package com.advandroid.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.advandroid.project.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("asd", "drrtsjdkfkjhjhfddfdghfchfhfhf ${intent.getSerializableExtra("EXTRA_ITEM")}")
//        intent.getSerializableExtra("EXTRA_ITEM")
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when(p0.id) {

            }
        }
    }
}