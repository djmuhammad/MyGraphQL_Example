package com.example.mygraphql.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mygraphql.CharactersListQuery
import com.example.mygraphql.databinding.ItemDataBinding

class MyAdapter :
    ListAdapter<CharactersListQuery.Result, MyAdapter.CharacterViewHolder>(MyDiffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemDataBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind()
    }

    inner class CharacterViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val data =  getItem(adapterPosition)
            binding.apply {
                name.text = data.name
                species.text = data.species
            }
        }

        init {
            binding.root.setOnClickListener({

            })
        }

    }

    private object MyDiffUtil : DiffUtil.ItemCallback<CharactersListQuery.Result>() {
        override fun areItemsTheSame(
            oldItem: CharactersListQuery.Result,
            newItem: CharactersListQuery.Result
        ): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(
            oldItem: CharactersListQuery.Result,
            newItem: CharactersListQuery.Result
        ): Boolean {
            TODO("Not yet implemented")
        }
    }
}