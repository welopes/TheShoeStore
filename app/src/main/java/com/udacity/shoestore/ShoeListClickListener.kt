package com.udacity.shoestore

import com.udacity.shoestore.models.Shoe

interface ShoeListClickListener {
    fun onViewItemClick(shoe: Shoe)
}