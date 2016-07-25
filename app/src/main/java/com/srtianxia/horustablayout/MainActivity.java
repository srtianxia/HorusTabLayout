package com.srtianxia.horustablayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.srtianxia.tablayoutlibs.HorusTabLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private HorusTabLayout mHorusTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHorusTabLayout = (HorusTabLayout) findViewById(R.id.tab_layout);
        List<String> titles = new ArrayList<>();
        titles.add("标题1");
        titles.add("标题2");
        titles.add("标题3");
        mHorusTabLayout.setTitle(titles);
        mHorusTabLayout.setOnTabSelectListener(new HorusTabLayout.OnTabSelectListener() {
            @Override public void onClick(int position) {
                Log.d("position --------->" , String.valueOf(position));
            }
        });
    }
}
