package com.oscar.test.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oscar.test.R
import com.oscar.test.databinding.LoginFragmentBinding
import com.oscar.test.network.Resource
import com.oscar.test.ui.base.BaseFragment

class LoginFragment : BaseFragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  LoginFragmentBinding.inflate(inflater, container, false)

        binding.login.setOnClickListener {
            login()
        }

        return binding.root
    }

    private fun login() {
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()

        binding.username.error = null
        binding.password.error = null

        if (username.isEmpty()) {
            binding.username.error = resources.getString(R.string.error_username_empty)
            return
        }

        if (password.isEmpty()) {
            binding.password.error =resources.getString(R.string.error_password_empty)
            return
        }

        viewModel.login(username, password).observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.login.isEnabled = false
                    binding.login.setText(R.string.loading)
                    binding.message.text = ""
                }
                Resource.Status.SUCCESS -> {
                    loginComplete()
                }
                Resource.Status.ERROR -> {
                    binding.login.isEnabled = true
                    binding.login.setText(R.string.login)

                    binding.message.text = resources.getString(R.string.error_login)
                }
            }
        }
    }

    private fun loginComplete() {
        val action = LoginFragmentDirections.actionLoginFragmentToRouteFragment()
        findNavController().navigate(action)
    }

}