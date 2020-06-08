package com.zyp.kotlin.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.aliyun.solution.longvideo.activity.AlivcHomeActivity
import com.zyp.kotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {


    var double_Array: DoubleArray = doubleArrayOf(1.3, 2.4, 55.3, 77.0)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn2.text = "点了2一下"

        btn1.setOnClickListener { toast("btn3") }

        //遍历数组
        var result: String = ""
        var i: Int = 0
        while (i < double_Array.size) {
            result += double_Array.get(i)
            i++
        }

        btn4.setOnClickListener {
            toast(result.toString())
        }

        /**
         * 控制语句  如 if-else  switch-case
         */
//        var change = true
        /**
         * 三元运算符已经取消
         * 但多路分支得到了加强
         */
        var count = 0;
        btn1.setOnClickListener {
            when (count) {
                0 -> btn1.setText("第1种")
                1 -> btn1.setText("第2种")
                2 -> btn1.setText("第3种")
                else -> btn1.setText("默认情况")
            }
            count = (count + 1) % 3;
        }

        btn2.setOnClickListener {
            btn2.text = when (count) {
                1, 3, 4, 5, 6, 7, 8 -> "001"
                2, 10, 11, 12, 13, 15 -> "002"
                else -> "默认"
            }

            count = if (count > 15) {
                0
            } else {
                count++
            }
        }

        //还可以根据类型来判断
        var type: Number
        btn3.setOnClickListener {
            count = (count + 1) % 3

            type = when (count) {
                0 -> count.toLong()
                1 -> count.toDouble()
                else -> count.toFloat()
            }

            btn3.text = when (type) {
                is Long -> "is Long "
                is Double -> "is Double"
                else -> ""
            }

        }

        btn1.onClick {
            var intent: Intent = Intent(this@MainActivity, SelfActivity::class.java)
            startActivity(intent)
        }

        btn6.onClick {
            var intent: Intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }

//      自定义Beaavior
        btn7.onClick {
            var intent: Intent = Intent(this@MainActivity, RecycleviewActivity::class.java)
            startActivity(intent)
        }

        btn8.onClick {
            var intent: Intent = Intent(this@MainActivity, FontActivity::class.java)
            startActivity(intent)
        }

        btn9.onClick {
            var intent: Intent = Intent(this@MainActivity, ViewActivity::class.java)
            startActivity(intent)
        }

        btn10.onClick {
            val intent = Intent(context, AlivcHomeActivity::class.java)
            context.startActivity(intent)
        }

    }
}
