package com.example.jobagency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jobagency.api.JobApi
import com.example.jobagency.model.Job
import com.example.jobagency.model.Jobs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobDetailViewModel :ViewModel() {
    var jobResult: MutableLiveData<Jobs> = MutableLiveData()

    //getter
    fun getDetails(): LiveData<Jobs> = jobResult
    private val detailApi: JobApi = JobApi()

    //setter
    fun loadDetails(category_id: Int) {
        val detailApiCall = detailApi.getJobsByFilter(category_id)
        detailApiCall.enqueue(object : Callback<Jobs> {
            override fun onFailure(call: Call<Jobs>, t: Throwable) {

            }

            override fun onResponse(call: Call<Jobs>, response: Response<Jobs>) {
                val a = response.body()!!
                jobResult.value = a
            }
        })
    }


}






