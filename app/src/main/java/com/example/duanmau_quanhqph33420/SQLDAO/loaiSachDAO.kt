package com.example.duanmau_quanhqph33420.SQLDAO

import android.content.ContentValues
import android.content.Context
import com.example.duanmau_quanhqph33420.Object.LoaiSach
import com.example.duanmau_quanhqph33420.SQL_Help.sql_helper
import com.example.duanmau_quanhqph33420.variable_pnlib
import kotlin.collections.ArrayList

class loaiSachDAO(var context: Context?) {
    val sql = sql_helper(context)
    val va = variable_pnlib()

    fun getData(): ArrayList<LoaiSach> {
        val db = sql.readableDatabase
        val list = ArrayList<LoaiSach>()
        val cursor = db.rawQuery("select * from ${va.table_loaiSach}", null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                list.add(
                    LoaiSach(
                        cursor.getInt(0),
                        cursor.getString(1)
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    fun addLoaisach(tenloai: String) {
        val db = sql.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(va.tenLoai_loaiSach, tenloai)
        db.insert(
            va.table_loaiSach,
            null, contentValues
        )
    }

    fun updateLoaisach(maloai: Int, tenloai: String) {
        val db = sql.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(va.tenLoai_loaiSach, tenloai)
        db.update(
            va.table_loaiSach,
            contentValues,
            "${va.maLoai_loaiSach}=?",
            arrayOf(maloai.toString())
        )
    }

    fun deleteLoaisach(maloai: Int) {
        val db = sql.writableDatabase
        db.delete(
            va.table_loaiSach,
            "${va.maLoai_loaiSach}=?",
            arrayOf(maloai.toString())
        )
    }
}