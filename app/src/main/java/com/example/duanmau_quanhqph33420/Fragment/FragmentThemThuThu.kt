package com.example.duanmau_quanhqph33420.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.duanmau_quanhqph33420.R
import com.example.duanmau_quanhqph33420.SQLDAO.thuThuDAO
import com.google.android.material.textfield.TextInputEditText

class FragmentThemThuThu : Fragment() {
    lateinit var dao: thuThuDAO
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_them_thu_thu, container, false)
        dao = thuThuDAO(requireActivity())

        val edtTendangnhap: TextInputEditText = v.findViewById(R.id.tendangnhap_thuthu)
        val edt_hoten: TextInputEditText = v.findViewById(R.id.hoten_thuthu)
        val edt_mk: TextInputEditText = v.findViewById(R.id.matkhau_thuthu)
        val edt_re_mk: TextInputEditText = v.findViewById(R.id.re_matkhau_thuthu)
        val btn_luu: Button = v.findViewById(R.id.btn_luu_thuthu)
        val btn_huy: Button = v.findViewById(R.id.btn_huy_thuthu)

        btn_luu.setOnClickListener {
            val tenlog = edtTendangnhap.text.toString().trim()
            val hoten = edt_hoten.text.toString().trim()
            val mk = edt_mk.text.toString().trim()
            val re_mk = edt_re_mk.text.toString().trim()

            if (tenlog == "" || hoten == "" || mk == "" || re_mk == "") {
                Toast.makeText(requireActivity(), "Dữ liệu trống", Toast.LENGTH_SHORT).show()
            } else if (edt_mk.text.toString() != edt_re_mk.text.toString()) {
                Toast.makeText(requireActivity(), "Mật khẩu không khớp", Toast.LENGTH_SHORT)
                    .show()
            } else {
                dao.them(tenlog, hoten, mk)
                edtTendangnhap.setText("")
                edt_hoten.setText("")
                edt_mk.setText("")
                edt_re_mk.setText("")
            }
        }
        btn_huy.setOnClickListener {
            edtTendangnhap.setText("")
            edt_hoten.setText("")
            edt_mk.setText("")
            edt_re_mk.setText("")
        }
        return v
    }
}