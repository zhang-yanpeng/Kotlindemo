package com.chuange.kotlin.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.chuange.kotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    /**
     *
     * var 可变变量  val 不可变变量
     * 基本数据类型 Int Long Float Double Boolean Char String
     */
    var intMax: Int = Int.MAX_VALUE
    var intMin: Int = Int.MIN_VALUE

    var numLong: Long = 20
    var numLongMax: Long = Long.MAX_VALUE
    var numLongMmin: Long = Long.MIN_VALUE

    var floatMax: Float = Float.MAX_VALUE
    var floatMin: Float = Float.MIN_VALUE

    var doubleMax: Double = Double.MAX_VALUE
    var doubleMin: Double = Double.MIN_VALUE

    var exits: Boolean = true

    var cha: Char = '1'

    var str: String = "1212"

    /**
     * 数组
     * 除去string类型数组写法特殊，其他的基本类型都是一样的
     */
    var int_array: IntArray = intArrayOf(1, 2, 3, 4)
    var long_Array: LongArray = longArrayOf(1, 2, 3, 4, 5, 67, 8)
    var float_Array: FloatArray = floatArrayOf(0.1f, 1.4f)
    var double_Array: DoubleArray = doubleArrayOf(1.3, 2.4, 55.3, 77.0)

    var string_Array: Array<String> = arrayOf("天空", "海洋", "飞鸟", "猛兽")

    /**
     * 容器
     */
    var string_list: List<String> = listOf("天空", "海洋", "飞鸟", "猛兽")

    /**
     * set 只读集合
     * MutableSet 可变集合
     */
    var testSet: Set<String> = setOf("set1", "set2")
    var testSet2: MutableSet<String> = mutableSetOf("set1", "set2")


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setText("点了1一下")

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
         *  对字符串进行操作
         *  indexOf  包含
         *  substring 截取
         *
         *  字符串模板
         *  &str
         *
         */
        var testString: String = "121n1oi1eld.;sq,.,[12k121-02"
        if (testString.indexOf('.') > 0) {
//            testString.sub
        }

        btn4.text = "将按钮1 的text赋值给btn4：${btn1.text}"

        /**
         * 容器
         * 包含list，map，set
         */
//      for-in 循环
        var desc: String = ""
        for (item in testSet) {
            desc = "$item\n"
        }

//      迭代器遍历
        var iterator = testSet2.iterator()
        while (iterator.hasNext()) {
            var item = iterator.next()
            desc = "$desc 名称：$item\n"
        }

//      for each 遍历 内部使用it来指代每个item
        testSet2.forEach { desc = "$desc 名称：$it \n" }

        /**
         * list 只读
         * mutablelist 可变  增add 删removeAt 改set  都是在此list中进行
         * 遍历方式也是三种*
         *
         * sort 升序  sortBy 规则升序
         *
         *
         */

        var testList = listOf<String>("li1", "li2", "li3", "li4")

        testList.sortedBy { it.length }


        var testList2 = mutableListOf("天空", "海洋", "飞鸟", "猛兽")

        testList2.add("add1")
        testList2.add("add1")
        testList2.add("add1")

        testList2[2] = "改了"
//      删除
        testList2.removeAt(1)
        testList2.set(3, "不是飞鸟")
//      排序 sortBy 后面跟着的事排序规则
        testList2.sort()//升序
        testList2.sortBy { it.length }//按长度 升序

        testList2.sortDescending()//降序
        testList2.sortByDescending { it.length }//按长度降序

        /**
         * 映射 Map 采用key-value的方式
         * 初始值有两种方式
         * key to value
         * Pair(key,value)
         *
         * 遍历方式有三种 for in / for each / 迭代器
         * 但是于list又有稍许不同
         */
        var testMap = mapOf<String, String>("华为" to "Mate 20 Pro", "苹果" to "IPhone X", Pair("小米", "小米9"))

        var mapString = ""
        for (item in testMap) {
            mapString = "$mapString 厂商：${item.key} 名称：${item.value}"
        }

        val iterMap = testMap.iterator()
        mapString = ""
        while (iterMap.hasNext()) {
            val next = iterMap.next()
            mapString = "$mapString 厂商：${next.key} 名称：${next.value}"
        }

        testMap.forEach { key, value -> mapString = "厂商：$key  名称：$value" }

        /**
         * 控制语句  如 if-else  switch-case
         */
        var change = true
        btn5.setOnClickListener {
            btn5.text = if (change == true) {
                "凉风有信"
            } else {
                "秋月无边"
            }
            change = !change
        }

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

    }
}
