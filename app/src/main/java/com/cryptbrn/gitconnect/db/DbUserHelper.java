package com.cryptbrn.gitconnect.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.cryptbrn.gitconnect.db.DbUserContract.UserColumns.TABLE_NAME;

public class DbUserHelper extends SQLiteOpenHelper {
    private static final String USER_DB_NAME = "dbuserfav";
    private static final int USER_DB_VERSION = 2;
    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY ," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT)",
            TABLE_NAME,
            DbUserContract.UserColumns.ID,
            DbUserContract.UserColumns.USERNAME,
            DbUserContract.UserColumns.URLREPOS,
            DbUserContract.UserColumns.AVATAR,
            DbUserContract.UserColumns.TYPE
    );

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public DbUserHelper(Context context){
        super(context,USER_DB_NAME,null,USER_DB_VERSION);
    }
}