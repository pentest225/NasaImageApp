package ci.orange.nasaimageapp.data

import android.util.Log
import ci.orange.nasaimageapp.data.remoute.RetrofitApi
import ci.orange.nasaimageapp.data.remoute.api.getNextSevenDaysFormattedDates
import ci.orange.nasaimageapp.data.remoute.api.parseAsteroidsJsonResult
import org.json.JSONObject

private const val TAG = "AsteroidRepository"
class AsteroidRepository() {

    suspend fun getImageOfWeek():Result<List<Asteroid>>{
        try {
            val date = getNextSevenDaysFormattedDates()
            val response = RetrofitApi.nasaApi.getAsteroidData()
            return if(response.isSuccessful && response.body() != null){
                val json = JSONObject(response.body()!!)
                val responseObject = parseAsteroidsJsonResult(json)
                Log.d(TAG, "Get Data: ${responseObject.count()}")

                Result.success(responseObject)
            }else{
                Log.e(TAG, "getAsteroidData: Error to get data ${response.message()}", )
                Result.failure(MyException(response.message()))
            }
        }catch (e:java.lang.Exception){
            return Result.failure(e)
        }
    }
}

data class MyException(val exceptionMessage:String):Throwable(exceptionMessage)