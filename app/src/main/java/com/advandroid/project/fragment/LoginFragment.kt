package com.advandroid.project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController


import com.advandroid.project.R

import com.advandroid.project.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment(), View.OnClickListener {
    val TAG: String = "LOGIN-FRAGMENT"
    private lateinit var auth: FirebaseAuth


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            //smth on ui as loged in
        }
        //not loged
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginButton.setOnClickListener(this)
        binding.registerButton.setOnClickListener(this)
        return binding.root
    }

    fun login(){
        try{
            val email=binding.userEmail.text.toString()
            val password=binding.userPassword.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this.requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                        val G=user!!.uid
                        Log.d(TAG,"Token ${G}")
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this.requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
        catch (ex: Exception) {
            Log.e(TAG, "login: $ex")
        }
    }

    fun register(){
        try{
            val email=binding.userEmail.text.toString()
            val password=binding.userPassword.text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this.requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this.requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
        catch (ex: Exception) {
            Log.e(TAG, "login: $ex")
        }
    }

    fun logout(){
        updateUI(null)
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    override fun onClick(p0: View?){
        if(p0!=null){
            when(p0.id){
                R.id.loginButton->{
                    //login varif
                   login()
                    val action = LoginFragmentDirections.actionLoginFragmentToProductFragment()
                    findNavController().navigate(action)
                }
                R.id.registerButton->{
                    //register varif
                    register()
                }
            }
        }

    }

}