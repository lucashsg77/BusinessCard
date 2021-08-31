package com.example.businesscard.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessCardRepository(private val dao: BusinessCardDAO) {

    fun cards() = dao.allCards()

    fun insert(businessCard: BusinessCard) = runBlocking{
        launch(Dispatchers.IO){
            dao.insertCard(businessCard)
        }
    }
    fun delete(businessCard: BusinessCard) = runBlocking{
        launch(Dispatchers.IO){
            dao.deleteCard(businessCard)
        }
    }

}