package com.example.duanmau_quanhqph33420.Fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.duanmau_quanhqph33420.R
import com.example.duanmau_quanhqph33420.SQLDAO.doanhthuDAO
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class FragmentDoanhThu : Fragment() {
    lateinit var dao: doanhthuDAO
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_doanh_thu, container, false)
        dao = doanhthuDAO(requireActivity())

        val btnTungay = view.findViewById<Button>(R.id.btn_tungay_doanhthu)
        val btnDenngay = view.findViewById<Button>(R.id.btn_denngay_doanhthu)
        val btnKQ = view.findViewById<Button>(R.id.btn_ketqua_doanhthu)
        val txtTungay = view.findViewById<TextView>(R.id.txt_tungay_doanhthu)
        val txtDenngay = view.findViewById<TextView>(R.id.txt_denngay_doanhthu)
        val txtKetqua = view.findViewById<TextView>(R.id.txt_ketqua_doanhthu)

        showDate(txtTungay, btnTungay)
        showDate(txtDenngay, btnDenngay)

        btnKQ.setOnClickListener {
            if (txtTungay.text.toString() != ""
                && txtDenngay.text.toString() != ""
            ) {
                txtKetqua.setText(
                    "${
                        dao.doanhthuPM(
                            txtTungay.text.toString(),
                            txtDenngay.text.toString()
                        )
                    }VND"
                )
            }
        }

        return view
    }

    fun showDate(textView: TextView, button: Button) {
        val calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog =
            DatePickerDialog(
                requireActivity(),
                { view, Year, Month, Day ->
                    var y = Year
                    var m = String.format("%02d", Month + 1)
                    var d = String.format("%02d", Day)
                    var selectDate = "$y-$m-$d"
                    textView.text = selectDate
                },
                year, month, day
            )
        button.setOnClickListener {
            datePickerDialog.show()
        }
    }
}
