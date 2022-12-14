package com.advandroid.project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.advandroid.project.adapter.CartAdapter
import com.advandroid.project.data.Datasource
import com.advandroid.project.data.SelectedProduct
import com.advandroid.project.databinding.FragmentCartBinding
import com.advandroid.project.databinding.FragmentProductBinding

class CartFragment : Fragment() {
    val TAG: String = "CART-FRAGMENT"

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    lateinit var dataSource: Datasource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataSource = Datasource.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productList: MutableList<SelectedProduct> = dataSource.getCart()
        val adapter = CartAdapter(requireActivity(), productList)
        binding.lvProduct.adapter = adapter
        binding.btnPayNow.text = "Pay now ${dataSource.getTotal()}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}