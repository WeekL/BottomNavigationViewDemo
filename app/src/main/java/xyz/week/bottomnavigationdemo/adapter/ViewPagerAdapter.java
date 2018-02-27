package xyz.week.bottomnavigationdemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.week.bottomnavigationdemo.base.BasePager;


/**
 * Created by LLL on 2018/2/27 0027.
 */

public class ViewPagerAdapter extends PagerAdapter {

    List<BasePager> pagerList = new ArrayList<>();

    public ViewPagerAdapter(List<BasePager> list) {
        pagerList = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePager pager = pagerList.get(position);
        View view = pager.rootView;
        if (!pager.isInitData()) {
            pager.initData();
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pagerList.get(position).rootView);
    }

    @Override
    public int getCount() {
        return pagerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
