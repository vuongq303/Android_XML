package com.example.duanmau_quanhqph33420.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.duanmau_quanhqph33420.Fragment.FragmentPhieuMuon
import com.example.duanmau_quanhqph33420.Object.phieuMuon
import com.example.duanmau_quanhqph33420.R
import org.w3c.dom.Text

class adapter_phieuMuon(
    var context: Context, var list: ArrayList<phieuMuon>, var fragment: FragmentPhieuMuon
) : RecyclerView.Adapter<adapter_phieuMuon.rcv_holder>() {
    class rcv_holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var txtMapm: TextView
        lateinit var txtTentv: TextView
        lateinit var txtTensach: TextView
        lateinit var txtTienthue: TextView
        lateinit var txtTrasach: TextView
        lateinit var txtNgaythue: TextView
        lateinit var imgbtnXoaPm: ImageButton

        init {
            txtMapm = itemView.findViewById(R.id.maPhieuMuon_item)
            txtTentv = itemView.findViewById(R.id.tenThanhvienPhieuMuon_item)
            txtTensach = itemView.findViewById(R.id.tensachPhieuMuon_item)
            txtTienthue = itemView.findViewById(R.id.tienthuePhieuMuon_item)
            txtTrasach = itemView.findViewById(R.id.trasachPhieuMuon_item)
            txtNgaythue = itemView.findViewById(R.id.ngaythuePhieuMuon_item)
            imgbtnXoaPm = itemView.findViewById(R.id.imgbtn_xoaPhieuMuon_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rcv_holder {
        return rcv_holder(
            LayoutInflater.from(context).inflate(R.layout.quanly_phieu_muon_item, null, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: rcv_holder, position: Int) {
        holder.txtMapm.text = "Mã phiếu mượn: " + list[position].mapm
        holder.txtTentv.text = "Tên thành viên: " + list[position].tentv
        holder.txtTensach.text = "Tên sách: " + list[position].tensach
        holder.txtTienthue.text = "Tiền thuê: " + list[position].tienthue
        holder.txtTrasach.text = "${list[position].trasach}"
        holder.txtNgaythue.text = "Ngày thuê: " + list[position].ngaythue
        holder.itemView.setOnLongClickListener {
            fragment.sua(
                list[position].mapm,
                list[position].tentv,
                list[position].tensach,
                list[position].ngaythue,
                list[position].tienthue,
                list[position].trasach
            )
            false
        }
        holder.imgbtnXoaPm.setOnClickListener {
            fragment.xoa(list[position].mapm)
        }
    }
}