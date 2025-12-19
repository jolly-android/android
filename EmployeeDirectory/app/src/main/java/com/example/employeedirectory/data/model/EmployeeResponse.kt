package com.example.employeedirectory.data.model

import com.google.gson.annotations.SerializedName

/**
 * API response wrapper for the employee list
 */
data class EmployeeResponse(
    @SerializedName("employees")
    val employees: List<Employee>
)

