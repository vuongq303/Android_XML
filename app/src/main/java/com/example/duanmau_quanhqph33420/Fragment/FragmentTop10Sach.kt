package com.example.duanmau_quanhqph33420.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.duanmau_quanhqph33420.Adapter.adapterTop10Sach
import com.example.duanmau_quanhqph33420.Object.top10
import com.example.duanmau_quanhqph33420.R
import com.example.duanmau_quanhqph33420.sort10Sach

class FragmentTop10Sach : Fragment() {
    lateinit var rcv_top10: RecyclerView
    lateinit var adp: adapterTop10Sach
    lateinit var sortSach: sort10Sach
    lateinit var list: ArrayList<top10>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_top10_sach, container, false)
        rcv_top10 = view.findViewById(R.id.rcv_top10Sach)
        rcv_top10.layoutManager = LinearLayoutManager(requireActivity())
        sortSach = sort10Sach(requireActivity())
        list = sortSach.sapxep()
        adp = adapterTop10Sach(requireActivity(), list, this)
        rcv_top10.adapter = adp
        return view
    }

}