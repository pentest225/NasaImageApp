package ci.orange.nasaimageapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AsteroidEntity::class], version = 1)
abstract class AsteroidDataBase:RoomDatabase(){
    abstract val asteroidDao : AsteroidDao
}

private lateinit var INSTANCE :AsteroidDataBase


fun getInstance(context: Context):AsteroidDataBase {
    synchronized(AsteroidDataBase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext,AsteroidDataBase::class.java,"asteroid")
                .build()
        }
    }
    return INSTANCE
}
