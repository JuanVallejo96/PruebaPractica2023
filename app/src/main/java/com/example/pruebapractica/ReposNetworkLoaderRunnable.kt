package com.example.pruebapractica
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReposNetworkLoaderRunnable(
    private val onReposLoadedListener: OnReposLoadedListener
): Runnable {

    override fun run() {
        // Retrofit instantiation and asynchronous call
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.cheapshark.com/api/1.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(DealService::class.java)

        try {
            val deals = service.listDeals("1", "15").execute().body()
            AppExecutors.instance?.mainThread()?.execute {
                if (deals != null) {
                    onReposLoadedListener.onReposLoaded(deals)
                } else {
                    Log.d("HELLO", "ISNULL")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        object : retrofit2.Callback<List<OfferModel>> {
            override fun onResponse(call: retrofit2.Call<List<OfferModel>>, response: retrofit2.Response<List<OfferModel>>) {
                val deals = response.body()
                AppExecutors.instance?.mainThread()?.execute {
                    onReposLoadedListener.onReposLoaded(deals!!)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<OfferModel>>, t: Throwable) {
                t.printStackTrace()
            }
        }

        // Llamada al Listener con los datos obtenidos
    }
}