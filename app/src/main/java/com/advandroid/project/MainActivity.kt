package com.advandroid.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.advandroid.project.data.CartRepository
import com.advandroid.project.data.Product
import com.advandroid.project.databinding.ActivityMainBinding
import com.advandroid.project.fragment.ProductFragmentDirections

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var cartRepository: CartRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cartRepository = CartRepository(this)
        cartRepository.getCart()
    }

    fun goToDetail(product: Product, id: Int) {
        val action = ProductFragmentDirections.actionProductFragmentToDetailFragment(
            product
        )
        findNavController(id).navigate(action)
    }
}