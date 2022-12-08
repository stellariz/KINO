package com.stellariz.testapp.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.like.LikeButton
import com.like.OnLikeListener
import com.squareup.picasso.Picasso
import com.stellariz.testapp.adapters.MovieGenresAdapter
import com.stellariz.testapp.config.ApiTokenHolder
import com.stellariz.testapp.dao.LikedMovieDao
import com.stellariz.testapp.databases.LikedMovieDatabase
import com.stellariz.testapp.databinding.MovieInfoActivityBinding
import com.stellariz.testapp.entities.LikedMovie
import com.stellariz.testapp.model.dto.MovieFullData
import com.stellariz.testapp.service.TMDBService.tmdbService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieInfoActivity : AppCompatActivity() {

    private lateinit var binding: MovieInfoActivityBinding

    private lateinit var movieImage: ImageView
    private lateinit var movieTitle: TextView
    private lateinit var movieTagline: TextView
    private lateinit var movieOverview: TextView
    private lateinit var rvMovieGenres: RecyclerView
    private lateinit var lbLike: LikeButton

    private lateinit var db : LikedMovieDatabase
    private lateinit var likedMovieDao: LikedMovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieInfoActivityBinding.inflate(layoutInflater)
        val view = binding.layoutMovieInfo
        setContentView(view)
        initMovieView()
        setOnClickLikeListener()
        initRecyclerViewGenres()
        loadMovieInfo()
    }

    private fun initMovieView() {
        movieImage = binding.ivMoviePoster
        movieTitle = binding.txtMovieTitle
        movieTagline = binding.txtMovieTagline
        movieOverview = binding.txtMovieDescription
    }

    private fun initRecyclerViewGenres() {
        rvMovieGenres = binding.rvMovieGenres
        rvMovieGenres.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setOnClickLikeListener() {
        val movieId = intent.getIntExtra("movieId", -1)
        lbLike = binding.likeButton
        db = Room.databaseBuilder(
            applicationContext,
            LikedMovieDatabase::class.java, "liked_movies"
        ).allowMainThreadQueries().build()
        likedMovieDao = db.likedMovieDao()
        lbLike.isLiked = likedMovieDao.isMovieLiked(movieId)
        lbLike.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton?) {
                Log.d("DB", "Inserting movie with [id]: $movieId")
                likedMovieDao.insertAll(LikedMovie(movieId))
            }

            override fun unLiked(likeButton: LikeButton?) {
                Log.d("DB", "Deleting movie with [id]: $movieId")
                likedMovieDao.deleteLikedMovie(LikedMovie(movieId))
            }
        })
    }

    @SuppressLint("CheckResult")
    private fun loadMovieInfo() {
        val movieId = intent.getIntExtra("movieId", -1)
        Log.d("API_REQUEST", "Sending API request with movie id: $movieId")
        tmdbService.getMovieById(ApiTokenHolder.API_TOKEN, movieId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                this.handleResponse(it)
            },
                { Log.d("ERROR", it.message.toString()) })
    }

    private fun handleResponse(movieInfo: MovieFullData) {
        Log.d("API_REQUEST", "Got response: $movieInfo")
        Picasso.get().load("https://image.tmdb.org/t/p/w300${movieInfo.poster_path}")
            .into(movieImage)
        movieTitle.text = movieInfo.original_title
        movieTagline.text = movieInfo.tagline
        movieOverview.text = movieInfo.overview
        val adapter = MovieGenresAdapter(movieInfo.genres)
        rvMovieGenres.adapter = adapter
    }
}