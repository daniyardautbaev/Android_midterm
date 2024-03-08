package com.example.aviatickets.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aviatickets.R
import com.example.aviatickets.databinding.ActivityMainBinding
import com.example.aviatickets.fragment.OfferListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_view, OfferListFragment.newInstance())
            .commit()

    }
    interface ApiService {
        @GET("your_endpoint")
        fun getOffers(): Call<List<Offer>>
    }
    val retrofit = Retrofit.Builder()
        .baseUrl("BASE_URL") // Установите базовый URL
        .addConverterFactory(GsonConverterFactory.create()) // Используйте Gson для сериализации данных
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    apiService.getOffers().enqueue(object : Callback<List<Offer>> {
        override fun onResponse(call: Call<List<Offer>>, response: Response<List<Offer>>) {
            if (response.isSuccessful) {
                // Обновление UI с полученными данными
                response.body()?.let { offers ->
                    // Передайте список offers в адаптер RecyclerView
                }
            }
        }

        override fun onFailure(call: Call<List<Offer>>, t: Throwable) {
            // Обработка ошибки запроса
        }
    })

}