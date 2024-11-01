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
import com.example.duanmau_quanhqph33420.variable_pnlib
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text

class FragmentDoiMk : Fragment() {
    lateinit var dao: thuThuDAO
    val va = variable_pnlib()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_doi_mk, container, false)
        dao = thuThuDAO(requireActivity())
        val sharedPreferences = requireActivity().getSharedPreferences(va.table_thongTin_TKMK, 0)

        val taikhoan_old = sharedPreferences.getString(va.taiKhoan, "")
        val matkhau_old = sharedPreferences.getString(va.matKhau, "")

        val edt_input_matkhauCu: TextInputEditText = view.findViewById(R.id.matkhau_cu_doimk)
        val edt_input_matkhauMoi: TextInputEditText = view.findViewById(R.id.matkhau_moi_doimk)
        val edt_input_reMatkhauMoi: TextInputEditText = view.findViewById(R.id.re_matkhau_moi_doimk)
        val btn_luu: Button = view.findViewById(R.id.btn_luu_doimk)
        val btn_huy: Button = view.findViewById(R.id.btn_huy_doimk)

        btn_huy.setOnClickListener {
            edt_input_matkhauCu.setText("")
            edt_input_matkhauMoi.setText("")
            edt_input_reMatkhauMoi.setText("")
        }
        btn_luu.setOnClickListener {
            val mkCu = edt_input_matkhauCu.text.toString().trim()
            val mkMoi = edt_input_matkhauMoi.text.toString().trim()
            val reMkMoi = edt_input_reMatkhauMoi.text.toString().trim()

            if (mkCu == "" || mkMoi == "" || reMkMoi == "") {
                Toast.makeText(
                    requireActivity(),
                    "Dữ liệu trống",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (mkMoi != reMkMoi) {
                Toast.makeText(
                    requireActivity(),
                    "Mật khẩu mới không khớp",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (mkCu != matkhau_old) {
                Toast.makeText(
                    requireActivity(),
                    "Mật khẩu cũ không đúng",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                dao.doimk(taikhoan_old, mkMoi)
                Toast.makeText(
                    requireActivity(),
                    "Đổi mật khẩu thành công",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return view
    }
}