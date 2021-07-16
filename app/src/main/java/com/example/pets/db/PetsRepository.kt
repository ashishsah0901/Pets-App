package com.example.pets.db

import androidx.lifecycle.LiveData
import com.example.pets.model.Pet
import javax.inject.Inject

class PetsRepository @Inject constructor(private val petsDao: PetsDao) {
    val allPets: LiveData<List<Pet>> = petsDao.getAllPets()
    suspend fun insert(pet: Pet){
        petsDao.insert(pet)
    }
    suspend fun delete(pet: Pet){
        petsDao.delete(pet)
    }
    suspend fun deleteAllPets(){
        petsDao.deleteAll()
    }
}