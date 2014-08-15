package com.astuetz.example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.PagerSlidingTabStrip.R;


public class CardFragment extends Fragment {

    private  int position = 0;

    public CardFragment(int position) {

        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.card_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText("" + position);

        return rootView;
    }

}