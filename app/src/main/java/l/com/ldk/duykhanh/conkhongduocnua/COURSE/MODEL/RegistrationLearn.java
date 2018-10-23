package l.com.ldk.duykhanh.conkhongduocnua.COURSE.MODEL;

public class RegistrationLearn {

    private String maSinhVien;
    private String tenSinhVien;
    private String monHoc;
    private String soDienThoai;

    public RegistrationLearn() {
    }

    public RegistrationLearn(String maSinhVien, String tenSinhVien, String monHoc, String soDienThoai) {
        this.maSinhVien = maSinhVien;
        this.tenSinhVien = tenSinhVien;
        this.monHoc = monHoc;
        this.soDienThoai = soDienThoai;
    }



    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}

