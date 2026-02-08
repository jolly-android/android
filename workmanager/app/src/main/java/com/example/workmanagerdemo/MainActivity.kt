package com.example.workmanagerdemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.example.workmanagerdemo.adapters.WorkInfoAdapter
import com.example.workmanagerdemo.databinding.ActivityMainBinding
import com.example.workmanagerdemo.workers.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var workInfoAdapter: WorkInfoAdapter
    private val workManager by lazy { WorkManager.getInstance(applicationContext) }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupRecyclerView()
        setupClickListeners()
        requestNotificationPermission()
        observeAllWork()
    }
    
    private fun setupRecyclerView() {
        workInfoAdapter = WorkInfoAdapter()
        binding.recyclerViewWorkInfo.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = workInfoAdapter
        }
    }
    
    private fun setupClickListeners() {
        binding.btnSimpleWork.setOnClickListener {
            startSimpleWork()
        }
        
        binding.btnProgressWork.setOnClickListener {
            startProgressWork()
        }
        
        binding.btnConstrainedWork.setOnClickListener {
            startConstrainedWork()
        }
        
        binding.btnChainedWork.setOnClickListener {
            startChainedWork()
        }
        
        binding.btnPeriodicWork.setOnClickListener {
            startPeriodicWork()
        }
        
        binding.btnNotificationWork.setOnClickListener {
            startNotificationWork()
        }
        
        binding.btnCancelAll.setOnClickListener {
            cancelAllWork()
        }
    }
    
    private fun startSimpleWork() {
        val inputData = workDataOf(SimpleWorker.KEY_TASK_NAME to "Simple Task Demo")
        
        val workRequest = OneTimeWorkRequestBuilder<SimpleWorker>()
            .setInputData(inputData)
            .addTag(TAG_SIMPLE_WORK)
            .build()
        
        workManager.enqueueUniqueWork(
            "simple_work",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
        
        observeWork(workRequest.id, "Simple Work")
        showToast("Simple work started")
    }
    
    private fun startProgressWork() {
        val workRequest = OneTimeWorkRequestBuilder<ProgressWorker>()
            .addTag(TAG_PROGRESS_WORK)
            .build()
        
        workManager.enqueueUniqueWork(
            "progress_work",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
        
        // Observe progress
        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this) { workInfo ->
            workInfo?.let {
                val progress = it.progress.getInt(ProgressWorker.KEY_PROGRESS, 0)
                val message = it.progress.getString(ProgressWorker.KEY_MESSAGE)
                
                if (progress > 0) {
                    binding.progressBar.progress = progress
                    binding.tvProgressText.text = "$progress% - $message"
                }
                
                if (it.state.isFinished) {
                    binding.progressBar.progress = 0
                    binding.tvProgressText.text = "Progress work completed!"
                }
            }
        }
        
        observeWork(workRequest.id, "Progress Work")
        showToast("Progress work started")
    }
    
    private fun startConstrainedWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
        
        val workRequest = OneTimeWorkRequestBuilder<ConstrainedWorker>()
            .setConstraints(constraints)
            .addTag(TAG_CONSTRAINED_WORK)
            .build()
        
        workManager.enqueueUniqueWork(
            "constrained_work",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
        
        observeWork(workRequest.id, "Constrained Work")
        showToast("Constrained work queued (needs network & battery)")
    }
    
    private fun startChainedWork() {
        val workA = OneTimeWorkRequestBuilder<ChainWorkerA>()
            .addTag(TAG_CHAINED_WORK)
            .build()
        
        val workB = OneTimeWorkRequestBuilder<ChainWorkerB>()
            .addTag(TAG_CHAINED_WORK)
            .build()
        
        val workC = OneTimeWorkRequestBuilder<ChainWorkerC>()
            .addTag(TAG_CHAINED_WORK)
            .build()
        
        workManager.beginUniqueWork(
            "chained_work",
            ExistingWorkPolicy.REPLACE,
            workA
        )
            .then(workB)
            .then(workC)
            .enqueue()
        
        observeWork(workA.id, "Chain Work A")
        observeWork(workB.id, "Chain Work B")
        observeWork(workC.id, "Chain Work C")
        showToast("Chained work started (A → B → C)")
    }
    
    private fun startPeriodicWork() {
        // Note: Minimum interval is 15 minutes, using for demo purposes
        val workRequest = PeriodicWorkRequestBuilder<PeriodicWorker>(
            15, TimeUnit.MINUTES,
            5, TimeUnit.MINUTES  // Flex interval
        )
            .addTag(TAG_PERIODIC_WORK)
            .build()
        
        workManager.enqueueUniquePeriodicWork(
            "periodic_work",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
        
        observeWork(workRequest.id, "Periodic Work")
        showToast("Periodic work scheduled (every 15 min)")
    }
    
    private fun startNotificationWork() {
        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(3, TimeUnit.SECONDS)
            .addTag(TAG_NOTIFICATION_WORK)
            .build()
        
        workManager.enqueueUniqueWork(
            "notification_work",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
        
        observeWork(workRequest.id, "Notification Work")
        showToast("Notification work will show notification in 3 seconds")
    }
    
    private fun observeWork(workId: java.util.UUID, workName: String) {
        workManager.getWorkInfoByIdLiveData(workId).observe(this) { workInfo ->
            workInfo?.let {
                workInfoAdapter.updateWorkInfo(workId, workName, it)
            }
        }
    }
    
    private fun observeAllWork() {
        // Observe all work with our tags
        val tags = listOf(
            TAG_SIMPLE_WORK,
            TAG_PROGRESS_WORK,
            TAG_CONSTRAINED_WORK,
            TAG_CHAINED_WORK,
            TAG_PERIODIC_WORK,
            TAG_NOTIFICATION_WORK
        )
        
        tags.forEach { tag ->
            workManager.getWorkInfosByTagLiveData(tag).observe(this) { workInfoList ->
                workInfoList?.forEach { _ ->
                    // Work info is already being tracked through individual observers
                }
            }
        }
    }
    
    private fun cancelAllWork() {
        workManager.cancelAllWork()
        workInfoAdapter.clearAll()
        binding.progressBar.progress = 0
        binding.tvProgressText.text = "All work cancelled"
        showToast("All work cancelled")
    }
    
    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_NOTIFICATION_PERMISSION
                )
            }
        }
    }
    
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    
    companion object {
        private const val REQUEST_NOTIFICATION_PERMISSION = 1001
        private const val TAG_SIMPLE_WORK = "simple_work"
        private const val TAG_PROGRESS_WORK = "progress_work"
        private const val TAG_CONSTRAINED_WORK = "constrained_work"
        private const val TAG_CHAINED_WORK = "chained_work"
        private const val TAG_PERIODIC_WORK = "periodic_work"
        private const val TAG_NOTIFICATION_WORK = "notification_work"
    }
}


