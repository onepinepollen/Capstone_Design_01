package com.example.more_close;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MemberData {
    private SQLiteDatabase db;
    private DBHelperMember dbHelper;

    public MemberData(Context context) {
        dbHelper = new DBHelperMember(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //팀 리스트를 데이터베이스에 추가하는 함수
    // 이름,설명,인원을 받아 저장한다.
    public long addMembers(String name, String member) {
        ContentValues values = new ContentValues();
        values.put(DBHelperMember.KEY_NAME, name);
        values.put(DBHelperMember.KEY_MEMBER, member);
        return db.insert(DBHelperMember.TABLE_NAME, null, values);
    }

    //해당 데이터의 팀 이름을 받아을데이터베이스에서 삭제하는 함수
    public void delAllMembers(String name){
        String query = DBHelperMember.KEY_NAME+" = ?";
        String[] select_data = { String.valueOf(name) };
        db.delete(DBHelperMember.TABLE_NAME,query,select_data);
    }

    public void delMembers(String id){
        String query = DBHelperMember.KEY_ID+" = ?";
        String[] select_data = { String.valueOf(id) };
        db.delete(DBHelperMember.TABLE_NAME,query,select_data);
    }

    public void updateName(String name,String new_name){
        ContentValues values = new ContentValues();
        values.put(DBHelperMember.KEY_NAME, new_name);

        String whereClause = DBHelperMember.KEY_NAME + " = ?";
        String[] whereArgs = { name };

        int rowsAffected = db.update(DBHelperMember.TABLE_NAME, values, whereClause, whereArgs);

        //예외처리
        if (rowsAffected > 0) {
            // 업데이트 성공
        } else {
            // 업데이트 실패
        }
    }

    //해당 팀의 저장된 메뉴들을 불러오는 함수
    public List<Member> getNameMembers(String name){
        List<Member> memberList = new ArrayList<>();
        String query = "SELECT * FROM " + DBHelperMember.TABLE_NAME + " WHERE " + DBHelperMember.KEY_NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{name});
        if (cursor.moveToFirst()) {
            do {
                Member member = new Member();
                member.setId(cursor.getString(0));
                member.setName(cursor.getString(1));
                member.setMember(cursor.getString(2));
                memberList.add(member);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return memberList;
    }

}
