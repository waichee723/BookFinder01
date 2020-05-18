package com.waichee.bookfinder01.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.waichee.bookfinder01.databinding.ListItemBookBinding
import com.waichee.bookfinder01.network.model.Item

class BookListAdapter : ListAdapter<Item, BookListAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemBookBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }


    companion object DiffCallback: DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }
    }


    class ViewHolder(private var binding: ListItemBookBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Item) {
            binding.book = book
            binding.executePendingBindings()
        }
    }


}