package com.example.pets.di

import android.app.Application
import androidx.room.Room
import com.example.pets.db.PetsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDB(app: Application): PetsDatabase {
        return Room.databaseBuilder(app, PetsDatabase::class.java,"pets_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Singleton
    @Provides
    fun getPetsDao(db: PetsDatabase) = db.getPetsDao()
}