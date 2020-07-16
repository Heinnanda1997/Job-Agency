package com.example.jobagency.api

import com.example.jobagency.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class JobApi {
private val jobInterface: JobInterface
    companion object {
        const val BASE_URL = "http://job-agency.khaingthinkyi.me/api/setup/"
    }
    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        jobInterface = retrofit.create(JobInterface::class.java)
    }

    fun index ():Call<Category>{
        return jobInterface.index()
    }

    fun getJobsByFilter(category_id : Int):Call<Jobs>{
        return jobInterface.getJobsByFilter(category_id)
    }

    fun nextFun(category_id: Int):Call<Jobdetail>{
        return jobInterface.nextFun(category_id)
    }

    fun store (name:String, email: String, phoneNo:String,address:String,status:String,password:String,detail:String)
            :Call<Apply> {
        return jobInterface.store(name,email,phoneNo,address,status, password,detail)
    }

}




