package com.example.pets.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import com.example.pets.db.PetsRepository
import com.example.pets.model.Pet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetsViewModel @Inject constructor(private val repository: PetsRepository): ViewModel() {
    val allPets: LiveData<List<Pet>> = repository.allPets
    fun deletePet(pet: Pet) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(pet)
    }
    fun insertPet(pet: Pet) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(pet)
    }
    fun deleteAllPets() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllPets()
    }
}