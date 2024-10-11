package com.dicoding.dicodingeventsubmission.data.retrofit

import com.dicoding.dicodingeventsubmission.data.response.EventResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("events")
    fun getActiveEvents(
        @Query("active") active: Int
    ): Call<EventResponse>
}