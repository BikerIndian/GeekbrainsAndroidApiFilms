package net.svichch.geekbrains.films.nework.api.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie (

     @Expose val page: Int? = null,
     @Expose val results: List<Result>? = null,
     @Expose val totalPages: Int? = null,
     @Expose val totalResults: Int? = null,
     @Expose val additionalProperties: Map<String, Any> = HashMap()

)

data class Result(

     val adult: Boolean? = null,
     @SerializedName("backdrop_path")
     val backdropPath: String? = null,
     val genreIds: List<Int>? = null,
     val id: Long,
     val originalLanguage: String? = null,
     val originalTitle: String? = null,
     val overview: String? = null,
     val popularity: Double? = null,
     @SerializedName("poster_path")
     val posterPath: String? = null,
     @SerializedName("release_date")
     val releaseDate: String? = null,
     val title: String? = null,
     val video: Boolean? = null,
     @SerializedName("vote_average")
     val voteAverage: String? = null,
     val voteCount: Int? = null

)