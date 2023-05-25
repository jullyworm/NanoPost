package com.example.nanopost.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nanopost.data.models.api.ApiImage
import com.example.nanopost.data.models.response.PagedDataResponse

class ImagesPagingSource (private val data: (Int, String?) -> PagedDataResponse<ApiImage>,
) :  PagingSource<String, ApiImage>(){

    override fun getRefreshKey(
        state: PagingState<String, ApiImage>
    ): String? {
        return null
    }

    override suspend fun load(
        params: LoadParams<String>
    ): LoadResult<String, ApiImage> {
        try {
            val response = data(
                params.loadSize,
                params.key,
            )
            return LoadResult.Page(
                data = response.items,
                nextKey = response.offset,
                prevKey = null,
            )
        }
        catch (e: Exception){
            return LoadResult.Error(e)
        }
    }
}