package com.example.duanmau_quanhqph33420.SQLDAO

import android.content.ContentValues
import android.content.Context
import com.example.duanmau_quanhqph33420.Object.Sach
import com.example.duanmau_quanhqph33420.SQL_Help.sql_helper
import com.example.duanmau_quanhqph33420.variable_pnlib

class sachDAO(context: Context?) {
    val va = variable_pnlib()
    val sql = sql_helper(context)

    fun getList(): ArrayList<Sach> {
        val db = sql.readableDatabase
        val list = ArrayList<Sach>()
        val join = "select ${va.table_sach}.${va.maSach_sach}, " +
                "${va.table_loaiSach}.${va.tenLoai_loaiSach}, " +
                "${va.table_sach}.${va.tenSach_sach}, " +
                "${va.table_sach}.${va.giaThue_sach} " +
                "from ${va.table_sach} " +
                "inner join ${va.table_loaiSach} " +
                "on ${va.table_sach}.${va.maLoai_sach}=${va.table_loaiSach}.${va.maLoai_loaiSach}"

        val cursor = db.rawQuery(join, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                list.add(
                    Sach(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    fun addSach(maloai: Int, tensach: String, giathue: Int) {
        val db = sql.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(va.maLoai_sach, maloai)
        contentValues.put(va.tenSach_sach, tensach)
        contentValues.put(va.giaThue_sach, giathue)
        db.insert(va.table_sach, null, contentValues)
    }

    fun updateSach(masach: Int, maloai: Int, tensach: String, giathue: Int) {
        val db = sql.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(va.maLoai_sach, maloai)
        contentValues.put(va.tenSach_sach, tensach)
        contentValues.put(va.giaThue_sach, giathue)
        db.update(
            va.table_sach, contentValues,
            "${va.maSach_sach}=?",
            arrayOf(masach.toString())
        )
    }

    fun deleteSach(masach: Int) {
        val db = sql.writableDatabase
        db.delete(
            va.table_sach, "${va.maSach_sach}=?",
            arrayOf(masach.toString())
        )
    }

}