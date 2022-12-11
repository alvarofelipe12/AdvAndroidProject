package com.advandroid.project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.advandroid.project.R
import com.advandroid.project.data.Product
import com.advandroid.project.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment(), View.OnClickListener {

    private var qty: Int = 1

    private val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.btnDecreaseQty.setOnClickListener(this)
        binding.btnIncreaseQty.setOnClickListener(this)
        binding.tvQty.setText("$qty")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product: Product = args.productData
        binding.tvTitle.setText(product.title)
        binding.tvPrice.setText("$${product.price}")
        binding.tvDescription.setText(product.description)
        Picasso.get().load(product.image).into(binding.ivProduct)
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.btn_decrease_qty -> {
                    if (qty > 1) {
                        qty--
                        binding.tvQty.setText("$qty")
                    }
                }
                R.id.btn_increase_qty -> {
                    qty++
                    binding.tvQty.setText("$qty")
                }
            }
        }
    }
}