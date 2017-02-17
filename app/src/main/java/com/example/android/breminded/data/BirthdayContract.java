package com.example.android.breminded.data;

import android.provider.BaseColumns;

/**
 * Created by harshit009 on 9/28/2016.
 */
public class BirthdayContract {

    private BirthdayContract(){}

        public static final class BirthdayEntry implements BaseColumns {
            public static final String TABLE_NAME="birthday_table";
            public static final String COLUMN_NAME="Name";
            public static final String COLUMN_DATE="Date";
            public static final String COLUMN_PHONENO="Phone_no";
            public static final String COLUMN_GROUP="GroupName";
            public static final String COLUMN_MESSAGE="Message";
    }
}
