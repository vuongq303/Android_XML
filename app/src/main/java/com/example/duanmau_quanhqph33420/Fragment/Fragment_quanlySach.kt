package com.example.duanmau_quanhqph33420.Fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.duanmau_quanhqph33420.Object.Sach
import com.example.duanmau_quanhqph33420.R
import com.example.duanmau_quanhqph33420.SQLDAO.loaiSachDAO
import com.example.duanmau_quanhqph33420.SQLDAO.sachDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Fragment_quanlySach : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var daosach: sachDAO
    lateinit var daoloaisach: loaiSachDAO
    lateinit var adp: adapter_quanlysach
    lateinit var list: ArrayList<Sach>
    lateinit var arrAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_quanly_sach, container, false)
        recyclerView = v.findViewById(R.id.rcv_quanlysach)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        daosach = sachDAO(requireActivity())
        daoloaisach = loaiSachDAO(requireActivity())

        reloadData()
        val fabThem = v.findViewById<FloatingActionButton>(R.id.fab_themSach)
        fabThem.setOnClickListener {
            them()
        }
        return v
    }

    fun reloadData() {
        list = daosach.getList()
        adp = adapter_quanlysach(
            requireActivity(), list, this
        )
        recyclerView.adapter = adp
    }

    fun them() {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        val view =
            LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_themsach, null, false)
        dialogBuilder.setView(view)
        val dialog = dialogBuilder.create()
        //anh xa dialog
        val edt_tensach: EditText = view.findViewById(R.id.dialogThem_edt_tensach)
        val edt_giathue: EditText = view.findViewById(R.id.dialogThem_edt_giathuesach)
        val btnThem: Button = view.findViewById(R.id.dialog_btn_themsach)
        val spinner_loaisach: Spinner = view.findViewById(R.id.dialogThem_spinner_loaisach)
        //get list loai sach
        val arrLoaisach = daoloaisach.getData()
        val arrSpinner = ArrayList<String>()
        //dua ten loai sach vao spinnner
        for (item in arrLoaisach.indices) {
            arrSpinner.add(arrLoaisach[item].tenloai)
        }
        //adapter spinner
        arrAdapter = ArrayAdapter(
            requireActivity(), android.R.layout.simple_spinner_dropdown_item, arrSpinner
        )
        spinner_loaisach.adapter = arrAdapter

        var maloai = -1
        spinner_loaisach.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                maloai = pos + 1
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                maloai = 1
            }
        }

        btnThem.setOnClickListener {
            val tensach = edt_tensach.text.toString().trim()
            val giathue = edt_giathue.text.toString().trim()

            if (tensach.equals("") || giathue.equals("")) {
                Toast.makeText(requireActivity(), "Thông tin sách trống", Toast.LENGTH_SHORT).show()
            } else {
                daosach.addSach(maloai, tensach, Integer.parseInt(giathue))
                Toast.makeText(requireActivity(), "Thêm sách thành công", Toast.LENGTH_SHORT).show()
                reloadData()
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    fun sua(masach: Int, tenloai: String, tensach: String, giathue: Int) {
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        val view =
            LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_suasach, null, false)
        dialogBuilder.setView(view)
        val dialog = dialogBuilder.create()
        //anh xa dialog
        val edt_masach: EditText = view.findViewById(R.id.dialogSua_edt_masach)
        val edt_tensach: EditText = view.findViewById(R.id.dialogSua_edt_tensach)
        val edt_giathue: EditText = view.findViewById(R.id.dialogSua_edt_giathuesach)
        val btnSua: Button = view.findViewById(R.id.dialog_btn_Suasach)
        val spinner_loaisach: Spinner = view.findViewById(R.id.dialogSua_spinner_loaisach)
        //set data to update
        edt_masach.setText("$masach")
        edt_tensach.setText(tensach)
        edt_giathue.setText("$giathue")
        //get list loai sach
        val arrLoaisach = daoloaisach.getData()
        val arrSpinner = ArrayList<String>()
        //dua ten loai sach vao spinnner
        for (item in arrLoaisach.indices) {
            arrSpinner.add(arrLoaisach[item].tenloai)
        }
        //adapter spinner
        arrAdapter = ArrayAdapter(
            requireActivity(), android.R.layout.simple_spinner_dropdown_item, arrSpinner
        )
        spinner_loaisach.adapter = arrAdapter
        val index = arrSpinner.indexOf(tenloai)
        spinner_loaisach.setSelection(index)

        var maloaiSpinner = -1
        spinner_loaisach.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                maloaiSpinner = pos + 1
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                maloaiSpinner = 1
            }
        }

        btnSua.setOnClickListener {
            val ten_sach = edt_tensach.text.toString().trim()
            val gia_thue = edt_giathue.text.toString().trim()

            if (ten_sach.equals("") || gia_thue.equals("")) {
                Toast.makeText(requireActivity(), "Thông tin sách trống", Toast.LENGTH_SHORT).show()
            } else {
                daosach.updateSach(masach, maloaiSpinner, ten_sach, Integer.parseInt(gia_thue))
                Toast.makeText(requireActivity(), "Sửa sách thành công", Toast.LENGTH_SHORT).show()
                reloadData()
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    fun xoa(masach: Int) {
        val dialog = AlertDialog.Builder(requireActivity())
        with(dialog) {
            setTitle("Bạn có chắc chắn muốn xóa sách")
            setIcon(R.drawable.baseline_error_24)
            setPositiveButton("Có") { dialog, i ->
                daosach.deleteSach(masach)
                Toast.makeText(requireActivity(), "Xóa sách thành công", Toast.LENGTH_SHORT).show()
                reloadData()
            }
            setNegativeButton("Không") { dialog, i -> }
            create()
            show()
        }
    }
}