package com.example.jobagency.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.jobagency.R
import com.example.jobagency.adapter.JobDetailAdapter
import com.example.jobagency.model.Job
import com.example.jobagency.model.JobX
import com.example.jobagency.viewmodel.JobDetailViewModel
import com.example.jobagency.viewmodel.NextViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_next.*
import kotlinx.android.synthetic.main.item_job.*


class NextFragment : Fragment() {

    private lateinit var nextViewModel: NextViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callbacks2: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callbacks2)
        var movieArgs = arguments.let { NextFragmentArgs.fromBundle(it!!) }
        var jobId = movieArgs.nextId
        observeNextViewModel(jobId)



        txt_applyButton.setOnClickListener {
            var act = NextFragmentDirections.actionNextFragmentToApplyFragment(id)
            findNavController().navigate(act)
        }

    }

    private fun observeNextViewModel(jobId: Int) {
        nextViewModel = ViewModelProvider(this).get(NextViewModel::class.java)
        nextViewModel.loadNext(jobId)
        nextViewModel.getNext().observe(viewLifecycleOwner, Observer {
            bindNextData(it)
        })

    }

    private fun bindNextData(data: Job) {
        Log.d("data", data.toString())
        txt_kindTwo.text = data.name
        txt_vacancy.text = data.vacancy
        Log.d("rsult", data?.salary)
        //txt_salary.text = data?.salary.toString()
        txt_experience.text = data?.experience
        txt_qualification.text = data?.qualifications
        //txt_city.text = data?.location
        txt_endDate.text = data?.company.created_at
        txt_company.text = data?.company.name
        txt_jobSalary.text = data?.salary
        txt_jobLocation.text = data?.location
        txt_mail.text = data?.company.email
    }

    override fun onResume() {
        super.onResume()
        var nextAr = arguments.let { NextFragmentArgs.fromBundle(it!!) }
        var id = nextAr.nextId
        nextViewModel.loadNext(id)
    }
}







