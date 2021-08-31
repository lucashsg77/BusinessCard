package com.example.businesscard.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscard.databinding.BusinessCardItemBinding
import com.example.businesscard.model.BusinessCard

class BusinessCardAdapter: ListAdapter<BusinessCard , BusinessCardAdapter.ViewHolder>(DiffCallback()) {
    var listenerShare: (View) -> Unit = {}

    inner class ViewHolder(
        private val binding: BusinessCardItemBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: BusinessCard){
            binding.nameTv.text = item.name
            binding.phoneTv.text = item.phone
            binding.emailTv.text = item.email
            binding.companyNameTv.text = item.company
            binding.contentMcv.setCardBackgroundColor(item.color)
            binding.contentMcv.setOnClickListener { listenerShare(it) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BusinessCardItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        holder.bind(getItem(position))
    }
}
