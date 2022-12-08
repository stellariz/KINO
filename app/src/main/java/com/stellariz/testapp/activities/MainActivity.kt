package com.stellariz.testapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stellariz.testapp.config.ApiTokenHolder
import com.stellariz.testapp.databinding.ActivityMainBinding
import com.stellariz.testapp.adapters.PopularMoviesPreviewAdapter
import com.stellariz.testapp.model.dto.MovieBriefViewData
import com.stellariz.testapp.service.TMDBService.tmdbService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var rvBriefMovieView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initRecyclerView()
        setSearchListener()
        loadData()
    }

    private fun initRecyclerView() {
        rvBriefMovieView = binding.rvBriefMovieView
        rvBriefMovieView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        val pbPopularMovieLoading = binding.pbPopularMovieLoading
        tmdbService.getPopularMovies(ApiTokenHolder.API_TOKEN)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { pbPopularMovieLoading.visibility = View.VISIBLE }
            .subscribe({
                pbPopularMovieLoading.visibility = View.INVISIBLE
                this.handleResponse(it.results)
            },
                { Log.d("ERROR", it.message.toString()) })
    }

    private fun setSearchListener() {
        val ivSearch = binding.ivSearch
        ivSearch.setOnClickListener {
            Intent(it.context, MovieSearchActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun handleResponse(moviePopularList: List<MovieBriefViewData>) {
        val adapter = PopularMoviesPreviewAdapter(moviePopularList)
        rvBriefMovieView.adapter = adapter
    }
}