package com.sword.demo.network

import com.sword.demo.network.models.Breed
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/breeds")
    fun getBreeds(
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null
    ): Observable<List<Breed>>

    @GET("/breeds/search")
    fun searchBreed(
        @Query("q") name: String
    ): Observable<List<Breed>>
}