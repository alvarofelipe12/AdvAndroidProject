package com.advandroid.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.advandroid.project.data.Product
import com.advandroid.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
//                R.id.btn_detail -> {
//                    val intent = Intent(this, DetailActivity::class.java)
//                    intent.putExtra(
//                        "EXTRA_ITEM",
//                        Product(
//                            "PS5",
//                            2.00,
//                            "electronics",
//                            "PS5 brand new",
//                            "https://i5.walmartimages.ca/images/Large/283/923/6000202283923.jpg"
//                        )
//                    )
//                    startActivity(intent)
//                }
            }
        }
    }
}