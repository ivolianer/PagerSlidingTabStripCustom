package com.astuetz.example;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.astuetz.PagerSlidingTabStrip;
import com.astuetz.TabView;
import com.astuetz.example.CustomPagerAdapter;
import com.astuetz.example.IconTabView;
import com.example.PagerSlidingTabStrip.R;

import java.util.ArrayList;
import java.util.List;


public class ExampleActivity extends FragmentActivity {

    // ============== colors ==============

    public static int blue;

    public static int red;

    public static int green;

    public static int purple;

    public static int orange;

    // ============== override ==============

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initColors();

        setContentView(R.layout.example);

        List<TabView> tabViewList = generateTabViewList();
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager(), tabViewList.size()));

        PagerSlidingTabStrip pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pagerSlidingTabStrip.realConstructor(viewPager, tabViewList, orange);
    }

    // ============== private methods ==============

    // use after onCreate
    private void initColors() {

        Resources resources = getResources();
        blue = resources.getColor(android.R.color.holo_blue_light);
        red = resources.getColor(android.R.color.holo_red_light);
        green = resources.getColor(android.R.color.holo_green_light);
        purple = resources.getColor(android.R.color.holo_purple);
        orange = resources.getColor(android.R.color.holo_orange_light);
    }

    private List<TabView> generateTabViewList() {

        List<TabView> tabViewList = new ArrayList<TabView>();
        tabViewList.add(new IconTabView(this, "fa-windows", blue));
        tabViewList.add(new IconTabView(this, "fa-stack-overflow", red));
        tabViewList.add(new IconTabView(this, "fa-android", green));
        tabViewList.add(new IconTabView(this, "fa-github-alt", purple));

        return tabViewList;
    }

}

