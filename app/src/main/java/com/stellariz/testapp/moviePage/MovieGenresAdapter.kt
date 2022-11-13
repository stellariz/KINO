package com.stellariz.testapp.moviePage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stellariz.testapp.databinding.GenreItemViewBinding
import com.stellariz.testapp.model.Genre

class MovieGenresAdapter (private var movieGenres: List<Genre>)
    : RecyclerView.Adapter<GenresHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresHolder {
        val binding = GenreItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenresHolder(binding)
    }

    override fun onBindViewHolder(holder: GenresHolder, position: Int) {
       holder.itemView.apply {
           holder.genreName.text = movieGenres[position].name
       }
    }

    override fun getItemCount(): Int {
        return movieGenres.size
    }
}