package com.example.jobagency.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jobagency.R
import com.example.jobagency.model.CategoryX
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_fragment.view.*

class JobAdapter(var jobList:List<CategoryX> = ArrayList())
    :RecyclerView.Adapter<JobAdapter.JobViewHolder>() {

    private var clickListener : ClickListener? =null
    fun setOnClickListener(clickListener : ClickListener){
        this.clickListener = clickListener
    }
    inner class JobViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener
    {

      private lateinit var jobAgency : CategoryX
        fun bindJob(categoryX: CategoryX){
            this.jobAgency =categoryX
            itemView.online_shop.text =categoryX.name

        }
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener?.onClick(jobAgency.id)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobAdapter.JobViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fragment,parent,false)
        return JobViewHolder(view)
    }


    override fun getItemCount(): Int {
      return jobList.size
    }

    override fun onBindViewHolder(holder: JobAdapter.JobViewHolder, position: Int) {
        holder.bindJob(jobList.get(position))
    }

    fun updateJob(result :List<CategoryX>){
        this.jobList =result
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick (id:Int)
    }
}


