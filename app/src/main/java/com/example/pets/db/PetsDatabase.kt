package com.example.pets.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pets.model.Pet

@Database(entities = [Pet::class], version = 1, exportSchema = false)
abstract class PetsDatabase: RoomDatabase() {
    abstract fun getPetsDao(): PetsDao
}