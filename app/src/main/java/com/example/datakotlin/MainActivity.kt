package com.example.datakotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var realStateRecyclerAdapter: RealStateRecyclerAdapter
    lateinit var arrayList : ArrayList<RealState>
    lateinit var api : JsonPlaceholdeApi
    lateinit var realStateViewModel: RealStateViewModel
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recPost)
        realStateRecyclerAdapter = RealStateRecyclerAdapter()
        recyclerView.adapter = realStateRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        realStateViewModel = RealStateViewModel()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

          api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://mars.udacity.com/")
            .build()
            .create(JsonPlaceholdeApi::class.java)

            GetData()


    }

    private fun GetData(){
        GlobalScope.launch(Dispatchers.IO) {
            val res = api.getPost().execute()
            val list : List<RealState> = res.body()!!
            arrayList = ArrayList()
            for (item in list){
                val realState = RealState(item.price, item.id, item.type, item.img_src)
                arrayList.add(realState)
            }
            withContext(Dispatchers.Main){
                realStateViewModel.setMutableRealState(arrayList)
                realStateViewModel.mutableRealState.observe(this@MainActivity, object : Observer<ArrayList<RealState>>{
                    override fun onChanged(t: ArrayList<RealState>?) {
                        realStateRecyclerAdapter.setList(arrayList)
                    }
                })
            }

        }

    }
}