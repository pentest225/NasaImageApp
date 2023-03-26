package ci.orange.nasaimageapp.presentation


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import ci.orange.nasaimageapp.R
import ci.orange.nasaimageapp.domain.worker.DownloadImageWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        delayedInit()
    }

    private fun delayedInit() = applicationScope.launch {
        setupRecurringWork()
    }
    private fun setupRecurringWork(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                setRequiresDeviceIdle(true)
            }.build()

        val repeatingRequest = PeriodicWorkRequestBuilder<DownloadImageWorker>(1, TimeUnit.DAYS )
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance()


        workManager.enqueueUniquePeriodicWork(
            DownloadImageWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }
}

