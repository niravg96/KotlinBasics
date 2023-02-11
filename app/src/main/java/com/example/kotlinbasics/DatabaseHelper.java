package com.example.kotlinbasics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME = "GIRNARI.db";
    public static final String TABLE_NAME = "DATA";
    public static final String columnID ="user_id";
    public static final String columnname ="name";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +"(" + columnID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + columnname + " TEXT)";
    db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    onCreate(db);

    }
public boolean insertdata(String name)
{
    try
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(columnname,name);

        long result  = db.insert(TABLE_NAME,null,cv);
        if(result  == -1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }
    catch (Exception ex) {
        ex.printStackTrace();
        return false;
    }
}
public ArrayList<StudentModel> getAllStudentData()
{
    ArrayList<StudentModel>list = new ArrayList<>();
    String SQL = "SELECT * FROM " +TABLE_NAME;
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(SQL,null);
    if(cursor.moveToFirst())
    {
        do{
            StudentModel studentModel = new StudentModel();
            studentModel.setId(cursor.getString(0));
            studentModel.setName(cursor.getString(1));
            list.add(studentModel);
        }while (cursor.moveToNext());
    }
    return list;
}
public void deleterecord(String id)
{
    SQLiteDatabase db =this.getWritableDatabase();
    db.delete(TABLE_NAME,"user_id=?",new String[]{id});
    db.close();
}
public void updaterecord(String id,String name_data)
{
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv= new ContentValues();
    cv.put(columnname,name_data);
    db.update(TABLE_NAME,cv,"user_id=?",new String[]{id});
    db.close();

}
}