package net.svichch.geekbrains.films.nework.api.movie

import com.google.gson.annotations.SerializedName

data class Actors(
    val id: Long,
    val cast: List<Cast>,
)

data class Cast(
    val id: Long = -1,
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String? = null,
)