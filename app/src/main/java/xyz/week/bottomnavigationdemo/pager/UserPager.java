package xyz.week.bottomnavigationdemo.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


import xyz.week.bottomnavigationdemo.base.BasePager;

import static android.content.ContentValues.TAG;

/**
 * Created by LLL on 2018/2/25 0025.
 */

public class UserPager extends BasePager {

    private TextView textView;

    public UserPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.d(TAG, "initView: 用户页面初始化");
        textView = new TextView(context);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG, "initView: 用户数据初始化");
        textView.setText("用户页面");
    }
}
