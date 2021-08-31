package com.example.businesscard.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusinessCard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0 ,
    val name: String ,
    val phone: String ,
    val email: String ,
    val company: String ,
    val color: Int
)