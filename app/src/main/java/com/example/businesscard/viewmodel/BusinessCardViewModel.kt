package com.example.businesscard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.businesscard.model.BusinessCard
import com.example.businesscard.model.BusinessCardRepository

class BusinessCardViewModel(private val businessCardRepository: BusinessCardRepository): ViewModel() {

    val cards = businessCardRepository.cards().asLiveData()

    fun insert(businessCard: BusinessCard){
        businessCardRepository.insert(businessCard)
    }

    fun delete(businessCard: BusinessCard){
        businessCardRepository.delete(businessCard)
    }
}