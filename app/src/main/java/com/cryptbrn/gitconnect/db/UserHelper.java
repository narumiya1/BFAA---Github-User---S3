package com.cryptbrn.gitconnect.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.cryptbrn.gitconnect.model.User;
import java.util.ArrayList;
import static com.cryptbrn.gitconnect.db.DbUserContract.UserColumns.ID;
import static com.cryptbrn.gitconnect.db.DbUserContract.UserColumns.TABLE_NAME;
import static com.cryptbrn.gitconnect.db.DbUserContract.UserColumns.AVATAR;
import static com.cryptbrn.gitconnect.db.DbUserContract.UserColumns.TYPE;
import static com.cryptbrn.gitconnect.db.DbUserContract.UserColumns.URLREPOS;
import static com.cryptbrn.gitconnect.db.DbUserContract.UserColumns.USERNAME;

public class UserHelper {
    private static final String DB_TABLE = TABLE_NAME;
    private static DbUserHelper dbUserHelper;
    private static UserHelper userHelper;
    private static SQLiteDatabase db;

    public UserHelper(Context context) {
        dbUserHelper = new DbUserHelper(context);
    }

    public static UserHelper getInstance(Context context) {
        if (userHelper == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (userHelper == null) {
                    userHelper = new UserHelper(context);
                }
            }
        }
        return userHelper;
    }

    public void open() throws SQLException {
        db = dbUserHelper.getWritableDatabase();
    }

    public void close() {
        dbUserHelper.close();
        if (db.isOpen())
            db.close();
    }

    public Cursor queryAll() {
        return db.query(DB_TABLE,
                null,
                null,
                null,
                null,
                null,
                ID+ " ASC");
    }

    public Cursor queryById(String string) {
        return db.query(DB_TABLE, null
                , ID + " = ?"
                , new String[]{string}
                , null
                , null
                , null
                , null);
    }
    public ArrayList<User> getDataUser(){
        ArrayList<User> userlist = new ArrayList<>();
        Cursor cursor = db.query(DB_TABLE,null,
                null,
                null,
                null,
                null,
                USERNAME + " ASC",
                null);
        cursor.moveToFirst();
        User user;
        if (cursor.getCount() > 0){
            do {
                user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                user.setLogin(cursor.getString(cursor.getColumnIndexOrThrow(USERNAME)));
                user.setReposUrl(cursor.getString(cursor.getColumnIndexOrThrow(URLREPOS)));
                user.setAvatarUrl(cursor.getString(cursor.getColumnIndexOrThrow(AVATAR)));
                user.setType(cursor.getString(cursor.getColumnIndexOrThrow(TYPE)));
                userlist.add(user);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }cursor.close();
        return userlist;
    }

    public long userInsert(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,user.getId());
        contentValues.put(USERNAME,user.getLogin());
        contentValues.put(URLREPOS,user.getReposUrl());
        contentValues.put(AVATAR,user.getAvatarUrl());
        contentValues.put(TYPE,user.getType());

        return db.insert(DB_TABLE,null, contentValues);

    }

    public int userDelete(String string){
        return db.delete(TABLE_NAME,ID + " = '" + string + "'", null);
    }


    public int DeleteProvider(String string) {
        return db.delete(TABLE_NAME, ID+ "=?",new String[]{string});
    }
    public int UpdateProvider(String string, ContentValues values) {
        return db.update(DB_TABLE, values, ID + " =?", new String[]{string});
    }
    public long InsertProvider(ContentValues values) {
        return db.insert(DB_TABLE, null, values);
    }
}

