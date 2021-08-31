package com.example.businesscard.view

import androidx.recyclerview.widget.DiffUtil
import com.example.businesscard.model.BusinessCard

class DiffCallback: DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard , newItem: BusinessCard): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: BusinessCard , newItem: BusinessCard): Boolean = oldItem.id == newItem.id

}