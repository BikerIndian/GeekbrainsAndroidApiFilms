package net.svichch.geekbrains.films.nework.api

import net.svichch.geekbrains.films.nework.retrofit.RetrofitNetwork

class ApiHolder {
    val api: IDataSource by lazy {
        RetrofitNetwork().getService("https://api.themoviedb.org").create(IDataSource::class.java)
    }
}