package com.oscar.test.ui.route

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.oscar.test.R
import com.oscar.test.databinding.RouteFragmentBinding
import com.oscar.test.model.Route
import com.oscar.test.network.Resource
import com.oscar.test.ui.base.BaseFragment

class RouteFragment : BaseFragment() {

    private val viewModel: RouteViewModel by viewModels()
    private lateinit var binding: RouteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RouteFragmentBinding.inflate(inflater, container, false)

        binding.search.setOnClickListener {
            search()
        }

        return binding.root
    }

    private fun search() {
        val plate = binding.plate.text.toString()

        binding.plate.error = null

        if (plate.isEmpty()) {
            binding.plate.error = resources.getString(R.string.error_plate_empty)
            return
        }

        if (plate.length != 6) {
            binding.plate.error =resources.getString(R.string.error_plate_format)
            return
        }

        viewModel.getRoute(plate).observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.search.isEnabled = false
                    binding.route.visibility = View.GONE
                    binding.message.text = resources.getText(R.string.searching)
                }
                Resource.Status.SUCCESS -> {
                    binding.message.text = ""
                    binding.search.isEnabled = true
                    showRoute(it.data!!)
                }
                Resource.Status.ERROR -> {
                    binding.search.isEnabled = true
                    binding.message.text = resources.getString(R.string.error_not_route_found)
                }
            }
        }
    }

    private fun showRoute(route: Route) {
        binding.route.visibility = View.VISIBLE
        binding.name.text = route.name
        binding.address.text = route.address
        binding.phone.text = route.phone
        binding.time.text = route.time
    }

}