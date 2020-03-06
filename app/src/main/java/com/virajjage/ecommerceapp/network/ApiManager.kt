package com.virajjage.ecommerceapp.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.virajjage.ecommerceapp.models.ResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


object ApiManager {
    private var apiService: ApiService = ApiClient.getRetrofit().create(ApiService::class.java)
    var data: MutableLiveData<ResponseModel> = MutableLiveData()


    fun callGetProductsAPI(compositeDisposable: CompositeDisposable): MutableLiveData<ResponseModel> {
        compositeDisposable.add(
            apiService.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onResponse, this::onError)


        )
        return data

    }


    private fun onResponse(response: ResponseModel) {
        data.value = response
    }

    private fun onError(error: Throwable) {
        Log.d("Error", error.message.toString())
        data.value = null
    }

}

