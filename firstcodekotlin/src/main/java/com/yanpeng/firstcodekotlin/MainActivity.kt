package com.yanpeng.firstcodekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var androidViewModel = MainViewModel(application)
        androidViewModel.pageStatus.observe(this,object :Observer<Int>{
            override fun onChanged(t: Int?) {
                t?.let {
                    when(t){
                        0->{
//                          请求正常
                        }
                        1->{
//                          数据为空
                        }
                        2->{
//                          网络请求超时
                        }
                        else->{

                        }
                    }
                }
            }
        })

//      异或
        var arr = listOf(1,2,3,4,1,2,3)
        var result = arr[0]
        arr.forEach {

        }


        btn_toImage.onClick {
            startActivity(Intent(this@MainActivity,ImageActivity::class.java))
        }

    }

    var pageStatus =0
    fun changePageState(){
        when(pageStatus){
            1->{
//             正常页面
            }
            2->{
//              显示空布局
            }
        }
    }
}
