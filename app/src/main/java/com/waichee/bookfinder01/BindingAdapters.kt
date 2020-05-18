package com.waichee.bookfinder01


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.waichee.bookfinder01.R.drawable.ic_launcher_background
import com.waichee.bookfinder01.network.model.Item
import com.waichee.bookfinder01.search.ApiStatus
import com.waichee.bookfinder01.search.ApiStatus.DONE
import com.waichee.bookfinder01.search.ApiStatus.ERROR
import com.waichee.bookfinder01.search.ApiStatus.LOADING
import com.waichee.bookfinder01.search.BookListAdapter

@BindingAdapter("numberOfResults")
fun TextView.setNumberOfResult(number: Int?) {
    number?.let {
        text = "Number of results: $number"
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Item>?) {
    val adapter = recyclerView.adapter as BookListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Picasso.get()
            .load(imgUrl)
            .placeholder(R.drawable.ic_block_black_24dp)
            .error(ic_launcher_background)
            .into(imgView)
    }
}

@BindingAdapter("apiStatus")
fun bindApiStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

