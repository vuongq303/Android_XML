package com.example.duanmau_quanhqph33420.Fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.duanmau_quanhqph33420.Adapter.adapter_phieuMuon
import com.example.duanmau_quanhqph33420.Object.Sach
import com.example.duanmau_quanhqph33420.Object.thanhVien
import com.example.duanmau_quanhqph33420.R
import com.example.duanmau_quanhqph33420.SQLDAO.phieuMuonDAO
import com.example.duanmau_quanhqph33420.SQLDAO.sachDAO
import com.example.duanmau_quanhqph33420.SQLDAO.thanhVienDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Calendar

class FragmentPhieuMuon : Fragment() {
    lateinit var rcv_phieumuon: RecyclerView
    lateinit var adp: adapter_phieuMuon
    lateinit var dao: phieuMuonDAO
    lateinit var daoSach: sachDAO
    lateinit var daotv: thanhVienDAO
    lateinit var listSach: ArrayList<Sach>
    lateinit var listTV: ArrayList<thanhVien>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_phieu_muon, container, false)
        val fab_them = view.findViewById<FloatingActionButton>(R.id.fab_themPhieuMuon)
        dao = phieuMuonDAO(requireActivity())
        daotv = thanhVienDAO(requireActivity())
        daoSach = sachDAO(requireActivity())
        listTV = daotv.getList()
        listSach = daoSach.getList()

        rcv_phieumuon = view.findViewById(R.id.rcv_qlPhieumuon)
        rcv_phieumuon.layoutManager = LinearLayoutManager(requireActivity())
        reload()
        fab_them.setOnClickListener {
            them()
        }
        return view
    }

    fun getTrasach(checkBox: CheckBox): Int {
        if (checkBox.isChecked) {
            return 1
        }
        return 0
    }

    fun setTrasach(s: String): Boolean {
        if (s == "Đã trả sách") {
            return true
        }
        return false
    }

    fun reload() {
        val listPM = dao.getList()
        adp = adapter_phieuMuon(requireActivity(), listPM, this)
        rcv_phieumuon.adapter = adp
    }

    fun them() {
        val builder = AlertDialog.Builder(requireActivity())
        val view = LayoutInflater.from(requireActivity())
            .inflate(R.layout.dialog_them_phieumuon, null, false)
        builder.setView(view)
        val dialog = builder.create()

        val spn_tentv: Spinner = view.findViewById(R.id.dialogThem_spinner_tentv_phieumuon)
        val spn_tensach: Spinner = view.findViewById(R.id.dialogThem_tensach_phieumuon)
        val txt_ngaythue: TextView = view.findViewById(R.id.dialogThem_txt_ngay_phieumuon)
        val txt_tienthue: TextView = view.findViewById(R.id.dialogThem_txt_tienthue_phieumuon)
        val chck_trasach: CheckBox = view.findViewById(R.id.dialogThem_chk_trangthai_phieumuon)
        val btn_Them: Button = view.findViewById(R.id.dialogThem_btn_thempm)

        val arrSpinnerTV = ArrayList<String>()
        val arrSpinnrSach = ArrayList<String>()

        for (item in listTV.indices) {
            arrSpinnerTV.add(listTV[item].hotentv)
        }

        for (item in listSach.indices) {
            arrSpinnrSach.add(listSach[item].tensach)
        }

        val adapterSpTV = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            arrSpinnerTV
        )

        val adapterSpSach = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            arrSpinnrSach
        )

        spn_tentv.adapter = adapterSpTV
        spn_tensach.adapter = adapterSpSach
        var maTentv = -1
        var maSach = -1
        var ngaythue = ""
        var tienthue = -1
        var trasach = -1
        val this_time = Calendar.getInstance()

        ngaythue = "${this_time.get(Calendar.YEAR)}-" +
                "${this_time.get(Calendar.MONTH) + 1}-" +
                "${this_time.get(Calendar.DAY_OF_MONTH)}"

        trasach = getTrasach(chck_trasach)
        chck_trasach.setOnClickListener {
            trasach = getTrasach(chck_trasach)
        }

        txt_ngaythue.text = "Ngày thuê: $ngaythue"
        spn_tentv.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                maTentv = p2 + 1
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                maTentv = 1
            }
        }

        spn_tensach.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                maSach = p2 + 1
                tienthue = listSach[p2].giathue
                txt_tienthue.text = "Tiền thuê: $tienthue"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                maSach = 1
                tienthue = listSach[0].giathue
                txt_tienthue.text = "Tiền thuê: $tienthue"
            }
        }

        btn_Them.setOnClickListener {
            dao.insertPm(maTentv, maSach, ngaythue, tienthue, trasach)
            Toast.makeText(requireActivity(), "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT)
                .show()
            reload()
            dialog.dismiss()
        }
        dialog.show()
    }

    fun xoa(p: Int) {
        val dialog = AlertDialog.Builder(requireActivity())
        with(dialog) {
            setTitle("Bạn có chắc chắn muốn xóa phiếu mượn")
            setIcon(R.drawable.baseline_error_24)
            setPositiveButton("Có") { dialog, i ->
                dao.deletePm(p)
                Toast.makeText(requireActivity(), "Xóa phiếu mượn thành công", Toast.LENGTH_SHORT)
                    .show()
                reload()
            }
            setNegativeButton("Không") { dialog, i -> }
            create()
            show()
        }
    }

    fun sua(
        mapm: Int,
        tentv: String,
        tensach: String,
        ngay: String,
        tien: Int,
        chktrasach: String
    ) {
        val builder = AlertDialog.Builder(requireActivity())
        val view = LayoutInflater.from(requireActivity())
            .inflate(R.layout.dialog_sua_phieumuon, null, false)
        builder.setView(view)
        val dialog = builder.create()

        val edt_mapm: EditText = view.findViewById(R.id.dialogSua_edt_mapm_Phieumuon)
        val spn_tentv: Spinner = view.findViewById(R.id.dialogSua_spinner_tentv_phieumuon)
        val spn_tensach: Spinner = view.findViewById(R.id.dialogSua_tensach_phieumuon)
        val txt_ngaythue: TextView = view.findViewById(R.id.dialogSua_txt_ngay_phieumuon)
        val txt_tienthue: TextView = view.findViewById(R.id.dialogSua_txt_tienthue_phieumuon)
        val chck_trasach: CheckBox = view.findViewById(R.id.dialogSua_chk_trangthai_phieumuon)
        val btn_Sua: Button = view.findViewById(R.id.dialogSua_btn_phieumuon)

        val arrSpinnerTV = ArrayList<String>()
        val arrSpinnrSach = ArrayList<String>()

        for (item in listTV.indices) {
            arrSpinnerTV.add(listTV[item].hotentv)
        }

        for (item in listSach.indices) {
            arrSpinnrSach.add(listSach[item].tensach)
        }

        val adapterSpTV = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            arrSpinnerTV
        )

        val adapterSpSach = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            arrSpinnrSach
        )

        spn_tentv.adapter = adapterSpTV
        spn_tensach.adapter = adapterSpSach

        var maTentv = -1
        var maSach = -1
        var tienthue = -1
        var trasach = -1

        edt_mapm.setText("$mapm")
        spn_tentv.setSelection(arrSpinnerTV.indexOf(tentv))
        spn_tensach.setSelection(arrSpinnrSach.indexOf(tensach))
        txt_ngaythue.text = "Ngày thuê: $ngay"
        chck_trasach.isChecked = setTrasach(chktrasach)
        trasach = getTrasach(chck_trasach)

        chck_trasach.setOnClickListener {
            trasach = getTrasach(chck_trasach)
        }
        spn_tentv.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                maTentv = p2 + 1
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        spn_tensach.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                maSach = p2 + 1
                tienthue = listSach[p2].giathue
                txt_tienthue.text = "Tiền thuê: $tienthue"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        btn_Sua.setOnClickListener {
            dao.updatePm(mapm, maTentv, maSach, ngay, tienthue, trasach)
            Toast.makeText(
                requireActivity(),
                "Sửa phiếu mượn thành công",
                Toast.LENGTH_SHORT
            )
                .show()
            reload()
            dialog.dismiss()
        }
        dialog.show()
    }
}