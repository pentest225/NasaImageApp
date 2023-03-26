package ci.orange.nasaimageapp.data.remoute.api

import ci.orange.nasaimageapp.utils.Constants
import ci.orange.nasaimageapp.data.remoute.dto.ImageOfToDayDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidAPI {
    @GET("planetary/apod?api_key=${Constants.API_KEY}")
    suspend fun getImageOfToDay():Response<ImageOfToDayDto>
    @GET("neo/rest/v1/feed?api_key=${Constants.API_KEY}")
    suspend fun getAsteroidData(@Query("start_date") startDate:String,@Query("end_date") endDate:String):Response<String>

}