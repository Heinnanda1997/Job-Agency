package com.example.jobagency.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.jobagency.R
import com.example.jobagency.api.JobApi
import com.example.jobagency.model.Apply
import kotlinx.android.synthetic.main.fragment_apply.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplyFragment : Fragment() {

    private val jobApi : JobApi = JobApi()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apply, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callbacks3: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callbacks3)

        var data = arguments.let { ApplyFragmentArgs.fromBundle(it !!) }


              button.setOnClickListener {
          Log.d("action","Action")
                  var email = apply_Email.text.toString()
                  var address = apply_address.text.toString()
                  var name = apply_Name.text.toString()
                  var status = apply_Status.text.toString()
                  var password = apply_Passward.text.toString()
                  var phoneNo = apply_Phone.text.toString()
                  var detail = apply_detail.text.toString()
                  applyJob(name, email, phoneNo, address, status, password, detail)
              }
    }
    private fun applyJob(name : String, email:String, phoneNo:String,address:String,status:String,
    password:String,detail:String) {
        var apiCall = jobApi.store(name,email,phoneNo,address,status,password,detail)

        apiCall.enqueue(
            object  : Callback<Apply> {
                override fun onFailure(call: Call<Apply>, t: Throwable) {
                 Log.d("Error",t.toString())
                }

                override fun onResponse(call: Call<Apply>, response: Response<Apply>) {
                Toast.makeText(context,response.body()?.message, Toast.LENGTH_LONG).show()

                }

            }
        )
    }
}
