package com.stellariz.testapp.holders

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stellariz.testapp.databinding.MovieBriefViewBinding

class MoviesBriefViewHolder (binding: MovieBriefViewBinding) : RecyclerView.ViewHolder(binding.movieBriefView){
    val movieTitle: TextView = binding.txtTitle
    val movieBigPoster: ImageView = binding.imgPoster
}