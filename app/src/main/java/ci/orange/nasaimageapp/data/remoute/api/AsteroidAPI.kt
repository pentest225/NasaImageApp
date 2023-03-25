package ci.orange.nasaimageapp.data.remoute.api

import ci.orange.nasaimageapp.Constants
import ci.orange.nasaimageapp.data.remoute.dto.ImageOfToDayDto
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidAPI {
    @GET("planetary/apod?api_key=${Constants.API_KEY}")
    suspend fun getImageOfToDay():Response<ImageOfToDayDto>

//    @GET("neo/rest/v1/feed?start_date={startData}&end_date={endDate}&api_key=${Constants.API_KEY}")
//    suspend fun getAsteroidData(@Query("startData") startDate:String,@Query("endData") endDate:String):Response<String>
    @GET("neo/rest/v1/feed?start_date=2023-03-25&end_date=2023-04-01&api_key=${Constants.API_KEY}")
    suspend fun getAsteroidData():Response<String>


}