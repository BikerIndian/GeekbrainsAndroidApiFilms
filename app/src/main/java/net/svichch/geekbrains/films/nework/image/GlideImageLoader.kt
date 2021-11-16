package net.svishch.android.outerspace.mvp.model.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import net.svichch.geekbrains.films.R


class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {

        Glide.with(container.context)
            .load(url)
            .thumbnail(0.5f)
            .fitCenter()
            .error(R.drawable.ic_not_image)
            .into(container)

    }
}