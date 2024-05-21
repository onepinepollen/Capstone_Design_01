package com.example.more_close;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MenuData {
    private SQLiteDatabase db;
    private DBHelperMenu dbHelper;

    public MenuData(Context context) {
        dbHelper = new DBHelperMenu(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    public long addMenus(String name, String menu, String pay) {
        ContentValues values = new ContentValues();
        values.put(DBHelperMenu.KEY_NAME, name);
        values.put(DBHelperMenu.KEY_MENU, menu);
        values.put(DBHelperMenu.KEY_PAY, pay);
        return db.insert(DBHelperMenu.TABLE_NAME, null, values);
    }

    //해당 데이터의 아이디를 받아 데이터베이스에서 삭제하는 함수
    public void delAllMenus(String name){
        String query = DBHelperMenu.KEY_NAME+" = ?";
        String[] select_data = { String.valueOf(name) };
        db.delete(DBHelperMenu.TABLE_NAME,query,select_data);
    }

    public void delMenus(String id){
        String query = DBHelperMenu.KEY_ID+" = ?";
        String[] select_data = { String.valueOf(id) };
        db.delete(DBHelperMenu.TABLE_NAME,query,select_data);

    }

    public void updateName(String name,String new_name){
        ContentValues values = new ContentValues();
        values.put(DBHelperMenu.KEY_NAME, new_name);

        String whereClause = DBHelperMenu.KEY_NAME + " = ?";
        String[] whereArgs = { name };

        int rowsAffected = db.update(DBHelperMenu.TABLE_NAME, values, whereClause, whereArgs);

        //예외처리
        if (rowsAffected > 0) {
            // 업데이트 성공
        } else {
            // 업데이트 실패
        }
    }

    //해당 팀의 저장된 메뉴들을 불러오는 함수
    public List<Menu> getNameMenus(String name){
        List<Menu> menuList = new ArrayList<>();
        String query = "SELECT * FROM " + DBHelperMenu.TABLE_NAME + " WHERE " + DBHelperMenu.KEY_NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{name});
        if (cursor.moveToFirst()) {
            do {
                Menu menu = new Menu();
                menu.setId(cursor.getString(0));
                menu.setName(cursor.getString(1));
                menu.setMenu(cursor.getString(2));
                menu.setPay(cursor.getString(3));
                menuList.add(menu);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return menuList;
    }

    public List<Menu> getMenu(String name, String menu1) {
        List<Menu> menuList = new ArrayList<>();
        String query = "SELECT * FROM " + DBHelperMenu.TABLE_NAME + " WHERE "
                + DBHelperMenu.KEY_NAME + " = ? AND " +
                DBHelperMenu.KEY_MENU + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{name, menu1});
        if (cursor.moveToFirst()) {
            do {
                Menu menu = new Menu();
                menu.setId(cursor.getString(0));
                menu.setName(cursor.getString(1));
                menu.setMenu(cursor.getString(2));
                menu.setPay(cursor.getString(3));
                menuList.add(menu);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return menuList;
    }

}
