package com.yanpeng.firstcodekotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.yanpeng.firstcodekotlin.adapter.ImageAdapter
import com.yanpeng.firstcodekotlin.utils.FileUtil
import kotlinx.android.synthetic.main.activity_image.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast
import java.io.File
import kotlin.collections.ArrayList

class ImageActivity : AppCompatActivity() {

    var imagePathList:ArrayList<File> = ArrayList()
    var adapter =ImageAdapter(imagePathList)

    var WRITE_PERMISSION_CODE =1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        ImageRecycleview.adapter = adapter
        ImageRecycleview.layoutManager =LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        btn_selectFile.onClick {
//          打开本地文件，选择图片
            checkPermission()
        }

    }

    fun checkPermission(){
        var hasPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (hasPermission!=PackageManager.PERMISSION_GRANTED){
//          去申请权限
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),WRITE_PERMISSION_CODE)
        }else{
            selectPicFile()
        }
    }

    var CODE_SELECT_FILE =10
    fun selectPicFile(){
        var intent =Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("*/*")
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent,CODE_SELECT_FILE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CODE_SELECT_FILE->{
                //获取文件路径
                var uri:Uri? = data?.data
                if (uri!=null){
                    var filePathByUri = FileUtil.getFilePathByUri(this, uri)
                    var fileP =File(filePathByUri).parentFile
                    if (fileP.exists()){
                        //刷新list
                        imagePathList.clear()
                        if (fileP.isDirectory){
                            imagePathList.addAll(fileP.listFiles())
                            adapter.notifyDataSetChanged()
                        }else{
                            Log.i("image","获取到的x")
                        }
                    }
                }
            }
            else->{

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            WRITE_PERMISSION_CODE->{
                for (i in grantResults){
//                  权限统一
                    if(i == PackageManager.PERMISSION_GRANTED){
                       selectPicFile()
                    }else{
                        toast("请设置权限")
                    }
                }
            }
            else->{

            }
        }

    }
}