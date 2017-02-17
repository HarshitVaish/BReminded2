package com.example.android.breminded.ui.landing;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.android.breminded.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalenderFragment extends Fragment {
  CalendarView birthdayCalenderView;

    public CalenderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_calender,container,false);
        birthdayCalenderView=(CalendarView)v.findViewById(R.id.calender_view_birthday);


        return v;
    }

}
