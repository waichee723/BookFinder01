package com.waichee.bookfinder01.network

import androidx.paging.PagingSource
import com.waichee.bookfinder01.network.model.Item
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

private const val STARTING_INDEX = 0

class ItemPagingSource(
    private val service: BooksApiService,
    private val query: String
): PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val position = params.key ?: STARTING_INDEX

        return try {

            Timber.i("Trying")

            val response = service.getResultPaged(
                keyword = query,
                startIndex = position,
                maxResults = params.loadSize).await()
            val books = response.items

            Timber.i(books.toString())

            LoadResult.Page(
                data = books,
                prevKey = if (position == STARTING_INDEX) null else position - params.loadSize,
                nextKey = if (books.isEmpty()) null else position + params.loadSize
            )
        } catch (e: IOException) {
            Timber.e(e)
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Timber.e(e)
            LoadResult.Error(e)
        }
    }
}