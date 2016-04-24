package com.example.edel.moodle1;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Apple on 24/02/16.
 */
public class CourseFragmentTab extends android.support.v4.app.Fragment {

    ///courses/course.json/<coursecode>/assignments
    String u1 = "http://10.192.37.78:2207/courses/course.json/";
    String u2 = "/assignments";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.course_fragment_layout, container, false);
        TextView tv = (TextView) v.findViewById(R.id.course_fragment_tab_text);
        tv.setText(getTag() + " for this course");

        ImageButton ib = (ImageButton)v.findViewById(R.id.course_fragment_imageButton);
        if (getTag() =="Assignment") {
            ib.setImageResource(R.drawable.board_64);


        }
        if (getTag() == "Threads"){
            ib.setImageResource(R.drawable.chat);
        }

        if (getTag() == "Grades"){
            ib.setImageResource(R.drawable.badge);
        }
        return v;
    }
}
