package com.yanpeng.firstcodekotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by zhangyanpeng on 2020/11/16
 */
class MainViewModel(appliation:Application):AndroidViewModel(appliation) {
    var pageStatus = MutableLiveData<Int>()
    fun getNet(){
//        假装 发起了一个网络请求，接收回调
        service.mineHome()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<T>() {
                    override fun onSuccess(baseResponse:T?) {
//                      网络请求成功
                        pageStatus.value = baseResponse.code
                    }

                    override fun onFailure(errcode: Int, errMsg: String?) {
//                      网络请求失败
                        pageStatus.value =errcode
                    }
                })
    }
}