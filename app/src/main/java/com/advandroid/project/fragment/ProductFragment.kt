package com.advandroid.project.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.advandroid.project.R
import com.advandroid.project.adapter.ProductAdapter
import com.advandroid.project.api.IAPIResponse
import com.advandroid.project.api.RetrofitInstance
import com.advandroid.project.data.Product
import com.advandroid.project.databinding.FragmentProductBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.s


class ProductFragment : Fragment() {
    val TAG: String = "PRODUCT-FRAGMENT"

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: ProductAdapter
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            Log.d(TAG,"Login as ${currentUser.email}")
        }
        //not loged
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        setHasOptionsMenu(true)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var productList: ArrayList<Product> = ArrayList()
        adapter = ProductAdapter(requireActivity(), productList)
        binding.lvProduct.adapter = adapter
        var api: IAPIResponse = RetrofitInstance.retrofitService         // singleton

        viewLifecycleOwner.lifecycleScope.launch {
            val productListFromAPI: List<Product> = api.getAllProduct()
            productList.clear()
            productList.addAll(productListFromAPI)
            adapter.notifyDataSetChanged()
        }
        binding.itemSearch.addTextChangedListener {
            adapter.filter.filter(it.toString());
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}