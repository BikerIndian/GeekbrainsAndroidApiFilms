package net.svichch.geekbrains.films.nework.api

import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Deferred
import net.svichch.geekbrains.films.nework.api.movie.Movie
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface IDataSource {

   @GET("3/discover/movie")
   fun getFilms(@Query("api_key") api_key: String,
                @Query("language") languageRus: String): Single<Movie>

}