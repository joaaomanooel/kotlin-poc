package com.example.kotlinpoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var model: GithubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        floatingActionButton.setOnClickListener {
            Snackbar.make(it, R.string.salve, 2000).show()
            CoroutineScope(Dispatchers.IO).launch {
                getRepos()
            }
        }
    }

    suspend fun getRepos() {
        val githubService = GithubFactory.makeRetrofitService()
        val res = githubService.getRepos().body()

        withContext(Dispatchers.Main) {
            res?.forEach {
                Log.d("Response:", it.name)
            }
        }
    }
}