package ci.orange.nasaimageapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroidList:AsteroidEntity)

    @Query("SELECT * FROM AsteroidEntity where closeApproachDate=:date")
    fun getImagesByDate(date:String):List<AsteroidEntity>

    @Query("SELECT * FROM AsteroidEntity")
    fun getAllSavedAsteroid():List<AsteroidEntity>

}