package com.kurly.kurlyassignment.data.remote

import com.kurly.kurlyassignment.data.model.SectionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SectionApiService {

    @GET("sections")
    suspend fun sections(
        @Query("page") page: Int
    ): SectionResponse
}