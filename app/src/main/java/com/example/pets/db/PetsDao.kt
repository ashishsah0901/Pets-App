package com.example.pets.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pets.model.Pet

@Dao
interface PetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pet: Pet)
    @Delete
    suspend fun delete(pet: Pet)
    @Query("SELECT * FROM pets_table order by id ASC")
    fun getAllPets(): LiveData<List<Pet>>
    @Query("DELETE FROM pets_table")
    suspend fun deleteAll()
}