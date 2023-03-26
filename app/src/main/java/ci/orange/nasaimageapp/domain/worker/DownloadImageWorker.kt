package ci.orange.nasaimageapp.domain.worker

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ci.orange.nasaimageapp.data.AsteroidRepositoryImpl
import ci.orange.nasaimageapp.data.remoute.api.getNextSevenDaysFormattedDates


private const val TAG = "DownloadImageWorker"
class DownloadImageWorker(private val context: Activity,params:WorkerParameters) :CoroutineWorker(context,params){
    companion object {
        const val WORK_NAME = "BackgroundDownloadImageWorker"
    }

    override suspend fun doWork(): Result {
        val repository = AsteroidRepositoryImpl(context)
        return try {
            Log.i(TAG, "doWork: Start Worker")
            val date = getNextSevenDaysFormattedDates()
            repository.getGetAsteroidByDate(date.first(),date.last())
            Result.success()
        }catch (e:java.lang.Exception){
            Log.e(TAG, "doWork: ",e )
            Result.failure()
        }
    }
}