package ci.orange.nasaimageapp.main

import androidx.lifecycle.ViewModel
import ci.orange.nasaimageapp.data.Asteroid
import kotlin.random.Random

class MainViewModel : ViewModel() {

    val defautAsteoidList : MutableList<Asteroid> = mutableListOf()

    init {
        generateDefaultList()
    }

    private fun generateDefaultList(){
        for (i in 1..10){
            defautAsteoidList.add(
                Asteroid(
                    id = (i+1).toLong(),
                    codename = "Code Name $i",
                    closeApproachDate = "21/12/2021",
                    estimatedDiameter = Random.nextDouble()*i,
                    absoluteMagnitude = Random.nextDouble()*i+1,
                    distanceFromEarth = Random.nextDouble()*i,
                    isPotentiallyHazardous = i.mod(2) == 0,
                    relativeVelocity = Random.nextDouble()*i
                )
            )
        }
    }

}