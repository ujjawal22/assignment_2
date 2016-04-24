package com.example.edel.moodle1;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CourseTabActivity extends FragmentActivity {



    private FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        String cour_code = intent.getStringExtra("course_code");


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_course_tab);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("Assignment").setIndicator("Assignment",null),
                CourseFragmentTab.class,null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Grades").setIndicator("Grades", null),
                CourseFragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Threads").setIndicator("Threads", null),
                CourseFragmentTab.class, null);
    }
}
