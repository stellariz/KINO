package com.stellariz.testapp.searchPage

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stellariz.testapp.databinding.MovieSearchViewBinding

class MoviesSearchViewHolder(binding: MovieSearchViewBinding) :
    RecyclerView.ViewHolder(binding.layoutMovieSearchView) {
    val movieTitle: TextView = binding.tvMovieTitle
    val movieSmallPoster: ImageView = binding.ivMovieSearchPoster
}