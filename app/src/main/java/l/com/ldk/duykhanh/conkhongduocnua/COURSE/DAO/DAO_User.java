package l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DATABASE.DatabaseHelper;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.MODEL.User;

public class DAO_User {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public static final String TABLE_NAME = "User";
    public static final String SQL_USER = " CREATE TABLE User ( username text primary key, password text);";
    public static final String TAG = "DAOUser";

    public DAO_User(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int inserUser(User u) {
        ContentValues values = new ContentValues();
        values.put("username", u.getUsername());
        values.put("password", u.getPassword());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;
    }

    public List<User> getAllNguoiDung() {
        List<User> dsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            User ee = new User();
            ee.setUsername(c.getString(0));
            ee.setPassword(c.getString(1));

            dsNguoiDung.add(ee);
            Log.d("/====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsNguoiDung;

    }

    public int checkLogin(String username, String password) {
        int id = -1;
        Cursor cursor = db.rawQuery("SELECT username FROM User WHERE username=? AND password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            id = 1;
            cursor.close();
        }
        return id;
    }
}

