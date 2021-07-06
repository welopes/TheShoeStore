package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    private val _isSaved = MutableLiveData(false)
    val isSaved: LiveData<Boolean>
        get() = _isSaved

    private val _isUpdate = MutableLiveData(false)
    val isUpdate: LiveData<Boolean>
        get() = _isUpdate

    fun saveOrUpdateShoe() {
        if (_isUpdate.value!!.not()) {
            _shoes.value?.add(_shoe.value!!)
        }

        _shoe.value = null
        _isSaved.value = true
    }

    fun updateInit(shoe: Shoe) {
        _isSaved.value = false
        _isUpdate.value = true
        _shoe.value = shoe
    }

    fun saveInit() {
        _isSaved.value = false
        _isUpdate.value = false
        _shoe.value = Shoe("", 0.0, "", "")
    }

}