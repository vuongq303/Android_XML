package com.example.duanmau_quanhqph33420;

import java.util.Objects;

public class obj {
    String ten, trangthai;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public obj(String ten, String trangthai) {
        this.ten = ten;
        this.trangthai = trangthai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        obj obj = (obj) o;
        return ten.equals(obj.ten) && trangthai.equals(obj.trangthai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ten, trangthai);
    }
}
