package com.udacity.shoestore

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeDetailBinding.inflate(inflater, container, false)

        binding.btCancel.setOnClickListener {
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        binding.btSave.setOnClickListener {
            saveShoe()
        }

        return binding.root
    }

    private fun validate(): Boolean {
        var isValid = true
        if (TextUtils.isEmpty(binding.etName.text)) {
            binding.etName.error = getString(R.string.required_field_error)
            isValid = false
        }

        if (TextUtils.isEmpty(binding.etCompany.text)) {
            binding.etCompany.error = getString(R.string.required_field_error)
            isValid = false
        }

        if (TextUtils.isEmpty(binding.etSize.text)) {
            binding.etSize.error = getString(R.string.required_field_error)
            isValid = false
        }

        return isValid
    }

    private fun saveShoe() {
        if (validate()) {
            val size: Double = binding.etSize.text.toString().toDoubleOrNull() ?: 0.0
            val shoe = Shoe(binding.etName.text.toString(), size,  binding.etCompany.text.toString(), binding.etDescription.text.toString())
            viewModel.saveShoe(shoe)

            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
    }
}