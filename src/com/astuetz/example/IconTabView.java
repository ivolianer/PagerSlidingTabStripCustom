package com.astuetz.example;

import MyAwesomeTry.FontAwesomeIconView;
import android.content.Context;
import android.view.Gravity;
import com.astuetz.TabView;


public class IconTabView extends FontAwesomeIconView implements TabView {

    private int color;

    IconTabView(Context context, String icon, int color) {

        super(context);
        this.color = color;

        setGravity(Gravity.CENTER);
        setTextSize(26);
        setIcon(icon);
        setTextColor(color);
    }

    @Override
    public void afterPageSelected() {

        setTextColor(ExampleActivity.orange);
    }

    @Override
    public void afterPageLeft() {

        setTextColor(color);
    }

}