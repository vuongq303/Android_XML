package com.example.duanmau_quanhqph33420.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.duanmau_quanhqph33420.Object.LoaiSach
import com.example.duanmau_quanhqph33420.R
import com.example.duanmau_quanhqph33420.SQLDAO.loaiSachDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Fragment_LoaiSach : Fragment() {
    lateinit var list: ArrayList<LoaiSach>
    lateinit var fabThem: FloatingActionButton
    lateinit var rcv_loaisach: RecyclerView
    lateinit var dao: loaiSachDAO
    lateinit var adap: adapter_loaisach

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment__loai_sach, container, false)
        fabThem = view.findViewById(R.id.fab_themloaisach)
        rcv_loaisach = view.findViewById(R.id.rcv_Loaisach)
        dao = loaiSachDAO(requireActivity())
        rcv_loaisach.layoutManager = LinearLayoutManager(requireActivity())
        realoadData()

        fabThem.setOnClickListener {
            them()
        }
        return view
    }

    fun realoadData() {
        list = dao.getData()
        adap = adapter_loaisach(
            requireActivity(), list,
            this
        )
        rcv_loaisach.adapter = adap
    }

    fun them() {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        val view = LayoutInflater.from(requireActivity())
            .inflate(
                R.layout.dialog_themloaisach,
                null, false
            )
        dialogBuilder.setView(view)
//        val edt_maloai: EditText = view.findViewById(R.id.dialogThem_edt_maloaisach)
        val edt_tenloai: EditText = view.findViewById(R.id.dialogThem_edt_tenloaisach)
        val btn_them: Button = view.findViewById(R.id.dialog_btn_themloaisach)
        val dialog = dialogBuilder.create()

        btn_them.setOnClickListener {
            if (edt_tenloai.text.toString() == "") {
                Toast.makeText(
                    requireActivity(), "Tên loại sách trống",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                dao.addLoaisach(edt_tenloai.text.toString().trim())
                realoadData()
                Toast.makeText(
                    requireActivity(), "Thêm loại sách thành công",
                    Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()
            }
        }
        dialog.show()

    }

    fun sua(maloai: Int, tenloai: String) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        val view = LayoutInflater.from(requireActivity())
            .inflate(
                R.layout.dialog_sualoaisach,
                null, false
            )
        dialogBuilder.setView(view)
        val edt_maloai: EditText = view.findViewById(R.id.dialogSua_edt_maloaisach)
        val edt_tenloai: EditText = view.findViewById(R.id.dialogSua_edt_tenloaisach)
        val btn_sua: Button = view.findViewById(R.id.dialog_btn_sualoaisach)
        val dialog = dialogBuilder.create()
        edt_maloai.setText("$maloai")
        edt_tenloai.setText(tenloai)

        btn_sua.setOnClickListener {
            if (edt_tenloai.text.equals("")) {
                Toast.makeText(
                    requireActivity(), "Tên loại sách trống",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                dao.updateLoaisach(maloai, edt_tenloai.text.toString().trim())
                realoadData()
                Toast.makeText(
                    requireActivity(), "sửa loại sách thành công",
                    Toast.LENGTH_SHORT
                ).show()
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    fun xoa(maloai: Int) {
        val dialog = AlertDialog.Builder(requireActivity())
        with(dialog) {
            setTitle("Bạn có chắc chắn muốn xoá")
            setIcon(R.drawable.baseline_error_24)
            setPositiveButton("Có") { dialogInterface, i ->
                dao.deleteLoaisach(maloai)
                Toast.makeText(
                    requireActivity(), "Xóa loại sách thành công",
                    Toast.LENGTH_SHORT
                ).show()
                realoadData()
            }
            setNegativeButton("Không") { dialogInterface, i -> }
            create()
            show()
        }
    }
}