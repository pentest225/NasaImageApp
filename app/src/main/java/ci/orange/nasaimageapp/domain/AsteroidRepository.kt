package ci.orange.nasaimageapp.domain

import ci.orange.nasaimageapp.domain.model.Asteroid
import ci.orange.nasaimageapp.domain.model.ImageOfToday


interface AsteroidRepository {
    suspend fun getGetAsteroidByDate(startData:String,endDate:String):Result<List<Asteroid>>
    suspend fun getNexWeekAsteroid():Result<List<Asteroid>>
    suspend fun getGetAsteroidOfWeek():Result<List<Asteroid>>
    suspend fun getAsteroidOfToday():Result<List<Asteroid>>
    suspend fun getImageOfToDay():Result<ImageOfToday>

    suspend fun getAllSavedAsteroid():Result<List<Asteroid>>

}