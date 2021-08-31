package com.example.businesscard.model

import androidx.room.*
import com.example.businesscard.model.BusinessCard
import kotlinx.coroutines.flow.Flow

@Dao
interface BusinessCardDAO {
    @Query("SELECT * FROM BusinessCard")
    fun allCards(): Flow<List<BusinessCard>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCard(businessCard: BusinessCard)
    @Delete
    suspend fun deleteCard(businessCard: BusinessCard)
}