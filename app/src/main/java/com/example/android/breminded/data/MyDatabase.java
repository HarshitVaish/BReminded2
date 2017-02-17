package com.example.android.breminded.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.breminded.model.Birthday;

import java.util.ArrayList;

/**
 * Created by harshit009 on 9/27/2016.
 */
public class MyDatabase {
    private SQLiteDatabase database;
    private BirthdayDbHelper dbHelper;

    public MyDatabase(Context context) {
        dbHelper = new BirthdayDbHelper(context);
    }

    private SQLiteDatabase openReadableDatabaseInstance() {
        return dbHelper.getReadableDatabase();
    }

    private SQLiteDatabase openWritableDatabaseInstance() {
        return dbHelper.getWritableDatabase();
    }

    private void closeDataBaseConnection() {
        database.close();
        dbHelper.close();
    }

    public ArrayList<Birthday> getBirthdays(String formattedDate) {
        database = openReadableDatabaseInstance();
        /*calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        formattedDate = df.format(calendar.getTime());*/

        String[] projections = {BirthdayContract.BirthdayEntry.COLUMN_NAME, BirthdayContract.BirthdayEntry.COLUMN_DATE, BirthdayContract.BirthdayEntry.COLUMN_GROUP};
        String selection = BirthdayContract.BirthdayEntry.COLUMN_DATE + " like ?";
        String[] selectionArgs = {formattedDate+"%"};
        Cursor cursor = database.query(BirthdayContract.BirthdayEntry.TABLE_NAME, projections, selection, selectionArgs, null, null, null);
        ArrayList<Birthday> birthdayArrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.COLUMN_NAME));
                String date = cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.COLUMN_DATE));
                String phoneNo = cursor.getString(cursor.getColumnIndex(String.valueOf(BirthdayContract.BirthdayEntry.COLUMN_PHONENO)));
                String message = cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.COLUMN_MESSAGE));
                String group = cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.COLUMN_GROUP));
                Birthday bt = new Birthday(name, date, group, phoneNo, message);
                birthdayArrayList.add(bt);
            } while (cursor.moveToNext());
        }
        closeDataBaseConnection();

        return birthdayArrayList;

    }

    public ArrayList<String> getAllBirthdayNamesByDate(String date) {
    /*    database = openReadableDatabaseInstance();
        String[] projections={BirthdayContract.BirthdayEntry._ID, BirthdayContract.BirthdayEntry.COLUMN_NAME, BirthdayContract.BirthdayEntry.COLUMN_DATE,BirthdayContract.BirthdayEntry.COLUMN_GROUP, BirthdayContract.BirthdayEntry.COLUMN_MESSAGE};
        String orderBy= BirthdayContract.BirthdayEntry.COLUMN_DATE +"ASC";
        Cursor cursor = database.query(BirthdayContract.BirthdayEntry.TABLE_NAME, projections, null, null, null, null, orderBy);
        ArrayList<String> birthdayArrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.COLUMN_NAME));
                String date = cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.COLUMN_DATE));
                int phoneNo = cursor.getInt(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.COLUMN_PHONENO));
                String message = cursor.getString(cursor.getColumnIndex(BirthdayContract.BirthdayEntry.COLUMN_MESSAGE));

                Birthday bt=new Birthday(name,date,phoneNo,message);
                birthdayArrayList.add(bt);
            } while (cursor.moveToNext());
        }
        closeDataBaseConnection();

        return birthdayArrayList;

*/
        database = openReadableDatabaseInstance();

        String[] projections = {BirthdayContract.BirthdayEntry.COLUMN_NAME};
        String orderBy = BirthdayContract.BirthdayEntry.COLUMN_NAME + " ASC ";
        String selections = BirthdayContract.BirthdayEntry.COLUMN_DATE + " like ?";

        String[] selectionArgs = {date+"%"};
        ArrayList<String> birthdayArrayList = new ArrayList<>();

        Cursor cursor = database.query(true, BirthdayContract.BirthdayEntry.TABLE_NAME, projections, selections, selectionArgs, null, null, orderBy, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                birthdayArrayList.add(name);
            } while (cursor.moveToNext());
        }

        closeDataBaseConnection();
        return birthdayArrayList;
    }

    public ArrayList<String> getAllDates() {

        database = openReadableDatabaseInstance();

        String[] projections = {BirthdayContract.BirthdayEntry.COLUMN_DATE};
        String orderBy = BirthdayContract.BirthdayEntry.COLUMN_DATE + " ASC ";

        Cursor cursor = database.query(true, BirthdayContract.BirthdayEntry.TABLE_NAME, projections, null, null, null, null, orderBy, null);
        ArrayList<String> arrayListDates = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(0);
                String[] dateParts=date.split("/");
                String day=dateParts[0];
                String month=dateParts[1];
                String dayNMonth=day+" / "+month;

                if(arrayListDates.contains(dayNMonth)){
                    continue;
                } else {
                    arrayListDates.add(dayNMonth);
                }

            } while (cursor.moveToNext());
        }

        closeDataBaseConnection();

        return arrayListDates;
    }


    public long addBirthdays(String name, String date, String group, String message, String phoneNo) {

        database = openWritableDatabaseInstance();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BirthdayContract.BirthdayEntry.COLUMN_NAME, name);
        contentValues.put(BirthdayContract.BirthdayEntry.COLUMN_DATE, date);
        contentValues.put(BirthdayContract.BirthdayEntry.COLUMN_GROUP, group);
        contentValues.put(String.valueOf(BirthdayContract.BirthdayEntry.COLUMN_PHONENO), phoneNo);
        contentValues.put(BirthdayContract.BirthdayEntry.COLUMN_MESSAGE, message);
        long value = database.insert(BirthdayContract.BirthdayEntry.TABLE_NAME, null, contentValues);

        closeDataBaseConnection();

        return value;

    }

    private class BirthdayDbHelper extends SQLiteOpenHelper {

        private static final String SQL_CREATE_BIRTHDAY_TABLE = "CREATE TABLE " + BirthdayContract.BirthdayEntry.TABLE_NAME + "("
                + BirthdayContract.BirthdayEntry._ID + " INTEGER PRIMARY KEY, "
                + BirthdayContract.BirthdayEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + BirthdayContract.BirthdayEntry.COLUMN_DATE + " TEXT NOT NULL, "
                + BirthdayContract.BirthdayEntry.COLUMN_GROUP + " TEXT NOT NULL, "
                + BirthdayContract.BirthdayEntry.COLUMN_PHONENO + " TEXT NOT NULL , "
                + BirthdayContract.BirthdayEntry.COLUMN_MESSAGE + " TEXT NOT NULL);";


        private static final String SQL_DROP_BIRTHDAY_TABLE = "DROP TABLE " + BirthdayContract.BirthdayEntry.TABLE_NAME + ";";
        //endregion

        private static final String DATABASE_NAME = "BeReminded.db";

        private static final int DATABASE_VERSION = 3;

        public BirthdayDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(SQL_CREATE_BIRTHDAY_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            if (newVersion > oldVersion) {
                sqLiteDatabase.execSQL(SQL_DROP_BIRTHDAY_TABLE);
                onCreate(sqLiteDatabase);

            }
        }
    }
}

