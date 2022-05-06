package com.example.mygraphql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo3.exception.ApolloException
import com.example.mygraphql.adapter.MyAdapter


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TAG = "TAG"

        lifecycleScope.launchWhenResumed {
            val response = apolloClient.query(CharactersListQuery()).execute()
            Log.d(TAG, "onCreate: ------------------SUCCESS ${response.data}")
        }

        lifecycleScope.launchWhenResumed {
            val response = try {
                apolloClient.query(CharactersListQuery()).execute()
            } catch (e: ApolloException){
                Log.d(TAG, "onCreate: Failture ==== Apollo: ")
                null
            }
            val characters = response?.data?.characters?.results?.filterNotNull()
            if (characters != null && !response.hasErrors()){
                val adapter = MyAdapter(characters)
                val recyclerView = findViewById<RecyclerView>(R.id.list)
                recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                recyclerView.adapter = adapter
                Toast.makeText(this@MainActivity, "SIZE ${characters.size}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}