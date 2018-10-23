package l.com.ldk.duykhanh.conkhongduocnua.COURSE.DATABASE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO.DAO_Registration;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO.DAO_Shedule;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO.DAO_Test;
import l.com.ldk.duykhanh.conkhongduocnua.COURSE.DAO.DAO_User;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hotrohoctap";
    private static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DAO_User.SQL_USER);
        db.execSQL(DAO_Registration.SQL_REGISTRATION);
        db.execSQL(DAO_Shedule.SQL_SHEDULE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + DAO_User.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DAO_Registration.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DAO_Shedule.TABLE_NAME);

        onCreate(db);
    }
}

