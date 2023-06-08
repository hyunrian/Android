package com.kh.project12_2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        // name : 생성할 데이터베이스 이름
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("mytag", "onCreate");
        String sql = "create table tbl_group(" +
                "       g_name char(20) primary key," +
                "       g_number number)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // onUpgrade 사용 목적 : 버전이 달라지면 실행됨
        Log.d("mytag", "onUpgrade");
        String sql = "drop table if exists tbl_group";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
