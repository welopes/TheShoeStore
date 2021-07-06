package com.udacity.shoestore

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.btCancel.setOnClickListener {
            hideSoftwareKeyboard()
            findNavController().popBackStack()
        }

        viewModel.isSaved.observe(viewLifecycleOwner, { isSaved ->
            if (isSaved) {
                hideSoftwareKeyboard()
                findNavController().popBackStack()
            }
        })

        return binding.root
    }

    private fun hideSoftwareKeyboard(){
        val inputManager: InputMethodManager  = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken,  InputMethodManager.HIDE_NOT_ALWAYS)
    }

}