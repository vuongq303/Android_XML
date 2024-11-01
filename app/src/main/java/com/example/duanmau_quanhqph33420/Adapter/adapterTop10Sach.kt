package com.example.duanmau_quanhqph33420.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.duanmau_quanhqph33420.Fragment.FragmentTop10Sach
import com.example.duanmau_quanhqph33420.Object.top10
import com.example.duanmau_quanhqph33420.R

class adapterTop10Sach(
    var context: Context, var list: ArrayList<top10>, var fragment: FragmentTop10Sach
) : RecyclerView.Adapter<adapterTop10Sach.rcv_holder>() {
    class rcv_holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var txtTensach: TextView
        lateinit var txtSoluong: TextView

        init {
            txtTensach = itemView.findViewById(R.id.tensach_top10sach_item)
            txtSoluong = itemView.findViewById(R.id.soluong_top10sach_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rcv_holder {
        return rcv_holder(
            LayoutInflater.from(context).inflate(R.layout.top10_sach_item, null, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: rcv_holder, position: Int) {
        holder.txtTensach.text ="Tên sách: " +list[position].tensach
        holder.txtSoluong.text ="Số lượng: " +"${list[position].soluong}"
    }
}