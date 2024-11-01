package com.example.duanmau_quanhqph33420.Main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.duanmau_quanhqph33420.Adapter.FragmentDoiMk
import com.example.duanmau_quanhqph33420.Adapter.FragmentThemThuThu
import com.example.duanmau_quanhqph33420.Fragment.FragmentDoanhThu
import com.example.duanmau_quanhqph33420.Fragment.FragmentPhieuMuon
import com.example.duanmau_quanhqph33420.Fragment.FragmentThanhVien
import com.example.duanmau_quanhqph33420.Fragment.FragmentTop10Sach
import com.example.duanmau_quanhqph33420.Fragment.Fragment_LoaiSach
import com.example.duanmau_quanhqph33420.Fragment.Fragment_quanlySach
import com.example.duanmau_quanhqph33420.Login_.dangNhap
import com.google.android.material.navigation.NavigationView
import com.example.duanmau_quanhqph33420.R


class mainActivity : AppCompatActivity() {
    lateinit var txt_Tieude: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_app)
        val navi = findViewById<NavigationView>(R.id.nav_main_app)
        val nav_icon = findViewById<ImageView>(R.id.imgBtn_Nav)

        val header = navi.getHeaderView(0)
        val text_header = header.findViewById<TextView>(R.id.txt_header)
        val intent = intent
        text_header.text = intent.getStringExtra("wel")

        Toast.makeText(
            this, intent.getStringExtra("wel"), Toast.LENGTH_SHORT
        ).show()

        if (text_header.text.equals("Welcome user")) {
            val menu = navi.menu
            val itemthemnv = menu.findItem(R.id.them_tv_menu)
            val itemqlnv = menu.findItem(R.id.ql_thanhVien_menu)
            itemqlnv.isVisible = false
            itemthemnv.isVisible = false
        } else {
            val menu1 = navi.menu
            val itemDoimk = menu1.findItem(R.id.doi_mk_menu)
            itemDoimk.isVisible = false
        }

        txt_Tieude = findViewById(R.id.tieude_app)

        nav_icon.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }

        val fragment_pm = supportFragmentManager.beginTransaction()
        val pm = FragmentPhieuMuon()
        drawer.closeDrawer(GravityCompat.START)
        with(fragment_pm) {
            replace(R.id.container_Fragment, pm)
            commit()
        }
        txt_Tieude.text = "Quản lí phiếu mượn"

        navi.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ql_phieuMuon_menu -> {
                    val fragment_pm = supportFragmentManager.beginTransaction()
                    val pm = FragmentPhieuMuon()
                    drawer.closeDrawer(GravityCompat.START)
                    with(fragment_pm) {
                        replace(R.id.container_Fragment, pm)
                        commit()
                    }
                    txt_Tieude.text = "Quản lí phiếu mượn"
                }

                R.id.ql_loaiSach_menu -> {
                    val fragmentloaisach = supportFragmentManager.beginTransaction()
                    val loaiSach = Fragment_LoaiSach()
                    drawer.closeDrawer(GravityCompat.START)
                    with(fragmentloaisach) {
                        replace(R.id.container_Fragment, loaiSach)
                        commit()
                    }
                    txt_Tieude.text = "Quản lí loại sách"
                }

                R.id.ql_sach_menu -> {
                    val fragmentsach = supportFragmentManager.beginTransaction()
                    val sach = Fragment_quanlySach()
                    drawer.closeDrawer(GravityCompat.START)
                    with(fragmentsach) {
                        replace(R.id.container_Fragment, sach)
                        commit()
                    }
                    txt_Tieude.text = "Quản lí sách"
                }

                R.id.ql_thanhVien_menu -> {
                    val fragment_qlTV = supportFragmentManager.beginTransaction()
                    val tv = FragmentThanhVien()
                    drawer.closeDrawer(GravityCompat.START)
                    with(fragment_qlTV) {
                        replace(R.id.container_Fragment, tv)
                        commit()
                    }
                    txt_Tieude.text = "Quản lí thành viên"
                }

                R.id.top10_sach_menu -> {
                    val fragment_top10 = supportFragmentManager.beginTransaction()
                    val top = FragmentTop10Sach()
                    drawer.closeDrawer(GravityCompat.START)
                    with(fragment_top10) {
                        replace(R.id.container_Fragment, top)
                        commit()
                    }
                    txt_Tieude.text = "Top 10 sách mượn nhiều nhất"
                }

                R.id.doanh_thu_menu -> {
                    val fragment_doanhthu = supportFragmentManager.beginTransaction()
                    val doanhthu = FragmentDoanhThu()
                    drawer.closeDrawer(GravityCompat.START)
                    with(fragment_doanhthu) {
                        replace(R.id.container_Fragment, doanhthu)
                        commit()
                    }
                    txt_Tieude.text = "Doanh thu"
                }

                R.id.them_tv_menu -> {
                    val fragment_thuThu = supportFragmentManager.beginTransaction()
                    val thuthu = FragmentThemThuThu()
                    drawer.closeDrawer(GravityCompat.START)
                    with(fragment_thuThu) {
                        replace(R.id.container_Fragment, thuthu)
                        commit()
                    }
                    txt_Tieude.text = "Thêm thành viên"
                }

                R.id.doi_mk_menu -> {
                    val fragment_doimk = supportFragmentManager.beginTransaction()
                    val doimk = FragmentDoiMk()
                    drawer.closeDrawer(GravityCompat.START)
                    with(fragment_doimk) {
                        replace(R.id.container_Fragment, doimk)
                        commit()
                    }
                    txt_Tieude.text = "Đổi mật khẩu"
                }

                R.id.dang_xuat_menu -> {
                    val dialog = AlertDialog.Builder(this)
                    with(dialog) {
                        setTitle("Bạn có chắc chắn muốn đăng xuất")
                        setIcon(R.drawable.baseline_error_24)
                        setPositiveButton("Có") { dialogInterface, i ->
                            startActivity(Intent(this@mainActivity, dangNhap::class.java))
                        }
                        setNegativeButton("Không") { dialogInterface, i1 -> }
                        create()
                        show()
                    }
                }
            }
            true
        }
    }
}