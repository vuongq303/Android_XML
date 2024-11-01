package com.example.duanmau_quanhqph33420.SQLDAO

import android.content.ContentValues
import android.content.Context
import com.example.duanmau_quanhqph33420.Object.phieuMuon
import com.example.duanmau_quanhqph33420.SQL_Help.sql_helper
import com.example.duanmau_quanhqph33420.variable_pnlib

class phieuMuonDAO(context: Context?) {
    val sql = sql_helper(context)
    val va = variable_pnlib()

    fun getList(): ArrayList<phieuMuon> {
        val list = ArrayList<phieuMuon>()
        val db = sql.readableDatabase
        val join = "select ${va.table_phieuMuon}.${va.ma_phieuMuon}," +
                "${va.table_thanhVien}.${va.hoTen_thanhVien}," +
                "${va.table_sach}.${va.tenSach_sach}," +
                "${va.table_phieuMuon}.${va.ngay_phieuMuon}," +
                "${va.table_sach}.${va.giaThue_sach}," +
                "case " +
                "when ${va.table_phieuMuon}.${va.traSach_phieuMuon}=1 then 'Đã trả sách' " +
                "when ${va.table_phieuMuon}.${va.traSach_phieuMuon}=0 then 'Chưa trả sách' " +
                "else '' " +
                "end as result " +
                "from ${va.table_phieuMuon} " +
                "inner join ${va.table_sach} on " +
                "${va.table_sach}.${va.maSach_sach}=${va.table_phieuMuon}.${va.maSach_phieuMuon} " +
                "inner join ${va.table_thanhVien} on " +
                "${va.table_thanhVien}.${va.maTv_thanhVien}=${va.table_phieuMuon}.${va.maTv_phieuMuon}"
        val cursor = db.rawQuery(join, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                list.add(
                    phieuMuon(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getString(5)
                    )
                )
            } while (cursor.moveToNext())
        }
        return list
    }

    fun insertPm(matv: Int, masach: Int, ngay: String, tienthue: Int, trasach: Int) {
        val db = sql.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(va.maTv_phieuMuon, matv)
        contentValues.put(va.maSach_phieuMuon, masach)
        contentValues.put(va.ngay_phieuMuon, ngay)
        contentValues.put(va.tienThue_phieuMuon, tienthue)
        contentValues.put(va.traSach_phieuMuon, trasach)
        db.insert(va.table_phieuMuon, null, contentValues)
    }

    fun updatePm(mapm: Int, matv: Int, masach: Int, ngay: String, tienthue: Int, trasach: Int) {
        val db = sql.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(va.maTv_phieuMuon, matv)
        contentValues.put(va.maSach_phieuMuon, masach)
        contentValues.put(va.ngay_phieuMuon, ngay)
        contentValues.put(va.tienThue_phieuMuon, tienthue)
        contentValues.put(va.traSach_phieuMuon, trasach)
        db.update(
            va.table_phieuMuon,
            contentValues,
            "${va.ma_phieuMuon}=?",
            arrayOf(mapm.toString())
        )
    }

    fun deletePm(mapm: Int) {
        val db = sql.writableDatabase
        db.delete(va.table_phieuMuon, "${va.ma_phieuMuon}=?", arrayOf(mapm.toString()))
    }
}