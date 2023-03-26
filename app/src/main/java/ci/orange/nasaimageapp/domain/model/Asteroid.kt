package ci.orange.nasaimageapp.domain.model

import android.os.Parcelable
import ci.orange.nasaimageapp.data.local.AsteroidEntity
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Asteroid(val id: Long,
                    val codename: String,
                    val closeApproachDate: String,
                    val absoluteMagnitude: Double, val estimatedDiameter: Double,
                    val relativeVelocity: Double, val distanceFromEarth: Double,
                    val isPotentiallyHazardous: Boolean) : Parcelable


fun Asteroid.toAsteroidEntity():AsteroidEntity{
    return AsteroidEntity(id,codename, closeApproachDate, absoluteMagnitude, estimatedDiameter, relativeVelocity, distanceFromEarth, isPotentiallyHazardous)
}

fun AsteroidEntity.toAsteroid():Asteroid{
    return Asteroid(id, codename, closeApproachDate, absoluteMagnitude, estimatedDiameter, relativeVelocity, distanceFromEarth, isPotentiallyHazardous)
}