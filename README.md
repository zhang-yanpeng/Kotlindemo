# Kotlindemo
此项目主要用于kotlin学习以及demo展示  
后续会逐渐完善
创建于2019 08 17  

新增Module：Kotlin 用于展示kotlin的基础用法
主Module   用于最后编写demo


1203  Recycleview的使用
-----------------
重点 适配器（涉及到类的继承）：
class KtAdapter(var mContext: Context, var mList: ArrayList<People>) : RecyclerView.Adapter<KtAdapter.KTholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KTholder {
        return KTholder(LayoutInflater.from(mContext).inflate(R.layout.item_people, parent, false))
//        return KTholder(View.inflate(mContext, R.layout.item_people,null))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: KTholder, position: Int) {
        holder.age.text = mList.get(position).age
        holder.name.text = mList.get(position).name
        holder.sex.text = mList.get(position).sex
    }

    class KTholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.tv_name
        var age: TextView = itemView.tv_age
        var sex: TextView = itemView.tv_sex
    }
}
