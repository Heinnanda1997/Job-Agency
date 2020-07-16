package com.example.jobagency.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobagency.R
import com.example.jobagency.adapter.JobAdapter
import com.example.jobagency.adapter.JobDetailAdapter
import com.example.jobagency.viewmodel.JobDetailViewModel
import kotlinx.android.synthetic.main.fragment_job_detail.*

class JobDetailFragment() : Fragment(), JobDetailAdapter.ClickListener {
    private lateinit var jobDetailAdapter: JobDetailAdapter
    private lateinit var jobDetailViewModel: JobDetailViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  jobDetailAdapter.setOnClickListener(this)

        val callback : OnBackPressedCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)


        viewManager = GridLayoutManager(context,1)
        jobDetailAdapter = JobDetailAdapter()
        val apply = recycler_detail.apply {
            adapter = jobDetailAdapter
            layoutManager = viewManager
        }

        var movieArgs =arguments.let { JobDetailFragmentArgs.fromBundle(it!!) }
        var jobId =movieArgs.jobId
        observeDetailViewModel (jobId)
       jobDetailAdapter.setOnClickListener(this)


    }

    private fun observeDetailViewModel (job_id: Int) {
        jobDetailViewModel = ViewModelProvider(this).get(JobDetailViewModel::class.java)

        jobDetailViewModel.loadDetails(job_id)
        jobDetailViewModel.getDetails().observe(viewLifecycleOwner, Observer {
            jobDetailAdapter.updateDetails(it.jobs)
        })
    }

    override fun onResume() {
        super.onResume()
        var movieArgs =arguments.let { JobDetailFragmentArgs.fromBundle(it!!) }
        var jobId =movieArgs.jobId
        jobDetailViewModel.loadDetails(jobId)
    }

    override fun onClick(id: Int) {
        var actions = JobDetailFragmentDirections.actionJobDetailFragmentToNextFragment(id)
        findNavController().navigate(actions)
    }
}



