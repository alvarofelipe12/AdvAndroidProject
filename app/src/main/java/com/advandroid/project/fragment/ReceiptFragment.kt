package com.advandroid.project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.advandroid.project.adapter.ReceiptRecyclerViewAdapter
import com.advandroid.project.data.Datasource
import com.advandroid.project.databinding.FragmentReceiptListBinding

class ReceiptFragment : Fragment() {
    lateinit var dataSource: Datasource
    private var _binding: FragmentReceiptListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReceiptListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataSource = Datasource.getInstance()
        binding.rvReceipt.adapter = ReceiptRecyclerViewAdapter(dataSource.getCart().toList())

        binding.tvSubtotal.text = "Subtotal: $${dataSource.getTotal()}"
        val total = (dataSource.getTotal() * 0.13) + dataSource.getTotal()
        binding.tvTotal.text = "Total: $${String.format("%.2f", total).toDouble()}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}