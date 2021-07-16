package com.example.pets.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.pets.viewmodel.PetsViewModel
import com.example.pets.R
import com.example.pets.databinding.ActivityMainBinding
import com.example.pets.model.Pet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PetsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.allPets.observe(this, Observer {
            binding.textViewPet.text = "Length of database is ${it.size}"
        })
        binding.fab.setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_catalog,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_insert_dummy_data -> {
                insertDummyPet()
                return true
            }
            R.id.action_delete_all_entries -> {
                deleteAllPets()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun insertDummyPet(){
        val pet = Pet("Tommy","Street-Dog",1,15)
        viewModel.insertPet(pet)
    }

    private fun deleteAllPets(){
        viewModel.deleteAllPets()
    }
}