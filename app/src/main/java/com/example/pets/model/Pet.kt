package com.example.pets.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "pets_table")
class Pet(@ColumnInfo(name = "name")var name:String, @ColumnInfo(name = "breed")var breed: String, @ColumnInfo(name = "gender")var gender: Int, @ColumnInfo(name = "weight")var weight: Int): Serializable{
    @PrimaryKey(autoGenerate = true)var id = 0
}