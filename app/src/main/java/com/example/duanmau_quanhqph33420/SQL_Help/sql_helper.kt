package com.example.duanmau_quanhqph33420.SQL_Help

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.duanmau_quanhqph33420.variable_pnlib

class sql_helper(context: Context?) :
    SQLiteOpenHelper(context, "PNLib", null, 1) {
    val va = variable_pnlib()
    override fun onCreate(p0: SQLiteDatabase?) {

        val table_loaiSach = "create table ${va.table_loaiSach}(" +
                "${va.maLoai_loaiSach} integer primary key autoincrement," +
                "${va.tenLoai_loaiSach} text)"

        val insert_loaisach = "insert into ${va.table_loaiSach}" +
                "(${va.tenLoai_loaiSach}) values " +
                "('Java')," +
                "('Photoshop')," +
                "('Python')"

        val table_sach = "create table ${va.table_sach}(" +
                "${va.maSach_sach} integer primary key autoincrement," +
                "${va.maLoai_sach} integer references ${va.table_loaiSach}(${va.maLoai_loaiSach})," +
                "${va.tenSach_sach} text," +
                "${va.giaThue_sach} int)"

        val insert_sach = "insert into ${va.table_sach}" +
                "(${va.maLoai_sach},${va.tenSach_sach},${va.giaThue_sach}) values " +
                "(1,'Java cơ bản',19000)," +
                "(3,'Python nâng cao',9000)," +
                "(2,'Photoshop cơ bản',5000)"
//-----------------------------------------------------------------------------------
        val table_thuThu = "create table ${va.table_thuThu}(" +
                "${va.maThuThu_thuThu} text primary key," +
                "${va.hoTen_thuThu} text," +
                "${va.matKhau_thuThu} text)"

        val insert_thuthu = "insert into ${va.table_thuThu}" +
                "(${va.maThuThu_thuThu},${va.hoTen_thuThu},${va.matKhau_thuThu}) values " +
                "('nguyetanh98','Hoàng nguyệt anh','123')," +
                "('minhduc99','Nguyễn minh đức','999888')," +
                "('hoangvan90','Hoàng đức văn','33344')"

        val table_thanhVien = "create table ${va.table_thanhVien}(" +
                "${va.maTv_thanhVien} integer primary key autoincrement," +
                "${va.hoTen_thanhVien} text," +
                "${va.namSinh_thanhVien} text)"

        val insert_tv = "insert into ${va.table_thanhVien}" +
                "(${va.hoTen_thanhVien},${va.namSinh_thanhVien}) values " +
                "('Đỗ đức anh','1998')," +
                "('Hoàng trung hải','1996')," +
                "('Trương việt đức','1992')"
//-----------------------------------------------------------------------------------
        val table_phieuMuon = "create table ${va.table_phieuMuon}(" +
                "${va.ma_phieuMuon} integer primary key autoincrement," +
                "${va.maTv_phieuMuon} integer references ${va.table_thanhVien}(${va.maTv_thanhVien})," +
                "${va.maSach_phieuMuon} integer references ${va.table_sach}(${va.maSach_sach})," +
                "${va.ngay_phieuMuon} text," +
                "${va.tienThue_phieuMuon} int," +
                "${va.traSach_phieuMuon} int)"

        val insert_pm = "insert into ${va.table_phieuMuon}" +
                "(${va.maTv_phieuMuon},${va.maSach_phieuMuon},${va.ngay_phieuMuon}," +
                "${va.tienThue_phieuMuon},${va.traSach_phieuMuon}) " +
                "values " +
                "(1,2,'2023-06-12',9000,1)," +
                "(2,1,'2023-08-15',19000,0)," +
                "(3,3,'2023-09-12',5000,1)," +
                "(2,2,'2023-02-11',9000,0)"
//-------------------------------------------------------------------------
        p0?.execSQL(table_thuThu)
        p0?.execSQL(table_loaiSach)
        p0?.execSQL(table_thanhVien)
        p0?.execSQL(table_sach)
        p0?.execSQL(table_phieuMuon)

        p0?.execSQL(insert_thuthu)
        p0?.execSQL(insert_tv)
        p0?.execSQL(insert_loaisach)
        p0?.execSQL(insert_sach)
        p0?.execSQL(insert_pm)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p1 != p2) {
            p0?.execSQL("drop table ${va.table_phieuMuon}")
            p0?.execSQL("drop table ${va.table_thuThu}")
            p0?.execSQL("drop table ${va.table_sach}")
            p0?.execSQL("drop table ${va.table_loaiSach}")
            p0?.execSQL("drop table ${va.table_thanhVien}")
        }
        onCreate(p0)
    }
}
