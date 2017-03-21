package com.fengniao.myandroidapplication;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;

public class BannerActivity extends BaseActivity {
    @BindView(R.id.linear_dot)
    LinearLayout linearDot;
    @BindView(R.id.view_pager)
    ViewPager banner;
    private int[] img = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4};
    private View[] dot = new View[img.length];
    private int currentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        initView();
    }


    public void initView() {
        initDot();
        BannerAdapter adapter = new BannerAdapter(img);
        banner.setAdapter(adapter);
        banner.setCurrentItem(img.length * 50);
        banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                dot[currentPosition].setEnabled(false);
                dot[position % 4].setEnabled(true);
                currentPosition = position % 4;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }

    public void initDot() {
        for (int i = 0; i < img.length; i++) {
            ImageView dotView = new ImageView(this);
            dotView.setPadding(8, 8, 8, 8);
            dotView.setImageResource(R.drawable.img_dot);
            dotView.setEnabled(false);
            dot[i] = dotView;
            linearDot.addView(dotView);
        }
        dot[currentPosition].setEnabled(true);
    }

}
