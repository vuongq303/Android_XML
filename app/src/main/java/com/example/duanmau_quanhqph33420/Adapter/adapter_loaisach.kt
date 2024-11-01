package com.example.duanmau_quanhqph33420.Fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.duanmau_quanhqph33420.Object.LoaiSach
import com.example.duanmau_quanhqph33420.R

class adapter_loaisach(
    var context: Context?,
    var list: ArrayList<LoaiSach>,
    var fragment: Fragment_LoaiSach
) :
    RecyclerView.Adapter<adapter_loaisach.rcv_adp>() {

    class rcv_adp(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_maloai: TextView
        val txt_tenloai: TextView
        val imgbtn_del: ImageButton

        init {
            txt_maloai = itemView.findViewById(R.id.maloaisach_item)
            txt_tenloai = itemView.findViewById(R.id.tenloaisach_item)
            imgbtn_del = itemView.findViewById(R.id.delloaisach_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rcv_adp {
        return rcv_adp(LayoutInflater.from(context).inflate(R.layout.loaisach_item, null, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: rcv_adp, position: Int) {
        holder.txt_maloai.text = "Mã loại: " + list[position].maloai.toString()
        holder.txt_tenloai.text = "Tên loại: " + list[position].tenloai
        holder.imgbtn_del.setOnClickListener {
            fragment.xoa(list[position].maloai)
        }
        holder.itemView.setOnLongClickListener {
            fragment.sua(
                list[position].maloai,
                list[position].tenloai
            )
            false
        }
    }
}