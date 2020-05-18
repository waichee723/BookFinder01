package com.waichee.bookfinder01.network.model

import com.waichee.bookfinder01.network.model.ListPrice
import com.waichee.bookfinder01.network.model.Offer
import com.waichee.bookfinder01.network.model.RetailPriceX

data class SaleInfo(
    val buyLink: String?,
    val country: String?,
    val isEbook: Boolean?,
    val listPrice: ListPrice?,
    val offers: List<Offer>?,
    val retailPrice: RetailPriceX?,
    val saleability: String?
)