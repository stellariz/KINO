package com.stellariz.testapp.moviePage

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stellariz.testapp.databinding.GenreItemViewBinding

class GenresHolder(binding: GenreItemViewBinding) : RecyclerView.ViewHolder(binding.genreLayout) {
    val genreName: TextView = binding.tvGenre
}