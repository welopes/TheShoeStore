package com.udacity.shoestore

import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    private lateinit var binding: FragmentShoeListBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.fabDetail.setOnClickListener {
            viewModel.saveInit()
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        viewModel.shoes.observe(viewLifecycleOwner, { shoes ->
            if (shoes.isEmpty()) {
                val tvEmpty = TextView(context)
                tvEmpty.text = getString(R.string.shoe_list_empty)
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                tvEmpty.layoutParams = params
                params.topMargin = 32
                tvEmpty.gravity = Gravity.CENTER

                binding.llShoes.addView(tvEmpty)
            } else {
                shoes.forEach { shoe -> addView(shoe) }
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun addView(shoe: Shoe) {
        val shoeItemBinding = ItemShoeBinding.inflate(
            layoutInflater,
            null,
            false
        )
        shoeItemBinding.shoe = shoe
        binding.llShoes.addView(shoeItemBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}