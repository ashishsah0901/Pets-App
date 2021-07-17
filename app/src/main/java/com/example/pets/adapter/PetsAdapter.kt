package com.example.pets.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pets.databinding.ItemPetBinding
import com.example.pets.model.Pet

class PetsAdapter(private val context: Context,private val listener: RVItemCLick): RecyclerView.Adapter<PetsAdapter.PetsViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<Pet>(){
        override fun areItemsTheSame(oldItem: Pet, newItem: Pet): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pet, newItem: Pet): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    val differ = AsyncListDiffer(this,differCallBack)

    inner class PetsViewHolder(val binding: ItemPetBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsViewHolder {
        return PetsViewHolder(ItemPetBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {
        val pet = differ.currentList[position]
        holder.binding.petNameTV.text = pet.name
        holder.binding.breedPetTV.text = pet.breed
        when(pet.gender){
            1 -> holder.binding.genderPetTV.text = "Male"
            2 -> holder.binding.genderPetTV.text = "female"
            else -> holder.binding.genderPetTV.text = "Unknown"
        }
        holder.binding.weightPetTV.text = "${pet.weight} KG"
        holder.binding.petsCL.setOnClickListener {
            listener.onClick(differ.currentList[position])
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
interface RVItemCLick{
    fun onClick(pet:Pet)
}