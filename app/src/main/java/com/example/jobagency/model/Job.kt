package com.example.jobagency.model

data class Job(
    val category: CategoryXX,
    val company: Company,
    val created_at: String,
    val details: String,
    val experience: String,
    val id: Int,
    val location: String,
    val name: String,
    val qualifications: String,
    val salary: String,
    val updated_at: String,
    val vacancy: String
)