package com.advandroid.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController

import com.advandroid.project.data.CartRepository
import com.advandroid.project.data.Datasource
import com.advandroid.project.data.Product
import com.advandroid.project.databinding.ActivityMainBinding
import com.advandroid.project.fragment.ProductFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var datasource: Datasource
    private lateinit var auth: FirebaseAuth
    private var menuItem:Menu?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        datasource = Datasource.getInstance()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuItem=menu
       menuInflater.inflate(R.menu.menu, menu)
        menuUpdate()
        return super.onCreateOptionsMenu(menu)
    }

    fun menuUpdate() {
        val currentUser = auth.currentUser
        if (currentUser != null&&datasource.currentUser!=null) {
                menuItem!!.findItem(R.id.login).isVisible = false
                menuItem!!.findItem(R.id.logout).isVisible = true
                menuItem!!.findItem(R.id.cart).isVisible = true
                Log.d("LOGIN", "MENU UPDATED,  ${currentUser.email}")
        }
        else if(currentUser != null&&datasource.currentUser==null){
            auth.signOut()
            menuItem!!.findItem(R.id.login).isVisible = true
            menuItem!!.findItem(R.id.logout).isVisible = false
            menuItem!!.findItem(R.id.cart).isVisible = false
            Log.d("LOGOUT", "MENU UPDATED")
        }
        else {
            menuItem!!.findItem(R.id.login).isVisible = true
            menuItem!!.findItem(R.id.logout).isVisible = false
            menuItem!!.findItem(R.id.cart).isVisible = false
            Log.d("LOGOUT", "MENU UPDATED")
        }
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        return when (item.itemId) {
            R.id.login -> {
                //menuUpdate()
                //to login
                val action = ProductFragmentDirections.actionToLoginFragment()
                findNavController(binding.container1.getFragment()).navigate(action)
                return true
            }
            R.id.logout -> {
                auth.signOut()
                datasource.logout()
                //menuUpdate()
                //reload
                val action = ProductFragmentDirections.actionToProductFragment()
                findNavController(binding.container1.getFragment()).navigate(action)
                return true
            }
            R.id.cart -> {
                val action = ProductFragmentDirections.actionToCartFragment()
                findNavController(binding.container1.getFragment()).navigate(action)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun goToDetail(product: Product, id: Int) {
        val action = ProductFragmentDirections.actionProductFragmentToDetailFragment(product)
        findNavController(binding.container1.getFragment()).navigate(action)
    }


}