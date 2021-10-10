package com.example.page1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context) {
        super(context, "final_app.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table login_details (uid text primary key , pwd text)");
        sqLiteDatabase.execSQL("Create table results (uid text, sub_code text, marks int)");
        sqLiteDatabase.execSQL("Create table R4MC (qno text primary key , qsn text , A text , B text , C text , D text , ans text , qsn_time text)");
        sqLiteDatabase.execSQL("Create table R4BC (qno text primary key , qsn text , A text , B text , C text , D text , ans text , qsn_time text)");
        sqLiteDatabase.execSQL("Create table R4DBMS (qno text primary key , qsn text , A text , B text , C text , D text , ans text , qsn_time text)");
        sqLiteDatabase.execSQL("Create table R4UDX (qno text primary key , qsn text , A text , B text , C text , D text , ans text , qsn_time text)");
        sqLiteDatabase.execSQL("Create table R4INS (qno text primary key , qsn text , A text , B text , C text , D text , ans text , qsn_time text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*sqLiteDatabase.execSQL("Drop table if exists R4MC");
        sqLiteDatabase.execSQL("Drop table if exists R4BC");
        sqLiteDatabase.execSQL("Drop table if exists R4DBMS");
        sqLiteDatabase.execSQL("Drop table if exists R4UDX");
        sqLiteDatabase.execSQL("Drop table if exists R4INS");
        onCreate(sqLiteDatabase);*/

    }

    public boolean registration(String uid,String pwd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid",uid);
        contentValues.put("pwd",pwd);
        long rs = db.insert("login_details",null,contentValues);
        if (rs == -1) return false;
        else return true;
    }

    public boolean chk_uid(String uid){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from login_details where uid=?",new String[]{uid});
        if(cursor.getCount()>0){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean chk_attempt(String uid,String code){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from results where uid=? and sub_code=?",new String[]{uid,code});
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }

    }

    public boolean login(String uid,String pwd){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from login_details where uid=? and pwd=?",new String[]{uid,pwd});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean add_qsn(String code,String qno,String qsn,String A,String B, String C, String D, String ans,String qsn_time){
        long qqs = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("qno",qno);
        contentValues.put("qsn",qsn);
        contentValues.put("A",A);
        contentValues.put("B",B);
        contentValues.put("C",C);
        contentValues.put("D",D);
        contentValues.put("ans",ans);
        contentValues.put("qsn_time",qsn_time);
        //qqs = db.insert(code,null,contentValues);
        if(code.equals("R4MC")){
            qqs = db.insert("R4MC",null,contentValues);
        }
        if(code.equals("R4BC")){
            qqs = db.insert("R4BC",null,contentValues);
        }
        if(code.equals("R4DBMS")){
            qqs = db.insert("R4DBMS",null,contentValues);
        }
        if(code.equals("R4UDX")){
            qqs = db.insert("R4UDX",null,contentValues);
        }
        if(code.equals("R4INS")){
            qqs = db.insert("R4INS",null,contentValues);
        }
        if(qqs == -1){
        return false;}
        else {return true;}
    }

    /*public Cursor getQsn(String code){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+code,null);
        return cursor;
    }*/

    public List<Questions> getQsn(String code){
        List<Questions> question_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        if (code.equals("R4MC")){
            Cursor cursor = db.rawQuery("select * from R4MC",null);
            if (cursor.moveToFirst()){
                do {
                    Questions questions1 = new Questions();
                    questions1.setQno(cursor.getString(0));
                    questions1.setQsn(cursor.getString(1));
                    questions1.setOptA(cursor.getString(2));
                    questions1.setOptB(cursor.getString(3));
                    questions1.setOptC(cursor.getString(4));
                    questions1.setOptD(cursor.getString(5));
                    questions1.setAns(cursor.getString(6));
                    questions1.setTimer(cursor.getString(7));
                    question_list.add(questions1);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return question_list;
        }
        else if (code.equals("R4BC")){
            Cursor cursor = db.rawQuery("select * from R4BC",null);
            if (cursor.moveToFirst()){
                do {
                    Questions questions1 = new Questions();
                    questions1.setQno(cursor.getString(0));
                    questions1.setQsn(cursor.getString(1));
                    questions1.setOptA(cursor.getString(2));
                    questions1.setOptB(cursor.getString(3));
                    questions1.setOptC(cursor.getString(4));
                    questions1.setOptD(cursor.getString(5));
                    questions1.setAns(cursor.getString(6));
                    questions1.setTimer(cursor.getString(7));
                    question_list.add(questions1);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return question_list;
        }
        else if (code.equals("R4DBMS")){
            Cursor cursor = db.rawQuery("select * from R4DBMS",null);
            if (cursor.moveToFirst()){
                do {
                    Questions questions1 = new Questions();
                    questions1.setQno(cursor.getString(0));
                    questions1.setQsn(cursor.getString(1));
                    questions1.setOptA(cursor.getString(2));
                    questions1.setOptB(cursor.getString(3));
                    questions1.setOptC(cursor.getString(4));
                    questions1.setOptD(cursor.getString(5));
                    questions1.setAns(cursor.getString(6));
                    questions1.setTimer(cursor.getString(7));
                    question_list.add(questions1);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return question_list;
        }
        else if (code.equals("R4UDX")){
            Cursor cursor = db.rawQuery("select * from R4UDX",null);
            if (cursor.moveToFirst()){
                do {
                    Questions questions1 = new Questions();
                    questions1.setQno(cursor.getString(0));
                    questions1.setQsn(cursor.getString(1));
                    questions1.setOptA(cursor.getString(2));
                    questions1.setOptB(cursor.getString(3));
                    questions1.setOptC(cursor.getString(4));
                    questions1.setOptD(cursor.getString(5));
                    questions1.setAns(cursor.getString(6));
                    questions1.setTimer(cursor.getString(7));
                    question_list.add(questions1);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return question_list;
        }
        else {
            Cursor cursor = db.rawQuery("select * from R4INS",null);
            if (cursor.moveToFirst()){
                do {
                    Questions questions1 = new Questions();
                    questions1.setQno(cursor.getString(0));
                    questions1.setQsn(cursor.getString(1));
                    questions1.setOptA(cursor.getString(2));
                    questions1.setOptB(cursor.getString(3));
                    questions1.setOptC(cursor.getString(4));
                    questions1.setOptD(cursor.getString(5));
                    questions1.setAns(cursor.getString(6));
                    questions1.setTimer(cursor.getString(7));
                    question_list.add(questions1);
                }while (cursor.moveToNext());
            }
            cursor.close();
            return question_list;
        }
    }

    public Boolean checkQsn(String code){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+code,null);
        if (cursor.getCount() > 0){ return true;}
        else {return false;}
    }

    public boolean addResult(String s_id,String code,int score){
        long r = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid",s_id);
        contentValues.put("sub_code",code);
        contentValues.put("marks",score);
        r = sqLiteDatabase.insert("results",null,contentValues);
        if (r == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getResult(String var,int i){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        if (i==0){
            String[] projections = {"uid","marks"};
            String selection = "sub_code LIKE ?";
            String[] selection_args = {var};
            Cursor cursor = sqLiteDatabase.query("results",projections,selection,selection_args,null,null,null);
            return cursor;
        }
        else {
            String[] projections = {"sub_code","marks"};
            String selection = "uid LIKE ?";
            String[] selection_args = {var};
            Cursor cursor = sqLiteDatabase.query("results",projections,selection,selection_args,null,null,null);
            return cursor;
        }
    }
}
