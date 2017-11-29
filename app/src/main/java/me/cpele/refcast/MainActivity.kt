package me.cpele.refcast

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.cpele.refcast.databinding.ActivityMainBinding
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)

        val interceptor: (Interceptor.Chain) -> okhttp3.Response = {
            val request = it.request()
            val url = request.url()
                    .newBuilder()
                    .addQueryParameter("APPID", BuildConfig.OW_APPID)
                    .build()
            val reqWithAppId = request.newBuilder().url(url).build()
            it.proceed(reqWithAppId)
        }

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        val service = retrofit.create(WeatherService::class.java)

        service.findWeather().enqueue(object : Callback<Weather> {

            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                TODO("not implemented")
            }

            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                response?.body()?.main?.temp?.apply { binding.data = MainData(toString()) }
            }
        })
    }
}

interface WeatherService {
    @GET("/data/2.5/weather?q=London,uk")
    fun findWeather(): Call<Weather>
}

data class Weather(val main: WeatherMain)

data class WeatherMain(val temp: Double)