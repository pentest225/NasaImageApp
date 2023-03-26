package ci.orange.nasaimageapp.data

import android.content.Context


import android.util.Log
import ci.orange.nasaimageapp.data.local.getInstance
import ci.orange.nasaimageapp.data.remoute.RetrofitApi
import ci.orange.nasaimageapp.data.remoute.api.getNextSevenDaysFormattedDates
import ci.orange.nasaimageapp.data.remoute.api.parseAsteroidsJsonResult
import ci.orange.nasaimageapp.data.remoute.dto.toImageOfToDay
import ci.orange.nasaimageapp.domain.AsteroidRepository
import ci.orange.nasaimageapp.domain.model.Asteroid
import ci.orange.nasaimageapp.domain.model.ImageOfToday
import ci.orange.nasaimageapp.domain.model.toAsteroid
import ci.orange.nasaimageapp.domain.model.toAsteroidEntity
import ci.orange.nasaimageapp.utils.Constants
import org.json.JSONObject
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.log


private const val TAG = "AsteroidRepository"
class AsteroidRepositoryImpl(private val context :Context) :AsteroidRepository{
    private val dao = getInstance(context).asteroidDao


    override suspend fun getGetAsteroidByDate(
        startData: String,
        endDate: String
    ): Result<List<Asteroid>> {
        try {

            val response = RetrofitApi.nasaApi.getAsteroidData(startData,endDate)
            return if(response.isSuccessful && response.body() != null){
                val responseBody = response.body()!!

                val json = JSONObject(responseBody)
                val responseObject = parseAsteroidsJsonResult(json)
                val entityList = responseObject.map { it.toAsteroidEntity() }.toTypedArray()
                dao.insertAll(*entityList)
                Result.success(responseObject)
            }else{
                Log.e(TAG, "getAsteroidData: Error to get data ${response.message()}", )
                Result.failure(MyException(response.message()))
            }
        }catch (e:java.lang.Exception){
            return Result.failure(e)
        }
    }

    override suspend fun getAsteroidOfToday(): Result<List<Asteroid>> {
        return try {
            val dateFormat = DateTimeFormatter.ofPattern(Constants.API_QUERY_DATE_FORMAT)
            val today = LocalDate.now().format(dateFormat)
            Log.i(TAG, "getAsteroidOfToday: Current Date $today")
            val todayList = dao.getImagesByDate(today)
            if(todayList.isEmpty()){
                val dateInterval = getNextSevenDaysFormattedDates()
                val refreshResult = this.getGetAsteroidByDate(dateInterval.first(),dateInterval.last())
                if(refreshResult.isSuccess){
                    this.getAsteroidOfToday()
                }else{
                    Result.failure(MyException("Error to load new Data"))
                }
            }else{
                val resultList = todayList.map { it.toAsteroid() }
                Result.success(resultList)
            }

        }catch (e:java.lang.Exception){
            Result.failure(e)
        }
    }

    override suspend fun getImageOfToDay(): Result<ImageOfToday> {
      return  try {
            val response = RetrofitApi.nasaApiForImage.getImageOfToDay()
            Log.i(TAG, "getImageOfToDay: Response $response")
            if(response.isSuccessful && response.body()!= null){
                val todayImageDto = response.body()!!
                Log.i(TAG, "getImageOfToDay: $todayImageDto")
                Result.success(todayImageDto.toImageOfToDay())
            }else{
                Result.failure(MyException("Error to load new Data"))
            }
        }catch (e:java.lang.Exception){

            Result.failure(e)
        }
    }
}

data class MyException(val exceptionMessage:String):Throwable(exceptionMessage)