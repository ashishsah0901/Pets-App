package com.example.pets.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.NavUtils
import com.example.pets.viewmodel.PetsViewModel
import com.example.pets.R
import com.example.pets.databinding.ActivityEditorBinding
import com.example.pets.model.Pet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditorBinding
    private var mGender = 0
    private val viewModel: PetsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpSpinner()
    }

    private fun setUpSpinner(){
        val genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
            R.array.array_gender_options,
            R.layout.support_simple_spinner_dropdown_item
        )
        genderSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        binding.spinnerGender.adapter = genderSpinnerAdapter
        binding.spinnerGender.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selection = parent?.getItemAtPosition(position) as String
                if (!TextUtils.isEmpty(selection)) {
                    mGender = when(selection){
                        getString(R.string.gender_male) -> 1
                        getString(R.string.gender_female) -> 2
                        else -> 0
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                mGender = 0
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_editor,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_save -> {
                if(saveToDatabase()) {
                    finish()
                }
                return true
            }
            R.id.action_delete -> {
                return true
            }
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveToDatabase(): Boolean {
        val name = binding.editPetName.text.toString().trim()
        val breed = binding.editPetBreed.text.toString().trim()
        val gender = mGender
        val weight = binding.editPetWeight.text.toString().toInt()
        if(name=="" || breed=="" || weight<=0){
            Toast.makeText(this,"Please enter all the fields!",Toast.LENGTH_SHORT).show()
            return false
        }else {
            val pet = Pet(name, breed, gender, weight)
            viewModel.insertPet(pet)
            return  true
        }
    }
}