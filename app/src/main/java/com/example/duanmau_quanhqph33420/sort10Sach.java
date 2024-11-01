package com.example.duanmau_quanhqph33420;

import android.content.Context;

import com.example.duanmau_quanhqph33420.Object.phieuMuon;
import com.example.duanmau_quanhqph33420.Object.top10;
import com.example.duanmau_quanhqph33420.SQLDAO.phieuMuonDAO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class sort10Sach {
    Context context;
    phieuMuonDAO dao;

    public sort10Sach(Context context) {
        this.context = context;
        dao = new phieuMuonDAO(context);
    }

    public ArrayList<top10> sapxep() {
        //khoi tao 3 arraylist
        ArrayList<phieuMuon> phieuMuonArrayList = dao.getList();
        ArrayList<obj> objArrayList = new ArrayList<>();
        ArrayList<top10> top10ArrayList = new ArrayList<>();
        ArrayList<top10> topArrayList = new ArrayList<>();

        //loc phan tu da tra sach
        for (phieuMuon item : phieuMuonArrayList) {
            if (item.getTrasach().equals("Đã trả sách")) {
                objArrayList.add(new obj(item.getTensach(), item.getTrasach()));
            }
        }
        // tạo mảng không trùng lặp
        HashSet<obj> objHashSet = new HashSet<>(objArrayList);
        // đếm xem từng phần tử trong đó xuất hiện bao nheieu lần
        for (obj o : objHashSet) {
            int count = Collections.frequency(objArrayList, o);
            topArrayList.add(new top10(o.getTen(), count));
        }
        // sapxep cac phan tu
        Collections.sort(topArrayList, (t1, t2) ->
                t2.getSoluong() - t1.getSoluong());

        //lay 10 phan tu dau tien
        if (topArrayList.size() < 10) {
            return topArrayList;
        } else {
            for (int i = 0; i < 10; i++) {
                top10ArrayList.add(topArrayList.get(i));
            }
            return top10ArrayList;
        }
    }
}
