package com.example.duanmau_quanhqph33420.Fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.duanmau_quanhqph33420.Adapter.adapter_thanhvien
import com.example.duanmau_quanhqph33420.Object.thanhVien
import com.example.duanmau_quanhqph33420.R
import com.example.duanmau_quanhqph33420.SQLDAO.thanhVienDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentThanhVien : Fragment() {
    lateinit var dao: thanhVienDAO
    lateinit var rcv_thanhvien: RecyclerView
    lateinit var adp: adapter_thanhvien
    lateinit var list: ArrayList<thanhVien>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_thanh_vien, container, false)
        rcv_thanhvien = view.findViewById(R.id.rcv_thanhvien)
        dao = thanhVienDAO(requireActivity())
        rcv_thanhvien.layoutManager = LinearLayoutManager(requireActivity())
        val fab_them: FloatingActionButton = view.findViewById(R.id.fab_themthanhvien)
        reload()
        fab_them.setOnClickListener {
            them()
        }
        return view
    }

    fun reload() {
        list = dao.getList()
        adp = adapter_thanhvien(requireActivity(), list, this)
        rcv_thanhvien.adapter = adp
    }

    fun them() {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        val view =
            LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_themtv, null, false)
        dialogBuilder.setView(view)
        val dialog = dialogBuilder.create()
        // val edt_matv: EditText = view.findViewById(R.id.dialogThem_edt_matv)
        val edt_tentv: EditText = view.findViewById(R.id.dialogThem_edt_tentv)
        val edt_namsinh: EditText = view.findViewById(R.id.dialogThem_edt_ngaytv)
        val btn_them: Button = view.findViewById(R.id.dialog_btn_Themtv)
        btn_them.setOnClickListener {
            val tentv = edt_tentv.text.toString()
            val namsinh = edt_namsinh.text.toString()
            if (tentv == "" || namsinh == "") {
                Toast.makeText(requireActivity(), "Dữ liệu trống!", Toast.LENGTH_SHORT).show()
            } else {
                dao.them(tentv, namsinh)
                Toast.makeText(requireActivity(), "Thêm thành viên thành công", Toast.LENGTH_SHORT)
                    .show()
                reload()
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    fun sua(matv_df: Int, tentv_df: String, namsinh_df: String) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        val view =
            LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_suatv, null, false)
        dialogBuilder.setView(view)
        val dialog = dialogBuilder.create()

        val edt_matv: EditText = view.findViewById(R.id.dialogSua_edt_matv)
        val edt_tentv: EditText = view.findViewById(R.id.dialogSua_edt_tentv)
        val edt_namsinh: EditText = view.findViewById(R.id.dialogSua_edt_ngaytv)
        val btn_sua: Button = view.findViewById(R.id.dialog_btn_Suatv)

        edt_matv.setText("$matv_df")
        edt_tentv.setText(tentv_df)
        edt_namsinh.setText(namsinh_df)

        btn_sua.setOnClickListener {
            val tentv = edt_tentv.text.toString()
            val namsinh = edt_namsinh.text.toString()
            if (tentv == "" || namsinh == "") {
                Toast.makeText(requireActivity(), "Dữ liệu trống!", Toast.LENGTH_SHORT).show()
            } else {
                dao.sua(matv_df, tentv, namsinh)
                Toast.makeText(requireActivity(), "Sửa thành viên thành công", Toast.LENGTH_SHORT)
                    .show()
                reload()
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    fun xoa(matv_df: Int) {
        val dialog = AlertDialog.Builder(requireActivity())
        with(dialog) {
            setTitle("Bạn có chắc chắn muốn xoá")
            setIcon(R.drawable.baseline_error_24)
            setPositiveButton("Có") { dialogInterface, i ->
                dao.xoa(matv_df)
                reload()
            }
            setNegativeButton("Không") { dialogInterface, i -> }
            create()
            show()
        }
    }
}
