package com.waichee.bookfinder01.network.model

import com.waichee.bookfinder01.network.model.Item

data class BookApiResponse(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)