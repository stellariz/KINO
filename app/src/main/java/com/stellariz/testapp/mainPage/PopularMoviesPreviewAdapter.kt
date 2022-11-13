package com.stellariz.testapp.mainPage

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.stellariz.testapp.databinding.MovieBriefViewBinding
import com.stellariz.testapp.moviePage.MovieInfoActivity
import com.stellariz.testapp.model.MovieBriefViewData

class PopularMoviesPreviewAdapter(
    private var popularMovies: List<MovieBriefViewData>
) : RecyclerView.Adapter<MoviesBriefViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesBriefViewHolder {
        val binding = MovieBriefViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesBriefViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesBriefViewHolder, position: Int) {
        holder.itemView.apply {
            val movieId = popularMovies[position].id
            Log.d("IMAGE_LOADING_MAIN_MENU", "Uploading: ${popularMovies[position].poster_path}...")
            holder.movieTitle.text = popularMovies[position].original_title

            Picasso.get()
                .load("https://image.tmdb.org/t/p/w300${popularMovies[position].poster_path}")
                .into(holder.movieBigPoster)

            holder.movieBigPoster.setOnClickListener {

                Intent(it.context, MovieInfoActivity::class.java).also {
                        it.putExtra("movieId", movieId)
                        holder.itemView.context.startActivity(it)
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }

}