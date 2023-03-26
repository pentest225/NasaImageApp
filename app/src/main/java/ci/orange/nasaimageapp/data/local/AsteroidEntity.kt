package ci.orange.nasaimageapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AsteroidEntity(
    @PrimaryKey val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)
