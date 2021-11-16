package net.svichch.geekbrains.films.nework.api.retrofit

import io.reactivex.rxjava3.core.Single
import net.svichch.geekbrains.films.nework.api.movie.Movie


interface IFilms {

    fun getFilms(): Single<Movie>

}