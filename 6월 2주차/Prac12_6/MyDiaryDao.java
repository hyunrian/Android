package com.kh.prac12_6;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


public class MyDiaryDao {
    MyHelper helper;

    public MyDiaryDao(MyHelper helper) {
        this.helper = helper;
    }

    private void closeAll(SQLiteStatement statement, SQLiteDatabase sqLiteDatabase, Cursor cursor) {
        if (statement != null) statement.close();
        if (sqLiteDatabase != null) sqLiteDatabase.close();
        if (cursor != null) cursor.close();
    }

    // 입력
    public boolean insert(MyDiaryVo vo) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String sql = "insert into tbl_diary" +
                "       values(?, ?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.bindString(1, vo.getDate());
        statement.bindString(2, vo.getText());
        statement.executeInsert();
        closeAll(statement, sqLiteDatabase, null);

        return true;
    }

    // 수정
    public boolean update(MyDiaryVo vo) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String sql = "update tbl_diary set text = ? where date = ?";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.bindString(1, vo.getText());
        statement.bindString(2, vo.getDate());
        statement.executeUpdateDelete();
        closeAll(statement, sqLiteDatabase, null);
        return true;
    }

    // 삭제
    public boolean delete(String date) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String sql = "delete from tbl_diary where date = ?";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.bindString(1, date);
        statement.executeUpdateDelete();
        closeAll(statement, sqLiteDatabase, null);
        return true;
    }

    // 조회
    public String select(String date) {
        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        String sql = "select * from tbl_diary where date = '" + date +"'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        String text = "";
        while (cursor.moveToNext()) { // while문이 없으면 오류가 발생하는데 가져올 값이 한개여도 꼭 써야하는지?
            text = cursor.getString(1);
        }
        closeAll(null, sqLiteDatabase, cursor);
        return text;
    }

}
