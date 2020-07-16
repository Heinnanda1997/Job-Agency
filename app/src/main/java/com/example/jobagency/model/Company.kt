package com.example.jobagency.model

data class Company(
    val created_at: String,
    val email: String,
    val email_verified_at: Any,
    val id: Int,
    val name: String,
    val role: String,
    val updated_at: String
)