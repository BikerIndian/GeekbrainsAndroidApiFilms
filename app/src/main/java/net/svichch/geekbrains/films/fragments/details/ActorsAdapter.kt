package net.svichch.geekbrains.films.fragments.details

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import net.svichch.geekbrains.films.databinding.ActorBinding
import net.svichch.geekbrains.films.nework.api.movie.Cast
import net.svichch.geekbrains.films.nework.api.retrofit.RetrofitFilms
import net.svishch.android.outerspace.mvp.model.image.IImageLoader

class ActorsAdapter(
    private val values: List<Cast>,
    private val imageLoader: IImageLoader<ImageView>

) : RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(

            ActorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.name.text = item.name
        holder.loadImg(RetrofitFilms.BASE_URL_IMG + item.profilePath)

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ActorBinding) : RecyclerView.ViewHolder(binding.root) {

        val imageActor = binding.imageActor
        val name = binding.name

        fun loadImg(url: String) {
            imageLoader.loadInto(url, imageActor)
        }
    }

}