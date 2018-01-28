package com.example.bazoka.myapplication;

/**
 * Created by bazoka on 22/01/2018.
 */

public class Tools {
    int id;
    String ten,mieuta,pn,sn,anh;

    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public String getMieuta() {
        return mieuta;
    }

    public String getPn() {
        return pn;
    }

    public String getSn() {
        return sn;
    }

    public String getAnh() {
        return anh;
    }

    public Tools(int id, String ten, String mieuta, String pn, String sn, String anh) {
        this.id = id;
        this.ten = ten;
        this.mieuta = mieuta;
        this.pn = pn;
        this.sn = sn;
        this.anh = anh;
    }

    public Tools(String ten, String mieuta, String pn, String sn, String anh) {
        this.ten = ten;
        this.mieuta = mieuta;
        this.pn = pn;
        this.sn = sn;
        this.anh = anh;
    }


}
