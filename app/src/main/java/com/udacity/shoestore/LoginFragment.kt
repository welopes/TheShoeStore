package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btCreateLogin.setOnClickListener { login() }
        binding.btLogin.setOnClickListener { login() }

        return binding.root
    }

    private fun login() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }
}