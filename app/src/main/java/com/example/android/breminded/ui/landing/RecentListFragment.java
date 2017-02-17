package com.example.android.breminded.ui.landing;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.breminded.R;
import com.example.android.breminded.data.MyDatabase;
import com.example.android.breminded.model.Birthday;
import com.example.android.breminded.ui.AlarmReceiver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */

public class RecentListFragment extends Fragment {
    ListView recentListView;
    public static final int REQ_Code2 = 1;
    TextView nameTextView, groupTextView;
    Button sendMessage;
    MyDatabase db;
    int mDay, mMonth;
    String strDay, strMonth;
    PendingIntent pendingIntent;
    private ArrayList<Birthday> arrayListRecent = new ArrayList<>();

    public class recentAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return arrayListRecent.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayListRecent.get(position);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final Birthday bd = arrayListRecent.get(i);
            View v = getActivity().getLayoutInflater().inflate(R.layout.birthday_row, null);
            nameTextView = (TextView) v.findViewById(R.id.text_view_name);
            groupTextView = (TextView) v.findViewById(R.id.text_view_date);
            sendMessage = (Button) v.findViewById(R.id.button_send);
            nameTextView.setText(bd.getName());
            groupTextView.setText(bd.getGroup());

           sendMessage.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getActivity(), sendTextMessageActivity.class);
                   startActivityForResult(intent, REQ_Code2);

               }
           });

            return v;
        }
    }

    ;

    public RecentListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_recent_list,null);
        db = new MyDatabase(getActivity());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM");
        String strDate = mdformat.format(calendar.getTime());
        recentListView = (ListView)v.findViewById(R.id.list_view_today_birthday);

        arrayListRecent.addAll(db.getBirthdays(strDate));
        recentAdapter rc = new recentAdapter();
        recentListView.setAdapter(rc);

        rc.notifyDataSetChanged();
        try {
            Calendar calendar1 = Calendar.getInstance();

            calendar1.setTimeInMillis(System.currentTimeMillis());
            calendar1.set(Calendar.HOUR_OF_DAY, 0);
            calendar1.set(Calendar.MINUTE, 15);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent myIntent = new Intent(getActivity(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, myIntent,0);


        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);


        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_Code2) {
            if (resultCode == REQ_Code2) {

            }
        }
    }
}
