package l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DATABASE.DatabaseHelper;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.MODEL.Shedule;

public class DAO_Shedule {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "LichHoc";
    public static final String SQL_SHEDULE = "CREATE TABLE LichHoc (id int primary key , ngay date, phong text, giangduong text, mamon text, mon text, giangvien text);";
    public static final String TAG = "DAO_Shedule";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh:mm:ss");

    public DAO_Shedule (Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int inserShedule(Shedule s){
        ContentValues values = new ContentValues();
        values.put("id",s.getId());
        values.put("ngay",simpleDateFormat.format(s.getNgay()));
        values.put("phong",s.getPhong());
        values.put("giangduong",s.getGiangDuong());
        values.put("mamon",s.getMaMon());
        values.put("mon",s.getMon());
        values.put("giangvien",s.getGiangVien());
        try {
            if(db.insert(TABLE_NAME,null,values)== -1){
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }
        return 1;
    }

    public List<Shedule> getAllShedule() throws ParseException {
        List<Shedule> dsShedule = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Shedule ss = new Shedule();
            ss.setId(c.getInt(0));
            ss.setNgay(simpleDateFormat.parse(c.getString(1)));
            ss.setPhong(c.getString(2));
            ss.setGiangDuong(c.getString(3));
            ss.setMaMon(c.getString(4));
            ss.setMon(c.getString(5));
            ss.setGiangVien(c.getString(6));
            dsShedule.add(ss);
            c.moveToNext();
        }
        c.close();
        return dsShedule;
    }

    public int updateShedule(Shedule sl){
        ContentValues values = new ContentValues();
        values.put("id",sl.getId());
        values.put("ngay",sl.getNgay().toString());
        values.put("phong",sl.getPhong());
        values.put("giangduong",sl.getGiangDuong());
        values.put("mamon",sl.getMaMon());
        values.put("mon",sl.getMon());
        values.put("giangvien",sl.getGiangVien());
        int result = db.update(TABLE_NAME,values,"id=?",new String[]{String.valueOf(sl.getId())});
        if(result == 0){
            return -1;
        }
        return 1;
    }

    public int updateSheduleif(String id,String ngay, String phong, String giang, String maMon,String mon, String lop, String giangv, String thoigian,String chitiet){
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("ngay",ngay);
        values.put("phong",phong);
        values.put("giangduong",giang);
        values.put("mamon",maMon);
        values.put("mon",mon);
        values.put("giangvien",giangv);
        int result = db.update(TABLE_NAME,values,"id=?",new String[]{String.valueOf(id)});
        if(result == 0){
            return -1;
        }
        return 1;
    }
    public int deleteShedu(String id){
        int result = db.delete(TABLE_NAME,"id=?",new String[]{id});
        if(result == 0)
            return -1;
        return 1;
    }

}

