package com.example.edel.moodle1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseActivity extends AppCompatActivity {
    private static final String COOKIE_KEY = "Cookie";
    private static final String SESSION_COOKIE = "sessionid";
    private static final String TAG1 = "message1";

    String course_url = "http://10.192.37.78:2207/courses/list.json";
    private List<Courses> myCourses = new ArrayList<Courses>();
    public Courses currentCourse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Intent intent1 = getIntent();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        final TextView textView = (TextView)findViewById(R.id.courseac_textView);
        ListView listView = (ListView)findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg  = (ViewGroup) view;
                TextView code = (TextView)vg.findViewById(R.id.code);

                currentCourse = myCourses.get(position);

                Intent intent = new Intent(CourseActivity.this,CourseTabActivity.class);
               intent.putExtra("course_code",currentCourse.getCode());
                startActivity(intent);


            }
        });

        populateListView();


       /* String[] items = new String[]{"Ujjawal", "Garvit", "Deepanker", "Mayank", "Garvit","SRK","Salman"};
        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.course_list_item, items);

        listView.setAdapter(adapter);*/


        com.android.volley.toolbox.StringRequest course_resp = new StringRequest(Request.Method.GET, course_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG1, "response:" + response.toString());

                        try{

                            JSONObject resp = new JSONObject(response);
                            JSONArray course = resp.getJSONArray("courses");
                            String data = "";
                            for (int i = 0; i < course.length(); i++){

                                JSONObject jsonObject = course.getJSONObject(i);

                                // int id = Integer.parseInt(jsonObject.optString("id").toString());

                                String code = jsonObject.getString("code");
                                String desc = jsonObject.getString("description");
                                String name = jsonObject.getString("name");
                                String credits = jsonObject.getString("credits");
                                String format = jsonObject.getString("l_t_p");
                                String id = jsonObject.getString("id");


                                myCourses.add(new Courses(code,desc,name,credits,format,id));
                            }
                        }
                        catch(JSONException e){e.printStackTrace();}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");

                    }
                })// StringRequest ends here

        {
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();//super.getHeaders();
                String sessionId = MainActivity._preferences.getString(SESSION_COOKIE, "");
                Log.i(TAG1,sessionId);

                headers.put(COOKIE_KEY, sessionId);

                //addSessionCookie(headers);

                return headers;
            }
        };
        requestQueue.add(course_resp);



    }//onCreate ends here

    private void populateListView() {
        ArrayAdapter<Courses> adapter = new MyListAdapter();
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

    public class MyListAdapter extends ArrayAdapter<Courses> {
        public MyListAdapter(){
            super(CourseActivity.this,R.layout.item_view,myCourses);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Make sure we have a view to work with
            View itemView = convertView;

            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.item_view,parent,false);
            }

            //Find the course to work with
            currentCourse = myCourses.get(position);


            //Fill the view

            TextView code_text = (TextView) itemView.findViewById(R.id.code);
            code_text.setText(currentCourse.getCode());

            TextView name_text = (TextView) itemView.findViewById(R.id.name);
            name_text.setText(currentCourse.getName());

            TextView credits_text = (TextView) itemView.findViewById(R.id.credits);
            credits_text.setText(currentCourse.getCredits());


            TextView format_text = (TextView) itemView.findViewById(R.id.format);
            format_text.setText(currentCourse.getFormat());

            TextView desc_text = (TextView) itemView.findViewById(R.id.coursedescription_textView);
            desc_text.setText(currentCourse.getDescription());




            return itemView;
        }
    }
}
