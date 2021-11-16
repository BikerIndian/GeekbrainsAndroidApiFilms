package net.svichch.geekbrains.films.nework.api.movie

import com.google.gson.annotations.Expose

data class Movie (

     @Expose val page: Int? = null,
     @Expose val results: List<Result>? = null,
     @Expose val totalPages: Int? = null,
     @Expose val totalResults: Int? = null,
     @Expose val additionalProperties: Map<String, Any> = HashMap()

)