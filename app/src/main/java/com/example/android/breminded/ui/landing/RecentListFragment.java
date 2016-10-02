package com.example.android.breminded.ui.landing;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.breminded.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class RecentListFragment extends Fragment {
ListView recentListView;
    String[] array;
    ArrayAdapter<String> recentAdapter;

    public RecentListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_list, container, false);
    }

}
