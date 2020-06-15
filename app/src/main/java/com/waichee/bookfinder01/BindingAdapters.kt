package com.waichee.bookfinder01


import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.waichee.bookfinder01.R.drawable.ic_launcher_background


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



