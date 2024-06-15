package com.example.more_close;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TeamData {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public TeamData(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //팀 리스트를 데이터베이스에 추가하는 함수
    // 이름,설명,인원을 받아 저장한다.
    public long addTeams(String name, String explanation) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_NAME, name);
        values.put(DBHelper.KEY_EXPLANATION, explanation);
        return db.insert(DBHelper.TABLE_NAME, null, values);
    }

    //해당 데이터의 아이디를 받아 데이터베이스에서 삭제하는 함수
    public void delTeams(String id){
        String query = DBHelper.KEY_ID+" = ?";
        String[] select_data = { String.valueOf(id) };
        db.delete(DBHelper.TABLE_NAME,query,select_data);
    }

    public void updateName(String id,String name){
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_NAME, name);

        String whereClause = DBHelper.KEY_ID + " = ?";
        String[] whereArgs = { id };

        int rowsAffected = db.update(DBHelper.TABLE_NAME, values, whereClause, whereArgs);

        //예외처리
        if (rowsAffected > 0) {
            // 업데이트 성공
        } else {
            // 업데이트 실패
        }
    }

    //데이터베이스 내 저장된 팀 정보를 불러오는 함수
    public List<Team> getAllTeams() {
        List<Team> teamList = new ArrayList<>();
        String query = "SELECT * FROM " + DBHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Team team = new Team();
                team.setId(cursor.getString(0));
                team.setName(cursor.getString(1));
                team.setExplanation(cursor.getString(2));
                teamList.add(team);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return teamList;
    }

    public List<Team> getIdTeams(String id){
        List<Team> teamList = new ArrayList<>();
        String query = "SELECT * FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.KEY_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{id});
        if (cursor.moveToFirst()) {
            do {
                Team team = new Team();
                team.setId(cursor.getString(0));
                team.setName(cursor.getString(1));
                team.setExplanation(cursor.getString(2));
                teamList.add(team);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return teamList;
    }
}


