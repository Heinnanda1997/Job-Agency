package com.example.jobagency.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jobagency.api.JobApi
import com.example.jobagency.model.Category
import retrofit2.Call
import retrofit2.Response

class JobViewModel :ViewModel() {
    var results : MutableLiveData<Category> = MutableLiveData()
    fun getResult(): LiveData<Category> = results
    private val jobApi: JobApi = JobApi()

    fun loadResult() {
        val apiCall = jobApi.index()
        apiCall.enqueue(object : retrofit2.Callback<Category>{
            override fun onFailure(call: Call<Category>, t: Throwable) {
                Log.d("Error",t.toString())
            }

            override fun onResponse(call: Call<Category>, response: Response<Category>) {
              val a = response.body()!!
                 results.value = a
                Log.d("response",a.toString())
            }

        })
    }
}

