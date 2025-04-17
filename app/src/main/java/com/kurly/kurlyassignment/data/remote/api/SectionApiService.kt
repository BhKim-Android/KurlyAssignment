package com.kurly.kurlyassignment.data.remote.api

import com.kurly.kurlyassignment.data.remote.model.ProductResponse
import com.kurly.kurlyassignment.data.remote.model.SectionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SectionApiService {

    @GET("sections")
    suspend fun sections(
        @Query("page") page: Int
    ): SectionResponse

    @GET("section/products")
    suspend fun products(
        @Query("sectionId") sectionId: Int
    ): Response<ProductResponse>
}