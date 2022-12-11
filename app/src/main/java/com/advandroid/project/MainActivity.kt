package com.advandroid.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.advandroid.project.data.Product
import com.advandroid.project.databinding.ActivityMainBinding
import com.advandroid.project.fragment.ProductFragmentDirections
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun goToDetail(product: Product, id: Int) {
        val action = ProductFragmentDirections.actionProductFragmentToDetailFragment(
            product
        )
        findNavController(id).navigate(action)
    }
}