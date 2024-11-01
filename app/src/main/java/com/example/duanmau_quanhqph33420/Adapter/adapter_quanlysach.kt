package com.example.duanmau_quanhqph33420.Fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.duanmau_quanhqph33420.Object.Sach
import com.example.duanmau_quanhqph33420.R

class adapter_quanlysach(
    var context: Context?,
    var list: ArrayList<Sach>,
    var fragment: Fragment_quanlySach
) :
    RecyclerView.Adapter<adapter_quanlysach.rcv_sach>() {
    class rcv_sach(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var masach: TextView
        var tensach: TextView
        var giathue: TextView
        var loaisach: TextView
        var btn_del: ImageButton

        init {
            masach = itemView.findViewById(R.id.masach_item)
            tensach = itemView.findViewById(R.id.tensach_item)
            giathue = itemView.findViewById(R.id.giathue_item)
            loaisach = itemView.findViewById(R.id.loaisach_item)
            btn_del = itemView.findViewById(R.id.delsach_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rcv_sach {
        return rcv_sach(LayoutInflater.from(context).inflate(R.layout.quanlysach_item, null, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: rcv_sach, position: Int) {
        holder.masach.text = "Mã sách: " + list[position].masach
        holder.tensach.text = "Tên sách: " + list[position].tensach
        holder.giathue.text = "Giá thuê: " + list[position].giathue
        holder.loaisach.text = "Loại sách: " + list[position].loaisach
        //=> string dc truy van tu list

        holder.itemView.setOnLongClickListener {
            fragment.sua(
                list[position].masach,
                list[position].loaisach,
                list[position].tensach,
                list[position].giathue
            )
            false
        }
        holder.btn_del.setOnClickListener {
            fragment.xoa(list[position].masach)
        }
    }
}