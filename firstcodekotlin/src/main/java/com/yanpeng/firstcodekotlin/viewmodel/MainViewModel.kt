package com.yanpeng.firstcodekotlin.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yanpeng.firstcodekotlin.base.KotlinApplication

/**
 * Created by zhangyanpeng on 2020/10/19
 */

 class MainViewModel(application: KotlinApplication) : AndroidViewModel(application) {

    fun constructor(){}

    val num: MutableLiveData<Int> = MutableLiveData()

}