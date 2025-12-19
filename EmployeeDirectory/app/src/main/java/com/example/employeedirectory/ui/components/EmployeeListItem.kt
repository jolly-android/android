package com.example.employeedirectory.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.employeedirectory.R
import com.example.employeedirectory.data.model.Employee
import com.example.employeedirectory.data.model.EmployeeType

/**
 * Composable for displaying a single employee item in the list
 */
@Composable
fun EmployeeListItem(
    employee: Employee,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Employee Photo
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(employee.photoUrlSmall)
                    .crossfade(true)
                    .diskCacheKey(employee.photoUrlSmall)
                    .memoryCacheKey(employee.photoUrlSmall)
                    .build(),
                contentDescription = "Photo of ${employee.fullName}",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                error = painterResource(id = android.R.drawable.ic_menu_gallery)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Employee Info
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = employee.fullName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Text(
                    text = employee.team,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Text(
                    text = employee.emailAddress,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                // Employee Type Badge
                EmployeeTypeBadge(employeeType = employee.employeeType)
            }
        }
    }
}

@Composable
private fun EmployeeTypeBadge(
    employeeType: EmployeeType,
    modifier: Modifier = Modifier
) {
    val (text, color) = when (employeeType) {
        EmployeeType.FULL_TIME -> "Full Time" to Color(0xFF4CAF50)
        EmployeeType.PART_TIME -> "Part Time" to Color(0xFFFF9800)
        EmployeeType.CONTRACTOR -> "Contractor" to Color(0xFF2196F3)
    }
    
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = color,
        fontWeight = FontWeight.Medium,
        modifier = modifier
    )
}

