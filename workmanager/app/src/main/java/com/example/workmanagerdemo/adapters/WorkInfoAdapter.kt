package com.example.workmanagerdemo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.work.WorkInfo
import com.example.workmanagerdemo.databinding.ItemWorkInfoBinding
import java.text.SimpleDateFormat
import java.util.*

class WorkInfoAdapter : RecyclerView.Adapter<WorkInfoAdapter.WorkInfoViewHolder>() {
    
    private val workInfoList = mutableListOf<WorkInfoItem>()
    
    data class WorkInfoItem(
        val id: UUID,
        val name: String,
        var workInfo: WorkInfo
    )
    
    fun updateWorkInfo(id: UUID, name: String, workInfo: WorkInfo) {
        val index = workInfoList.indexOfFirst { it.id == id }
        if (index != -1) {
            workInfoList[index].workInfo = workInfo
            notifyItemChanged(index)
        } else {
            workInfoList.add(0, WorkInfoItem(id, name, workInfo))
            notifyItemInserted(0)
        }
    }
    
    fun clearAll() {
        workInfoList.clear()
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkInfoViewHolder {
        val binding = ItemWorkInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WorkInfoViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: WorkInfoViewHolder, position: Int) {
        holder.bind(workInfoList[position])
    }
    
    override fun getItemCount(): Int = workInfoList.size
    
    class WorkInfoViewHolder(private val binding: ItemWorkInfoBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: WorkInfoItem) {
            binding.apply {
                tvWorkName.text = item.name
                tvWorkId.text = "ID: ${item.id.toString().take(8)}..."
                tvWorkState.text = "State: ${item.workInfo.state.name}"
                
                // Set state color
                val stateColor = when (item.workInfo.state) {
                    WorkInfo.State.ENQUEUED -> android.graphics.Color.parseColor("#FF9800")
                    WorkInfo.State.RUNNING -> android.graphics.Color.parseColor("#2196F3")
                    WorkInfo.State.SUCCEEDED -> android.graphics.Color.parseColor("#4CAF50")
                    WorkInfo.State.FAILED -> android.graphics.Color.parseColor("#F44336")
                    WorkInfo.State.BLOCKED -> android.graphics.Color.parseColor("#9E9E9E")
                    WorkInfo.State.CANCELLED -> android.graphics.Color.parseColor("#607D8B")
                }
                tvWorkState.setTextColor(stateColor)
                
                // Show output data if available
                val outputData = item.workInfo.outputData
                if (outputData.keyValueMap.isNotEmpty()) {
                    val output = outputData.keyValueMap.entries.joinToString("\n") { 
                        "${it.key}: ${it.value}" 
                    }
                    tvWorkOutput.text = "Output:\n$output"
                } else {
                    tvWorkOutput.text = "No output data yet"
                }
                
                // Show timestamp
                val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                tvWorkTimestamp.text = "Updated: $time"
            }
        }
    }
}


