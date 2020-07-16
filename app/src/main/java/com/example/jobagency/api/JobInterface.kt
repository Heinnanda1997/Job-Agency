package com.example.jobagency.api

import com.example.jobagency.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface JobInterface {
    @GET("category")
    fun index (): Call<Category>


   @GET("jobs_by_filters")
   fun getJobsByFilter(
    @Query("category_id") category_id : Int
    ):Call<Jobs>


    @GET("job/{job_Id}")
    fun nextFun(
        @Path("job_Id") category_id: Int
    ):Call<Jobdetail>


    @POST("apply")
    fun store (
       @Query("name") name:String,
       @Query("email") email : String,
       @Query("phoneNo") phoneNo: String,
       @Query("address") address : String,
       @Query("status") status: String,
       @Query("password") password:String,
       @Query("detail") detail:String
    ):Call<Apply>
}

