package com.kh.project12_2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GroupDao {
    MyHelper helper;

    public GroupDao(MyHelper helper) {
        this.helper = helper;
    }

    private void closeAll(Cursor cursor, SQLiteDatabase sqLiteDatabase) {
        if (cursor != null) cursor.close();
        if (sqLiteDatabase != null) sqLiteDatabase.close();
    }

    private void closeAll(Cursor cursor, SQLiteStatement sqLiteStatement, SQLiteDatabase sqLiteDatabase) {
        if (cursor != null) cursor.close();
        if (sqLiteStatement != null) sqLiteStatement.close();
        if (sqLiteDatabase != null) sqLiteDatabase.close();
    }

    // 입력
    public boolean insert(GroupVo vo) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        String sql = "insert into tbl_group" +
                "       values(?, ?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.bindString(1, vo.getG_name());
        statement.bindLong(2, vo.getG_number());
        statement.executeInsert();
        closeAll(null, statement, sqLiteDatabase);
        return true;

        /*
        // transaction 테스트
        SQLiteStatement statement = null;
        try {
            sqLiteDatabase.beginTransaction();
            String sql = "insert into tbl_group " +
                    "       values(?, ?)";
            statement = sqLiteDatabase.compileStatement(sql);
            for (int i = 1; i <= 2; i++) {
                statement.bindString(1, "zzz" + i);
                statement.bindLong(2, i);
                statement.executeInsert();
            }
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("mytag", e.getMessage());

        } finally {
            sqLiteDatabase.endTransaction();
            closeAll(null, statement, sqLiteDatabase);
        }
        */
    }

    // 조회
    public List<GroupVo> select() {
        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        String sql = "select * from tbl_group";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null); // JDBC의 ResultSet
        List<GroupVo> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String g_name = cursor.getString(0);
            int g_number = cursor.getInt(1);
            GroupVo vo = new GroupVo(g_name, g_number);
            list.add(vo);
        }
        closeAll(cursor, sqLiteDatabase);
        return list;
    }

    // 수정
    public boolean update(String name, int number) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String sql = "update tbl_group set g_number = ? where g_name = ?";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.bindLong(1, number);
        statement.bindString(2, name);
        int count = statement.executeUpdateDelete();
        Log.d("mytag", "count: " + count); // 수정한 데이터 개수
        closeAll(null, statement, sqLiteDatabase);
        return true;
    }


    // 삭제
    public boolean delete(String name) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String sql = "delete from tbl_group where g_name = ?";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.bindString(1, name);
        int count = statement.executeUpdateDelete();
        Log.d("mytag", "count : " + count);
        closeAll(null, statement, sqLiteDatabase);
        return true;
    }
}
