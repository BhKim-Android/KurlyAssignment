package com.kurly.kurlyassignment.data.source

import com.kurly.kurlyassignment.data.remote.model.ProductResponse
import com.kurly.kurlyassignment.data.remote.model.SectionResponse
import com.kurly.kurlyassignment.data.remote.api.SectionApiService
import retrofit2.Response
import javax.inject.Inject

class SectionRemoteDataSource @Inject constructor(
    private val sectionApiService: SectionApiService
) {
    suspend fun sections(page: Int): SectionResponse = sectionApiService.sections(page)
    suspend fun products(sectionId: Int): Response<ProductResponse> = sectionApiService.products(sectionId)
}