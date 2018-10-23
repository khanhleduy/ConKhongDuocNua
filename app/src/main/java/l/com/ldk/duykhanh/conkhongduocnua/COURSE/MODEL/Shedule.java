package l.com.ldk.duykhanh.conkhongduocnua.COURSE.MODEL;

import java.sql.Time;
import java.util.Date;

public class Shedule {
    private int id;
    private Date ngay;
    private String phong;
    private String giangDuong;
    private String maMon;
    private String mon;
    private String giangVien;

    public Shedule() {
    }

    public Shedule(int id, Date ngay, String phong, String giangDuong, String maMon, String mon, String giangVien) {
        this.id = id;
        this.ngay = ngay;
        this.phong = phong;
        this.giangDuong = giangDuong;
        this.maMon = maMon;
        this.mon = mon;
        this.giangVien = giangVien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getGiangDuong() {
        return giangDuong;
    }

    public void setGiangDuong(String giangDuong) {
        this.giangDuong = giangDuong;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(String giangVien) {
        this.giangVien = giangVien;
    }

    @Override
    public String toString() {
        return "Shedule{" +
                "id=" + id +
                ", ngay=" + ngay +
                ", phong='" + phong + '\'' +
                ", giangDuong='" + giangDuong + '\'' +
                ", maMon='" + maMon + '\'' +
                ", mon='" + mon + '\'' +
                ", giangVien='" + giangVien + '\'' +
                '}';
    }
}

