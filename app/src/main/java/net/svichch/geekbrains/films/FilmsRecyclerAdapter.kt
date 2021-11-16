package net.svichch.geekbrains.films

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import net.svichch.geekbrains.films.databinding.FragmentItemBinding
import net.svichch.geekbrains.films.nework.api.movie.Result
import net.svishch.android.outerspace.mvp.model.image.IImageLoader

class FilmsRecyclerAdapter(
    private val values: List<Result>,
    val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<FilmsRecyclerAdapter.ViewHolder>() {

    var BASE_URL_IMG = "https://image.tmdb.org/t/p/w500"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.tile.text = item.title
        holder.releaseDate.text = item.releaseDate
        holder.voteAverage.text = item.voteAverage
        holder.loadImg(BASE_URL_IMG + item.posterPath)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val tile = binding.title
        val releaseDate = binding.releaseDate
        val voteAverage = binding.vote
        val imgView = binding.ivImg

        override fun toString(): String {
            return super.toString() + " '" + tile.text + "'"
        }

        fun loadImg(url: String) {
            imageLoader.loadInto(url, imgView)
        }
    }
}