package kr.co.company.pocketmoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "POCKETMONEY.db"; // 데이터베이스 명
    public static final String TABLE_NAME = "money_table"; // 테이블 명

    // 테이블 항목
    public static final String COL_1 = "id";
    public static final String COL_2 = "io";
    public static final String COL_3 = "date";
    public static final String COL_4 = "money";
    public static final String COL_5 = "content";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, IO TEXT, DATE TEXT, MONEY TEXT, CONTENT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }

    // 데이터베이스 추가하기 insert

    public boolean insertData(String io, String date, String money, String content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,io);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,money);
        contentValues.put(COL_5,content);
        long result = db.insert(TABLE_NAME, null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    //데이터베이스 항목 읽어오기 Read
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return  res;
    }

    /*
    // 데이터베이스 삭제하기
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ? ",new String[]{id});
    }

    //데이터베이스 수정하기
    public boolean updateData(String id, String name, String phone, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,phone);
        contentValues.put(COL_4,address);
        db.update(TABLE_NAME,contentValues,"ID = ?", new String[] { id });
        return true;
    }
    */

}