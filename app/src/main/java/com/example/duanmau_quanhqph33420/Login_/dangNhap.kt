package com.example.duanmau_quanhqph33420.Login_

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.duanmau_quanhqph33420.Main.mainActivity
import com.example.duanmau_quanhqph33420.R
import com.example.duanmau_quanhqph33420.SQLDAO.thuThuDAO
import com.example.duanmau_quanhqph33420.variable_pnlib
import com.google.android.material.textfield.TextInputEditText

class dangNhap() : AppCompatActivity() {
    lateinit var chckRemem: CheckBox
    lateinit var sharedPreferences: SharedPreferences
    lateinit var inputEdt_taiKhoan: TextInputEditText
    lateinit var inputEdt_matKhau: TextInputEditText
    val va = variable_pnlib()
    var stt_chck = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_nhap)

        inputEdt_taiKhoan = findViewById(R.id.input_edt_taikhoan)
        inputEdt_matKhau = findViewById(R.id.input_edt_matkhau)
        val btn_login: Button = findViewById(R.id.btn_login)
        val btn_quit: Button = findViewById(R.id.btn_quit)
        chckRemem = findViewById(R.id.chck_remember)
        val thongtin_log = thuThuDAO(this)

        sharedPreferences = getSharedPreferences(va.table_thongTin_TKMK, Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean(va.chkCheck, false)) {
            with(sharedPreferences) {
                inputEdt_taiKhoan.setText(sharedPreferences.getString(va.taiKhoan, ""))
                inputEdt_matKhau.setText(sharedPreferences.getString(va.matKhau, ""))
                chckRemem.isChecked = sharedPreferences.getBoolean(va.chkCheck, false)
            }
        } else {
            inputEdt_taiKhoan.setText("")
            inputEdt_matKhau.setText("")
            chckRemem.isChecked = false
        }

        btn_login.setOnClickListener {
            val tk = inputEdt_taiKhoan.text.toString().trim()
            val mk = inputEdt_matKhau.text.toString().trim()
            stt_chck = chckRemem.isChecked

            if (tk == "admin" && mk == "1") {
                val intent = Intent(this@dangNhap, mainActivity::class.java)
                intent.putExtra("wel", "Welcome admin")
                startActivity(intent)
            } else {
                val log = thongtin_log.check_login(tk, mk)
                if (log == 1) {
                    val intent1 = Intent(this@dangNhap, mainActivity::class.java)
                    intent1.putExtra("wel", "Welcome user")
                    startActivity(intent1)
                } else if (log == 0) {
                    Toast.makeText(
                        this,
                        "Thông tin tài khoản mật khẩu không đúng",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            with(sharedPreferences.edit()) {
                putString(va.taiKhoan, tk)
                putString(va.matKhau, mk)
                putBoolean(va.chkCheck, stt_chck)
                apply()
            }
        }

        btn_quit.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            with(dialog) {
                setTitle("Bạn có chắc chắn muốn thoát")
                setIcon(R.drawable.baseline_error_24)
                setPositiveButton("Có") { dialogInterface, i ->
                    System.exit(0)
                }
                setNegativeButton("Không") { dialogInterface, i -> }
                create()
                show()
            }
        }
    }

}