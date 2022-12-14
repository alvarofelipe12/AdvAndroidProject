package com.advandroid.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.advandroid.project.data.CartRepository
import com.advandroid.project.data.Product
import com.advandroid.project.databinding.ActivityMainBinding
import com.advandroid.project.fragment.LoginFragmentDirections
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun goToDetail(product: Product, id: Int) {
        val action = ProductFragmentDirections.actionProductFragmentToDetailFragment(
            product
        )
        findNavController(id).navigate(action)
    }


}