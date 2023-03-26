package ci.orange.nasaimageapp.presentation.main

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import ci.orange.nasaimageapp.data.AsteroidRepositoryImpl

import ci.orange.nasaimageapp.data.remoute.api.getNextSevenDaysFormattedDates
import ci.orange.nasaimageapp.domain.model.Asteroid
import ci.orange.nasaimageapp.domain.model.ImageOfToday
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



private const val TAG = "MainViewModel"
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AsteroidRepositoryImpl(application)
    private val _imagesOfWeek : MutableLiveData<List<Asteroid>> = MutableLiveData(listOf())
    val imagesOfWeek : LiveData<List<Asteroid>> = _imagesOfWeek
    private val _toDaysImage : MutableLiveData<ImageOfToday> = MutableLiveData()
    val toDaysImage :LiveData<ImageOfToday> = _toDaysImage
    private val _loader :MutableLiveData<Boolean> = MutableLiveData(false)

    val visibility = Transformations.map(_loader){
        if(it) View.VISIBLE else View.GONE
    }

    init {
        getToDayImage()
        getTodayAsteroid()
    }

    private fun getToDayImage() {
        viewModelScope.launch {
            val response = repository.getImageOfToDay()
            response.onSuccess {
                _toDaysImage.value = it
                Log.i(TAG, "getToDayImage: ToDay $_toDaysImage")
            }
            response.onFailure {
                Log.e(TAG, "getToDayImage: Error to load today Image",it)
            }
        }
    }


    private fun getAsteroidData(){
        CoroutineScope(Dispatchers.IO).launch {
            _loader.postValue(true)
            val date = getNextSevenDaysFormattedDates()
            val result = repository.getGetAsteroidByDate(date.first(),date.last())
            result.onSuccess {
                _imagesOfWeek.postValue(it)
            }
            result.onFailure {
                //TODO SHOW ERROR
                Log.e(TAG, "getAsteroidData: Error $it")
            }
            _loader.postValue(false)
        }
    }

    private fun getTodayAsteroid(){
        CoroutineScope(Dispatchers.IO).launch {
            _loader.postValue(true)

            val result = repository.getAsteroidOfToday()
            result.onSuccess {
                _imagesOfWeek.postValue(it)
            }
            result.onFailure {
                //TODO SHOW ERROR
                Log.e(TAG, "getAsteroidData: Error $it")
            }
            _loader.postValue(false)
        }
    }



    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}
/**
 * Factory for constructing DevByteViewModel with parameter
 */
