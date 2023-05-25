package com.example.nanopost.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nanopost.data.models.api.ApiPost
import com.example.nanopost.data.models.response.PagedDataResponse

class PostPagingSource(
    private val data: (Int, String?) -> PagedDataResponse<ApiPost>,
) :  PagingSource<String, ApiPost>(){

    override fun getRefreshKey(
        state: PagingState<String, ApiPost>
    ): String? {
        return null
    }

    override suspend fun load(
        params: LoadParams<String>
    ): LoadResult<String, ApiPost> {
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