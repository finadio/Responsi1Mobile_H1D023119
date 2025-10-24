package com.unsoed.responsi1mobile_h1d023119.data.api

import com.unsoed.responsi1mobile_h1d023119.data.model.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FootballApiService {
    
    @Headers("X-Auth-Token: 194ef2d522c9467ea0e682a20742254f")
    @GET("v4/teams/{id}")
    fun getTeam(@Path("id") teamId: Int): Call<TeamResponse>
}
