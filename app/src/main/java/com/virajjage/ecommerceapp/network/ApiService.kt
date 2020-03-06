package com.virajjage.ecommerceapp.network

import com.virajjage.ecommerceapp.models.ResponseModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("json")
    fun getUsers(): Observable<ResponseModel>
}