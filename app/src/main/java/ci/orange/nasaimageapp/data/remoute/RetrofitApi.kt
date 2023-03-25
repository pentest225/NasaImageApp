package ci.orange.nasaimageapp.data.remoute

import ci.orange.nasaimageapp.Constants
import ci.orange.nasaimageapp.data.remoute.api.AsteroidAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

object RetrofitApi {
    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(1,TimeUnit.MINUTES)
        .readTimeout(1,TimeUnit.MINUTES)
        .writeTimeout(1,TimeUnit.MINUTES)
        .build()

    val nasaApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .client(httpClient)
        .build()
        .create(AsteroidAPI::class.java)
}