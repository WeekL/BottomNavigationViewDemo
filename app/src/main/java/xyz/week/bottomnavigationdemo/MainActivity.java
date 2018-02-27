package xyz.week.bottomnavigationdemo;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import xyz.week.bottomnavigationdemo.adapter.ViewPagerAdapter;
import xyz.week.bottomnavigationdemo.base.BasePager;
import xyz.week.bottomnavigationdemo.pager.AudioPager;
import xyz.week.bottomnavigationdemo.pager.MorePager;
import xyz.week.bottomnavigationdemo.pager.UserPager;
import xyz.week.bottomnavigationdemo.pager.VideoPager;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private MenuItem menuItem;

    private ViewPager viewPager;

    private List<BasePager> pagerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        disableShiftMode();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_video:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.bottom_audio:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.bottom_more:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.bottom_user:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                        viewPager.setCurrentItem(0);
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    //如果有已选中的item，则取消它的的选中状态
                    menuItem.setChecked(false);
                } else {
                    //如果没有，则取消默认的选中状态（第一个item）
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                //让与当前Pager相应的item变为选中状态
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.content_view_pager);
    }

    private void initData() {
        pagerList.add(new VideoPager(this));
        pagerList.add(new AudioPager(this));
        pagerList.add(new MorePager(this));
        pagerList.add(new UserPager(this));
        ViewPagerAdapter adapter = new ViewPagerAdapter(pagerList);
        viewPager.setAdapter(adapter);
    }

    @SuppressLint("RestrictedApi")
    private void disableShiftMode(){
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                itemView.setShiftingMode(false);
                itemView.setChecked(itemView.getItemData().isChecked());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
