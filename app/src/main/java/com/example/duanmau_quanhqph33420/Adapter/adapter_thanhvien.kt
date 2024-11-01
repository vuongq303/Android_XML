package com.example.duanmau_quanhqph33420.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.duanmau_quanhqph33420.Fragment.FragmentThanhVien
import com.example.duanmau_quanhqph33420.Object.thanhVien
import com.example.duanmau_quanhqph33420.R

class adapter_thanhvien(
    var context: Context,
    var list: ArrayList<thanhVien>,
    var fragment: FragmentThanhVien
) : RecyclerView.Adapter<adapter_thanhvien.rcv_thanhvien>() {
    class rcv_thanhvien(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var txt_matv: TextView
        lateinit var txt_tentv: TextView
        lateinit var txt_namsinh: TextView
        lateinit var imgbtn_xoa: ImageButton

        init {
            txt_matv = itemView.findViewById(R.id.matv_item)
            txt_tentv = itemView.findViewById(R.id.hotentv_item)
            txt_namsinh = itemView.findViewById(R.id.namsinhtv_item)
            imgbtn_xoa = itemView.findViewById(R.id.imgbtn_xoatv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rcv_thanhvien {
        return rcv_thanhvien(
            LayoutInflater.from(context).inflate(R.layout.thanhvien_item, null, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: rcv_thanhvien, i: Int) {
        holder.txt_matv.text = "Mã thành viên: " + list[i].matv
        holder.txt_tentv.text = "Tên thành viên: " + list[i].hotentv
        holder.txt_namsinh.text = "Năm sinh: " + list[i].namsinh
        holder.imgbtn_xoa.setOnClickListener {
            fragment.xoa(list[i].matv)
        }
        holder.itemView.setOnLongClickListener {
            fragment.sua(list[i].matv, list[i].hotentv, list[i].namsinh)
            false
        }
    }
}