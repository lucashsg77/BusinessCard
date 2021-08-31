package com.example.businesscard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.businesscard.model.BusinessCardRepository

class BusinessCardViewModelFactory(private val businessCardRepository: BusinessCardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom( BusinessCardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return  BusinessCardViewModel(businessCardRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}