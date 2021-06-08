import com.example.myweather2.network.OneCallAPI
import com.example.myweather2.network.CurrentWeather
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface OpenWeatherApiService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
    ): CurrentWeather

    @GET("onecall")
    suspend fun oneCallAPI(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
    ): OneCallAPI
}

object OpenWeatherApi {
    val retrofitService: OpenWeatherApiService by lazy {
        retrofit.create(OpenWeatherApiService::class.java)
    }
}
