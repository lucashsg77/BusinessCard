package com.example.businesscard

import android.app.Application
import com.example.businesscard.model.BusinessCardDatabase
import com.example.businesscard.model.BusinessCardRepository

class App: Application() {
    val database by lazy { BusinessCardDatabase.database(this)}
    val repository by lazy{ BusinessCardRepository(database.businessCardDao()) }
}