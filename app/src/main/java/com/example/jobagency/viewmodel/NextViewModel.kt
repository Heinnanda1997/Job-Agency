package com.example.jobagency.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jobagency.api.JobApi
import com.example.jobagency.model.Job
import com.example.jobagency.model.JobX
import com.example.jobagency.model.Jobdetail
import com.example.jobagency.model.Jobs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextViewModel : ViewModel() {
    var jobNext: MutableLiveData<Job> = MutableLiveData()
    fun getNext(): LiveData<Job> = jobNext
    private val nextApi: JobApi = JobApi()


    fun loadNext(category_id: Int) {

        Log.d("cat", category_id.toString())
        val nextApiCall =  nextApi.nextFun(category_id)
        nextApiCall.enqueue(object : Callback<Jobdetail>{
            override fun onFailure(call: Call<Jobdetail>, t: Throwable) {

            }

            override fun onResponse(call: Call<Jobdetail>, response: Response<Jobdetail>) {
                val b = response.body()!!.jobs
                Log.d("response",response.body()!!.jobs.toString())
                jobNext.value = b
            }

        })

    }
}
