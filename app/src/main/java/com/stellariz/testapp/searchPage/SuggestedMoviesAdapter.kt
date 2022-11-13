package com.stellariz.testapp.searchPage

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.stellariz.testapp.databinding.MovieSearchViewBinding
import com.stellariz.testapp.moviePage.MovieInfoActivity
import com.stellariz.testapp.model.MovieBriefViewData

class SuggestedMoviesAdapter(
    private var suggestedMovies: List<MovieBriefViewData>
) : RecyclerView.Adapter<MoviesSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesSearchViewHolder {
        val binding =
            MovieSearchViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesSearchViewHolder, position: Int) {
        holder.itemView.apply {
            val movieId = suggestedMovies[position].id
            Log.d(
                "LOADING_SUGGESTED_MOVIE",
                "Uploading: ${suggestedMovies[position].poster_path}..."
            )
            holder.movieTitle.text = suggestedMovies[position].original_title

            Picasso.get()
                .load("https://image.tmdb.org/t/p/w300${suggestedMovies[position].poster_path}")
                .into(holder.movieSmallPoster)

            setOnClickListener {
                Intent(holder.itemView.context, MovieInfoActivity::class.java).also {
                    it.putExtra("movieId", movieId)
                    holder.itemView.context.startActivity(it)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return suggestedMovies.size
    }


}