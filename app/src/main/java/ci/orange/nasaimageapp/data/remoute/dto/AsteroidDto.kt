package ci.orange.nasaimageapp.data.remoute.dto


data class AsteroidDto(
    val id : String,
    val absolute_magnitude : Double,
    val estimated_diameter_max : Float,
    val is_potentially_hazardous_asteroid : Boolean,
    val close_approach_data:List<CloseApproachData>,

    )

data class CloseApproachData(
    val close_approach_date : String,
    val relative_velocity : RelativeVelocity,
    val miss_distance: MissDistance
        )

data class RelativeVelocity(val kilometers_per_second:String)

data class MissDistance(val astronomical:String,val lunar:String)
