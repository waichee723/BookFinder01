package com.waichee.bookfinder01.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.waichee.bookfinder01.network.model.BookApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.googleapis.com/books/v1/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface BooksApiService {
    @GET("volumes")
    fun getResult(
        @Query("q") type: String):
            Deferred<BookApiResponse>

    @GET("volumes")
    fun getResultPaged(
        @Query("q") keyword: String,
        @Query("startIndex") startIndex: Int,
        @Query("maxResults") maxResults: Int):
            Deferred<BookApiResponse>
}

object BooksApi {
    val retrofitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}

