
package com.example.employeedirectory.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing an employee
 */
data class Employee(
    @SerializedName("uuid")
    val id: String,
    
    @SerializedName("full_name")
    val fullName: String,
    
    @SerializedName("phone_number")
    val phoneNumber: String?,
    
    @SerializedName("email_address")
    val emailAddress: String,
    
    @SerializedName("biography")
    val biography: String?,
    
    @SerializedName("photo_url_small")
    val photoUrlSmall: String?,
    
    @SerializedName("photo_url_large")
    val photoUrlLarge: String?,
    
    @SerializedName("team")
    val team: String,
    
    @SerializedName("employee_type")
    val employeeType: EmployeeType
)

enum class EmployeeType {
    @SerializedName("FULL_TIME")
    FULL_TIME,
    
    @SerializedName("PART_TIME")
    PART_TIME,
    
    @SerializedName("CONTRACTOR")
    CONTRACTOR
}

