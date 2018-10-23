package l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DATABASE.DatabaseHelper;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.MODEL.RegistrationLearn;

public class DAO_Registration {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "Registration";
    public static final String SQL_REGISTRATION = "CREATE TABLE Registration ( maSinhVien text, tenSinhVien text, monHoc text, soDienThoai text);";
    public static final String TAG = "DAO_Registration";

    public DAO_Registration(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertRegistrationLearn(RegistrationLearn rl){
        ContentValues values = new ContentValues();
        values.put("maSinhVien",rl.getMaSinhVien());
        values.put("tenSinhVien",rl.getTenSinhVien());
        values.put("monHoc",rl.getMonHoc());
        values.put("soDienThoai",rl.getSoDienThoai());
        try{
            if(db.insert(TABLE_NAME,null,values) == -1){
                return -1;
            }
        }
        catch (Exception e){
            Log.e(TAG, e.toString() );
        }
        return 1;
    }

    public List<RegistrationLearn> getALLMonHoc(){
        List<RegistrationLearn> dsRegistration = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            RegistrationLearn rl = new RegistrationLearn();
            rl.setMaSinhVien(c.getString(0));
            rl.setTenSinhVien(c.getString(1));
            rl.setMonHoc(c.getString(2));
            rl.setSoDienThoai(c.getString(3));
            dsRegistration.add(rl);
            Log.d(TAG,rl.toString());
            c.moveToNext();
        }
        c.close();
        return dsRegistration;
    }

    public int updateRegistration(RegistrationLearn rl){
        ContentValues values = new ContentValues();
        values.put("maSinhVien",rl.getMaSinhVien());
        values.put("tenSinhVien",rl.getTenSinhVien());
        values.put("monHoc",rl.getMonHoc());
        values.put("soDienThoai",rl.getSoDienThoai());
        int result = db.update(TABLE_NAME,values,"maSinhVien=?",new String[]{rl.getMaSinhVien()});
        if(result == 0){
            return -1;
        }
        return 1;
    }

    public int deleteSachByID(String maSinhVien){
        int result = db.delete(TABLE_NAME,"maSinhVien=?",new String[]{maSinhVien});
        if (result == 0)
            return -1;
        return 1;
    }
    public int updateRegistrationif(String ma,String t, String m, String s,String sdt) {
        ContentValues values = new ContentValues();
        values.put("maSinhVien",t);
        values.put("tenSinhVien",m);
        values.put("monHoc",s);
        values.put("soDienThoai",sdt);
        int result = db.update(TABLE_NAME,values,"maSinhVien=?",new String[]{ma});
        if(result == 0){
            return -1;
        }
        return 1;
    }


}

