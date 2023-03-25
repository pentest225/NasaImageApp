package ci.orange.nasaimageapp.main

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ci.orange.nasaimageapp.data.Asteroid
import ci.orange.nasaimageapp.data.AsteroidRepository
import ci.orange.nasaimageapp.data.remoute.RetrofitApi
import ci.orange.nasaimageapp.data.remoute.api.getNextSevenDaysFormattedDates
import ci.orange.nasaimageapp.data.remoute.api.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.random.Random

class MainViewModel() : ViewModel() {
    private val repository = AsteroidRepository()
    private val TAG = "MainViewModel"
    val defaultAsteroidList : MutableList<Asteroid> = mutableListOf()
    private val _imagesOfWeek : MutableLiveData<List<Asteroid>> = MutableLiveData(listOf())
    val imagesOfWeek : LiveData<List<Asteroid>> = _imagesOfWeek

    private val _loader :MutableLiveData<Boolean> = MutableLiveData(false)

    val visibility = Transformations.map(_loader){
        if(it) View.VISIBLE else View.GONE
    }

    init {
//        generateDefaultList()
        getAsteroidData()
    }

    private fun generateDefaultList(){
        for (i in 1..10){
            defaultAsteroidList.add(
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
    private fun getAsteroidData(){
        viewModelScope.launch {
            _loader.value = true
            val result = repository.getImageOfWeek()
            result.onSuccess {
                _imagesOfWeek.value = it
            }
            result.onFailure {
                //TODO SHOW ERROR
                Log.e(TAG, "getAsteroidData: Error $it")
            }
            _loader.value = false
        }
    }

}