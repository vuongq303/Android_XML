package com.example.duanmau_quanhqph33420.SQLDAO

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.widget.Toast
import com.example.duanmau_quanhqph33420.Main.mainActivity
import com.example.duanmau_quanhqph33420.Object.thuThu
import com.example.duanmau_quanhqph33420.SQL_Help.sql_helper
import com.example.duanmau_quanhqph33420.variable_pnlib

class thuThuDAO(var context: Context?) {
    val va = variable_pnlib()
    val sql = sql_helper(context)

    fun check_login(taikhoan: String, matkhau: String): Int {
        val db = sql.readableDatabase
        if (taikhoan == "" || matkhau == "") {
            Toast.makeText(context, "Tài khoản hoặc mật khẩu bị trống!", Toast.LENGTH_SHORT).show()
            return -1
        } else {
            val cursor = db.rawQuery(
                "select * from ${va.table_thuThu} " +
                        "where ${va.maThuThu_thuThu}='$taikhoan' and ${va.matKhau_thuThu}='$matkhau'",
                null
            )
            if (cursor.count > 0) {
                return 1
            }
        }
        return 0
    }

    fun them(matt: String, hoten: String, matkhau: String) {
        val db = sql.writableDatabase
        val cursor = db.rawQuery(
            "select * from ${va.table_thuThu} " +
                    "where ${va.maThuThu_thuThu}='$matt'",
            null
        )
        if (cursor.count > 0) {
            Toast.makeText(context, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show()
        } else {
            val contentValues = ContentValues()
            contentValues.put(va.maThuThu_thuThu, matt)
            contentValues.put(va.hoTen_thuThu, hoten)
            contentValues.put(va.matKhau_thuThu, matkhau)
            db.insert(va.table_thuThu, null, contentValues)
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show()
        }
    }

    fun doimk(taikhoan_old: String?, matkhau_new: String?) {
        val db = sql.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(va.matKhau_thuThu, matkhau_new)
        db.update(
            va.table_thuThu, contentValues,
            "${va.maThuThu_thuThu}=?",
            arrayOf(taikhoan_old)
        )
    }
}