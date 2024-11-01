package com.example.duanmau_quanhqph33420.SQLDAO

import android.content.Context
import com.example.duanmau_quanhqph33420.SQL_Help.sql_helper
import com.example.duanmau_quanhqph33420.variable_pnlib

class doanhthuDAO(context: Context?) {
    val sql = sql_helper(context)
    val va = variable_pnlib()

    fun doanhthuPM(start: String, end: String): Int {
        var tongtien = 0
        val db = sql.writableDatabase
        val cursor = db.rawQuery(
            "select ${va.tienThue_phieuMuon} " +
                    "from ${va.table_phieuMuon} " +
                    "where ${va.traSach_phieuMuon} = 1 " +
                    "and strftime('%Y-%m-%d',${va.ngay_phieuMuon}) " +
                    "between '$start' and '$end'", null
        )
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                tongtien += cursor.getInt(0)
            } while (cursor.moveToNext())
        }
        return tongtien
    }
}