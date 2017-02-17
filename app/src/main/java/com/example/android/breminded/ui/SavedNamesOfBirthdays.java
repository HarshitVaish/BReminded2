package com.example.android.breminded.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.breminded.R;
import com.example.android.breminded.data.MyDatabase;

import java.util.ArrayList;

public class SavedNamesOfBirthdays extends AppCompatActivity {

ListView listViewNames;
    ArrayList<String> arrayListNames=new ArrayList<>();
    ArrayAdapter<String> arrayAdapterNames;
    Button backButton;
    MyDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_names_of_birthdays);
        listViewNames=(ListView) findViewById(R.id.listview_names);
        backButton=(Button)findViewById(R.id.go_back_button);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String date=bundle.getString("SELECTED DATE");
        database=new MyDatabase(this);
        arrayListNames.addAll(database.getAllBirthdayNamesByDate(date));
        arrayAdapterNames=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayListNames);
        listViewNames.setAdapter(arrayAdapterNames);
        arrayAdapterNames.notifyDataSetChanged();
    backButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setResult(RESULT_OK);
            finish();
        }
    });
    }


    }

