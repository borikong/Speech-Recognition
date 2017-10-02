package com.google.cloud.android.speech.UserData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 김다영 on 2017-07-18.
 */

public class MySQLiteHandler {

    MySQLiteOpenHelper helper;
    SQLiteDatabase db;

    public MySQLiteHandler(Context ctx){

        helper=new MySQLiteOpenHelper(ctx,"han.sqlite",null,1);
    }

    public static MySQLiteHandler open(Context ctx){

        return new MySQLiteHandler(ctx);
    }

    public void close(){
        helper.close();
    }


    //삽입
    public void insert(String id,String name, String age, String address){
        db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("name",name);
        values.put("age",age);
        values.put("address",address);
        db.insert("user",null,values);
    }

    //수정
    public void update(String id,String name, String age, String address){
        db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("address",address);
        values.put("age",age);
        db.update("user",values,"id=?",new String[]{id});
    }

    //삭제
    public void delete(String id){
        db=helper.getWritableDatabase();
        db.delete("user","id=?",new String[]{id});
    }

    //_id 데이터 조회
    public Cursor selectById(String id){
        db=helper.getReadableDatabase();
        Cursor c=db.query("user",null,"id=?",new String[]{ id}, null,null,null);
        return c;
    }

    //전체 데이터 조회
    public Cursor selectAll(){
        db=helper.getReadableDatabase();
        Cursor c=db.query("user",null,null,null,null,null,null);
        return c;
    }

}

