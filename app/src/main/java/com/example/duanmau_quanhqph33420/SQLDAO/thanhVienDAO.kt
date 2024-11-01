package com.example.duanmau_quanhqph33420.SQLDAO

import android.content.ContentValues
import android.content.Context
import com.example.duanmau_quanhqph33420.Object.thanhVien
import com.example.duanmau_quanhqph33420.SQL_Help.sql_helper
import com.example.duanmau_quanhqph33420.variable_pnlib

class thanhVienDAO(context: Context?) {
    val sql = sql_helper(context)
    val va = variable_pnlib()

    fun getList(): ArrayList<thanhVien> {
        val list = ArrayList<thanhVien>()
        val db = sql.readableDatabase
        val cursor = db.rawQuery("select * from ${va.table_thanhVien}", null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                list.add(
                    thanhVien(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    fun them(hoten: String, namsinh: String) {
        val db = sql.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(va.hoTen_thanhVien, hoten)
        contentValues.put(va.namSinh_thanhVien, namsinh)
        db.insert(va.table_thanhVien, null, contentValues)
    }

    fun sua(matv: Int, hoten: String, namsinh: String) {
        val db = sql.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(va.hoTen_thanhVien, hoten)
        contentValues.put(va.namSinh_thanhVien, namsinh)
        db.update(
            va.table_thanhVien,
            contentValues,
            "${va.maTv_thanhVien}=?",
            arrayOf(matv.toString())
        )
    }

    fun xoa(matv: Int) {
        val db = sql.writableDatabase
        db.delete(
            va.table_thanhVien,
            "${va.maTv_thanhVien}=?",
            arrayOf(matv.toString())
        )
    }

}