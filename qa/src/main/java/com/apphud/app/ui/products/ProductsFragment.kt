package com.apphud.app.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apphud.app.R
import com.apphud.app.databinding.FragmentProductsBinding
import com.apphud.sdk.Apphud


class ProductsFragment : Fragment() {

    val args: ProductsFragmentArgs by navArgs()
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var viewAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productsViewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewAdapter = ProductsAdapter(productsViewModel, context)
        viewAdapter.selectProduct = { product ->
            activity?.let{
                Apphud.purchase(it, product){ result ->
                    result.error?.let{ err->
                        Toast.makeText(activity, err.message, Toast.LENGTH_SHORT).show()
                    }?: run{
                        Toast.makeText(activity, R.string.success, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        val recyclerView: RecyclerView = binding.productsList
        recyclerView.layoutManager = GridLayoutManager(activity,2)
        recyclerView.apply {
            adapter = viewAdapter
        }
        updateData(args.paywallId)

        val paywall =  Apphud.paywalls().find { it.id == args.paywallId }
        Apphud.paywallShown(paywall)

        return root
    }

    private fun updateData(pywallId: String){
        productsViewModel.updateData(pywallId)
        viewAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        val paywall =  Apphud.paywalls().find { it.id == args.paywallId }
        Apphud.paywallClosed(paywall)
    }
}