package net.svichch.geekbrains.films.fragments.films

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import net.svichch.geekbrains.films.databinding.FragmentItemBinding
import net.svichch.geekbrains.films.nework.api.movie.Result
import net.svichch.geekbrains.films.nework.api.retrofit.RetrofitFilms
import net.svishch.android.outerspace.mvp.model.image.IImageLoader

class FilmsAdapter(
    private val values: List<Result>,
    private val imageLoader: IImageLoader<ImageView>,
    private val adapterInFun: AdapterFilmsRecyclerFunctional

) : RecyclerView.Adapter<FilmsAdapter.ViewHolder>() {

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
        holder.loadImg(RetrofitFilms.BASE_URL_IMG + item.posterPath)

        holder.itemFilm.setOnClickListener {
            adapterInFun.toFilmInfoFragment(item)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val itemFilm = binding.itemFilm
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

    interface AdapterFilmsRecyclerFunctional {
        fun toFilmInfoFragment(film: Result)
    }
}