package com.stellariz.testapp.searchPage

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stellariz.testapp.R
import com.stellariz.testapp.config.ApiTokenHolder
import com.stellariz.testapp.databinding.MovieSearchActivityBinding
import com.stellariz.testapp.model.MovieBriefViewData
import com.stellariz.testapp.tmdbService.TMDBService.tmdbService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieSearchActivity : AppCompatActivity() {
    private lateinit var binding: MovieSearchActivityBinding

    private lateinit var rvMovieSearchResult: RecyclerView
    private lateinit var svMovieSearch: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieSearchActivityBinding.inflate(layoutInflater)
        val view = binding.layoutMovieSearch
        setContentView(view)
        initRecyclerView()
        initSearchListener()
    }

    private fun initRecyclerView(){
        rvMovieSearchResult = binding.rvMovieSearchResult
        rvMovieSearchResult.layoutManager =
            LinearLayoutManager(this)
    }

    private fun initSearchListener(){
        svMovieSearch = binding.svMovieSearch
        svMovieSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            @SuppressLint("CheckResult")
            override fun onQueryTextSubmit(movieTitle: String?): Boolean {
                return onQueryTextChange(movieTitle)
            }


            @SuppressLint("CheckResult")
            override fun onQueryTextChange(movieTitle: String?): Boolean {
                if (movieTitle == null || movieTitle.length < 2){
                    return false
                }
                tmdbService.getMovieByName(ApiTokenHolder.API_TOKEN, movieTitle)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { this@MovieSearchActivity.updateSuggestedList(it.results) }
                return true
            }
        })
    }

    private fun updateSuggestedList(response: List<MovieBriefViewData>){
        Log.d("UPDATING LIST", "List size: ${response.size}")
        val adapter = SuggestedMoviesAdapter(response)
        rvMovieSearchResult.adapter = adapter
    }
}