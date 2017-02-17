package com.example.android.breminded.ui.landing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.breminded.R;
import com.example.android.breminded.model.SendTextMessage;

import java.util.ArrayList;

public class sendTextMessageActivity extends AppCompatActivity {
    Button backButton2, sendTextButton;
    EditText editextMessages;
    TextView numberTextView;
    ArrayList<SendTextMessage> arraylistMessages = new ArrayList<>();

    /* public class textMessageAdapter extends BaseAdapter {

         @Override
         public int getCount() {
             return arraylistMessages.size();
         }

         @Override
         public Object getItem(int i) {
             return arraylistMessages.get(i);
         }

         @Override
         public long getItemId(int i) {
             return i;
         }

         @Override
         public View getView(int i, View view, ViewGroup viewGroup) {
             final SendTextMessage sd = arraylistMessages.get(i);
             View v = getLayoutInflater().inflate(R.layout.sendmessge_view, null);
             sendTextButton = (Button) v.findViewById(R.id.send_message_button);
             editextMessages = (EditText) v.findViewById(R.id.messge_sent);
             numberTextView = (TextView) v.findViewById(R.id.number_text_view);
             return v;
         }
     }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_text_message);
        backButton2 = (Button) findViewById(R.id.back_msg_button);
        sendTextButton = (Button) findViewById(R.id.send_message_button);
        editextMessages = (EditText) findViewById(R.id.messge_sent);
        numberTextView = (TextView) findViewById(R.id.number_text_view);



        sendTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}

