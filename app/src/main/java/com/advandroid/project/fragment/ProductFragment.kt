package com.advandroid.project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.advandroid.project.adapter.ProductAdapter
import com.advandroid.project.api.IAPIResponse
import com.advandroid.project.api.RetrofitInstance
import com.advandroid.project.data.Product
import com.advandroid.project.databinding.FragmentProductBinding
import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.s


class ProductFragment : Fragment() {
    val TAG: String = "PRODUCT-FRAGMENT"

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: ProductAdapter

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