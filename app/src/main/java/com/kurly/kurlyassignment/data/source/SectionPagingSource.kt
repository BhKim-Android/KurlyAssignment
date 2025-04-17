package com.kurly.kurlyassignment.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kurly.kurlyassignment.domain.model.Section
import com.kurly.kurlyassignment.domain.model.SectionType

class SectionPagingSource(
    private val remoteDataSource: SectionRemoteDataSource
) : PagingSource<Int, Section>() {
    override fun getRefreshKey(state: PagingState<Int, Section>): Int? =
        state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Section> {
        return try {
            val page = params.key ?: 1
            val response = remoteDataSource.sections(page = page)
            val section = response.sections.map {
                Section(
                    title = it.title,
                    id = it.id,
                    type = SectionType.from(it.type),
                    url = it.url
                )
            }

            LoadResult.Page(
                data = section,
                prevKey = if (page == 1) null else page - 1,
                nextKey = response.paging?.nextPage
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}