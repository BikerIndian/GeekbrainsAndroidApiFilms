package net.svichch.geekbrains.films.nework.api.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import net.svichch.geekbrains.films.nework.api.IDataSource
import net.svichch.geekbrains.films.nework.api.movie.Actors
import net.svichch.geekbrains.films.nework.api.movie.Movie

class RetrofitFilms(private val api: IDataSource) : IFilms {
    companion object {
        private val API_KEY: String = "274f828ad283bd634ef4fc1ee4af255f"
        private val LANGUAGE: String = "ru"
        var BASE_URL_IMG = "https://image.tmdb.org/t/p/w500"
    }

    override fun getFilms(): Single<Movie>  = api.getFilms(API_KEY,LANGUAGE).subscribeOn(Schedulers.io())
    override fun getActors(movieID: Long): Single<Actors> {
        return api.getActors(movieID,API_KEY,LANGUAGE).subscribeOn(Schedulers.io())
    }

}