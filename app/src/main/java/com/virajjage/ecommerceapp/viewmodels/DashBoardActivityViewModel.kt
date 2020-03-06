package com.virajjage.abl_test_viraj_jage.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.virajjage.ecommerceapp.models.ResponseModel
import com.virajjage.ecommerceapp.network.ApiManager
import io.reactivex.disposables.CompositeDisposable

class DashBoardActivityViewModel(application: Application) : AndroidViewModel(application) {

    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private lateinit var userListObservable: LiveData<ResponseModel>

    fun callProductListAPI(): LiveData<ResponseModel> {
        userListObservable = ApiManager.callGetProductsAPI(mCompositeDisposable)
        return userListObservable
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
    }

}