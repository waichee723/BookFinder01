package com.waichee.bookfinder01.network.model

data class Item(
    val accessInfo: AccessInfo?,
    val etag: String?,
    val id: String?,
    val kind: String?,
    val saleInfo: SaleInfo?,
    val searchInfo: SearchInfo?,
    val selfLink: String?,
    val volumeInfo: VolumeInfo?
)