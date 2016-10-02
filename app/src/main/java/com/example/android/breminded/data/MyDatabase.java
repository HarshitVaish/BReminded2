package com.example.android.breminded.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.breminded.data.BirthdayContract;

/**
 * Created by harshit009 on 9/27/2016.
 */
public class MyDatabase {
    private SQLiteDatabase database;
    private BirthdayDbHelper dbHelper;
public MyDatabase(Context context){ dbHelper=new BirthdayDbHelper(context);}

    private SQLiteDatabase openReadableDatabaseInstance(){ return dbHelper.getReadableDatabase();
}
    private SQLiteDatabase openWritableDatabaseInstance() {
        return dbHelper.getWritableDatabase();
    }
private void closeDataBaseConnection(){
   database.close();
    dbHelper.close();
}
    public long createUserCredentials(String name, String date,String group, String message,Long phoneNo) {

        database = openWritableDatabaseInstance();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BirthdayContract.UserCredentialsEntry.COLUMN_NAME, name);
        contentValues.put(BirthdayContract.UserCredentialsEntry.COLUMN_DATE, date);
        contentValues.put(BirthdayContract.UserCredentialsEntry.COLUMN_GROUP,group);
        contentValues.put(BirthdayContract.UserCredentialsEntry.COLUMN_PHONENO,phoneNo);
        contentValues.put(BirthdayContract.UserCredentialsEntry.COLUMN_MESSAGE,message);
        long value = database.insert(BirthdayContract.UserCredentialsEntry.TABLE_NAME, null, contentValues);

        closeDataBaseConnection();

        return value;

    }

    private class BirthdayDbHelper extends SQLiteOpenHelper {

            private static final String SQL_CREATE_USER_CREDENTIALS_TABLE = "CREATE TABLE " + BirthdayContract.UserCredentialsEntry.TABLE_NAME + "("
                    + BirthdayContract.UserCredentialsEntry._ID + " INTEGER PRIMARY KEY, "
                    + BirthdayContract.UserCredentialsEntry.COLUMN_NAME + " TEXT NOT NULL, "
                    + BirthdayContract.UserCredentialsEntry.COLUMN_DATE + " TEXT NOT NULL"
                    + BirthdayContract.UserCredentialsEntry.COLUMN_GROUP + " TEXT NOT NULL, "
                    +BirthdayContract.UserCredentialsEntry.COLUMN_PHONENO+ "INTEGER NOT NULL"
                    + BirthdayContract.UserCredentialsEntry.COLUMN_MESSAGE + " TEXT NOT NULL);";





        private static final String SQL_DROP_USER_CREDENTIALS_TABLE = "DROP TABLE " + BirthdayContract.UserCredentialsEntry.TABLE_NAME + ";";
        //endregion

        private static final String DATABASE_NAME = "BeReminded.db";

        private static final int DATABASE_VERSION = 1;

        public BirthdayDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);


        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(SQL_CREATE_USER_CREDENTIALS_TABLE);

        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            if (newVersion > oldVersion) {
                sqLiteDatabase.execSQL(SQL_DROP_USER_CREDENTIALS_TABLE);
                onCreate(sqLiteDatabase);

            }
        }
    }}

