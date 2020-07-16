package com.example.jobagency.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jobagency.R
import com.example.jobagency.model.Job
import com.example.jobagency.toSimpleString
import kotlinx.android.synthetic.main.item_job.view.*


class JobDetailAdapter(var detailList: List<Job> = ArrayList()) :
    RecyclerView.Adapter<JobDetailAdapter.JobDetailViewHolder>() {
    private var clickListener: JobDetailAdapter.ClickListener? = null

    interface ClickListener {
        fun onClick(id: Int)
    }

    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    inner class JobDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private lateinit var detailAgency: Job
        fun bindDetail(job: Job) {
            this.detailAgency = job
            itemView.txt_kind.text = job.name
            itemView.txt_city.text = job.location
            itemView.txt_salary.text = job.salary
//            itemView.txt_applyDate.text = job.company.created_at
            itemView.txt_applyDate.text = toSimpleString(job.company.created_at)
            //toSimpleString(article.publishedAt)
            //job.updated_at
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener?.onClick(detailAgency.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobDetailViewHolder {
        val views = LayoutInflater.from(parent.context).inflate(R.layout.item_job, parent, false)
        return JobDetailViewHolder(views)
    }

    override fun getItemCount(): Int {
        return detailList.size
    }

    override fun onBindViewHolder(holder: JobDetailViewHolder, position: Int) {
        holder.bindDetail(detailList.get(position))
    }

    fun updateDetails(detailResult: List<Job>) {
        this.detailList = detailResult
        notifyDataSetChanged()
    }
}

