package com.example.android.breminded;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class SavedBirthdayActivity extends AppCompatActivity {
    ListView listviewSavedBirthday;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_birthday_activity);
        listviewSavedBirthday=(ListView)findViewById(R.id.list_view_saved_Birthday);
        backButton=(Button)findViewById(R.id.button_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               setResult(RESULT_OK);
                finish();
            }
        });
    }
}
