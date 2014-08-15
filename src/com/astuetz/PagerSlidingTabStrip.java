package com.astuetz;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.List;


public class PagerSlidingTabStrip extends HorizontalScrollView implements OnPageChangeListener {

    private ViewPager viewPager;

    private LinearLayout.LayoutParams expandedTabLayoutParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);

    private LinearLayout tabsContainer;

    private int currentPosition = 0;

    private float currentPositionOffset = 0f;

    private Paint paint;

    private int indicatorColor;

    private int underlineColor;

    private int scrollOffset = 52;

    private int indicatorHeight = 4;

    private int underlineHeight = 2;

    private int lastScrollX = 0;

    public PagerSlidingTabStrip(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    public void realConstructor(ViewPager viewPager, List<TabView> viewList, int color) {

        indicatorColor = color;
        underlineColor = color;

        setFillViewport(true);
        setWillNotDraw(false);

        initViewPager(viewPager);
        initTabsContainer(viewList);

        valuesToDp();
        initPaint();
    }

    private void initViewPager(ViewPager viewPager) {

        this.viewPager = viewPager;
        viewPager.setOnPageChangeListener(this);
    }

    private void initTabsContainer(List<TabView> viewList) {

        tabsContainer = new LinearLayout(getContext());
        tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabsContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        fillTabsContainer(viewList);
        addView(tabsContainer);
    }

    private void valuesToDp() {

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        scrollOffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scrollOffset, displayMetrics);
        indicatorHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorHeight, displayMetrics);
        underlineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, underlineHeight, displayMetrics);
    }

    private void initPaint() {

        paint = new Paint();
        paint.setAntiAlias(true);               // 抗锯齿
        paint.setStyle(Style.FILL);          // 实心
    }

    public void fillTabsContainer(List<TabView> tabViewList) {

        for (int i = 0, size = tabViewList.size(); i < size; i++) {
            addTabView(i, (View) tabViewList.get(i));
        }
    }

    private void addTabView(final int position, View view) {

        view.setOnClickListener(new OnClickListener() {

                                    @Override
                                    public void onClick(View v) {

                                        viewPager.setCurrentItem(position);
                                    }
                                }
        );
        tabsContainer.addView(view, position, expandedTabLayoutParams);
    }

    private void scrollToChild(int position, int offset) {

        int newScrollX = tabsContainer.getChildAt(position).getLeft() + offset;

        if (position > 0 || offset > 0) {
            newScrollX -= scrollOffset;
        }

        if (newScrollX != lastScrollX) {
            lastScrollX = newScrollX;
            scrollTo(newScrollX, 0);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        final int height = getHeight();

        // draw indicator line

        paint.setColor(indicatorColor);

        // default: line below current tab
        View currentTab = tabsContainer.getChildAt(currentPosition);
        float lineLeft = currentTab.getLeft();
        float lineRight = currentTab.getRight();

        // if there is an offset, start interpolating left and right coordinates between current and next tab
        if (currentPositionOffset > 0f) {

            View nextTab = tabsContainer.getChildAt(currentPosition + 1);
            final float nextTabLeft = nextTab.getLeft();
            final float nextTabRight = nextTab.getRight();

            lineLeft = (currentPositionOffset * nextTabLeft + (1f - currentPositionOffset) * lineLeft);
            lineRight = (currentPositionOffset * nextTabRight + (1f - currentPositionOffset) * lineRight);
        }

        canvas.drawRect(lineLeft, height - indicatorHeight, lineRight, height, paint);

        // draw underline

        paint.setColor(underlineColor);
        canvas.drawRect(0, height - underlineHeight, tabsContainer.getWidth(), height, paint);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        currentPosition = position;
        currentPositionOffset = positionOffset;

        scrollToChild(position, (int) (positionOffset * tabsContainer.getChildAt(position).getWidth()));

        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0, count = tabsContainer.getChildCount(); i != count; i++) {
            TabView tabView = (TabView) tabsContainer.getChildAt(i);
            if (i == position) {
                tabView.afterPageSelected();
            } else {
                tabView.afterPageLeft();
            }
        }
    }

}
