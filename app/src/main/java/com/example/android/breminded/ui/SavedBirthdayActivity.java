package com.example.android.breminded.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.breminded.R;
import com.example.android.breminded.data.MyDatabase;

import java.util.ArrayList;

public class SavedBirthdayActivity extends AppCompatActivity {
    ListView listviewSavedBirthday;
    ArrayList<String> arrayListAllBirthdays = new ArrayList<>();
     static String saveDate;
    Button backButton;
  public static final int REQ_CODE2=1;
    /*public class ShowAllBirthdaysAdapter extends BaseAdapter{

        @Override
        public int getCount() {
           return arrayListAllBirthdays.size();
        }

        @Override
        public Object getItem(int i) {
            return arrayListAllBirthdays.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final Birthday bd=arrayListAllBirthdays.get(i);
            View v=getLayoutInflater().inflate(android.R.layout.simple_list_item_1,null);
            listviewSavedBirthday=(ListView)v.findViewById(R.id.list_view_saved_Birthday);
            backButton=(Button)v.findViewById(R.id.button_back);

            return v;
        }
    }*/

    MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_birthday_activity);
        listviewSavedBirthday = (ListView) findViewById(R.id.all_birthday_list_view);
        backButton = (Button) findViewById(R.id.button_back);


        database = new MyDatabase(this);


        arrayListAllBirthdays.addAll(database.getAllDates());
        ArrayAdapter<String> datesArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrayListAllBirthdays);
        listviewSavedBirthday.setAdapter(datesArrayAdapter);
        datesArrayAdapter.notifyDataSetChanged();

        listviewSavedBirthday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SavedBirthdayActivity.this, SavedNamesOfBirthdays.class);
                String date=arrayListAllBirthdays.get(i);
                intent.putExtra("SELECTED DATE",date);
                startActivityForResult(intent,REQ_CODE2);

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);

                finish();
            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
  if(requestCode==REQ_CODE2){
      if(resultCode==RESULT_OK){

      }
  }
    }
}
