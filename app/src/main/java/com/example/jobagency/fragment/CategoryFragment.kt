package com.example.jobagency.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobagency.R
import com.example.jobagency.adapter.JobAdapter
import com.example.jobagency.viewmodel.JobViewModel
import kotlinx.android.synthetic.main.fragment_category.*
import androidx.navigation.fragment.findNavController

class CategoryFragment : Fragment(),JobAdapter.ClickListener {
     private lateinit var jobAdapter: JobAdapter
     private lateinit var jobViewModel:JobViewModel
     private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = GridLayoutManager(context,2)
        jobAdapter = JobAdapter()
        recycler_job.apply {
            adapter = jobAdapter
            layoutManager = viewManager
        }
        observeJobViewModel()
        jobAdapter.setOnClickListener(this)
    }

    private fun observeJobViewModel () {
        jobViewModel = ViewModelProvider(this).get(JobViewModel::class.java)
        jobViewModel.loadResult()
        jobViewModel.getResult().observe(viewLifecycleOwner, Observer {
            jobAdapter.updateJob(it.categories)
            Log.d("Result","Success")
        })
    }

    override fun onResume() {
        super.onResume()
        jobViewModel.loadResult()
    }

    override fun onClick(id: Int) {

     var action = CategoryFragmentDirections.actionCategoryFragmentToJobDetailFragment(id)
        findNavController().navigate(action)
    }

}

