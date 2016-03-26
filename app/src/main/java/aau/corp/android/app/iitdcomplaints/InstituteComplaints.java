package aau.corp.android.app.iitdcomplaints;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

public class InstituteComplaints extends AppCompatActivity {

    Spinner search_list, filter_list;
    ArrayAdapter<CharSequence> search_list_adapter, filter_list_adapter;
    Button launch_institute_complaint;
    String[] title_array,time_array,status_array,posted_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_complaints);
        onButtonClickListener_Launch_Institute();

        search_list = (Spinner) findViewById(R.id.spinner_search_list);
        search_list_adapter = ArrayAdapter.createFromResource(this, R.array.search_list, android.R.layout.simple_spinner_item);
        search_list_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        search_list.setAdapter(search_list_adapter);

        search_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " --", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        filter_list = (Spinner) findViewById(R.id.spinner_filter_list);
        filter_list_adapter = ArrayAdapter.createFromResource(this, R.array.filter_list, android.R.layout.simple_spinner_item);
        filter_list_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter_list.setAdapter(filter_list_adapter);

        filter_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

     display_institutedata();
    }

    public void onButtonClickListener_Launch_Institute(){
        launch_institute_complaint = (Button) findViewById(R.id.launch_complaint_institute);
        launch_institute_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(InstituteComplaints.this, LaunchInstituteComplaint.class);
                startActivity(in);

            }
        });
    }


    public void display_institutedata()
    {

        String adder1 = IPAddress.getName();


        String url;
        url = "http://" + adder1 + "/complaint_system/show_complaints/show_institute_complaints.php";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("hello1", response.toString());
                        Toast.makeText(InstituteComplaints.this, response.toString(), Toast.LENGTH_SHORT).show();
                        getresponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InstituteComplaints.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            //used to store data and sent the string request
            @Override
            protected LinkedHashMap<String, String> getParams() {
                LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();

                return data;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(request);
    }

    public void getresponse(String response)
    {

        JSONArray mainObject ;

        try{
            mainObject = new JSONArray(response);


            //declaring the size of the array

            title_array = new String[mainObject.length()];
            status_array = new String[mainObject.length()];
            time_array = new String[mainObject.length()];
            posted_array = new String[mainObject.length()];

            for (int i = 0; i < mainObject.length(); i++) {
                JSONObject childJSONObject = mainObject.getJSONObject(i);
                title_array[i] = childJSONObject.getString("title");
                int stat = childJSONObject.getInt("status");
                if (stat ==0 )
                {status_array[i] = "unresolved";}
                else if (stat == 1)
                {status_array[i] = "pending";}
                else
                {
                    status_array[i] = "resolved";
                }
                time_array[i] = childJSONObject.getString("time");
                posted_array[i] = childJSONObject.getString("first_name");


                //  time_array[i] = "time";
            }

            create_complaint_table();
        }catch (JSONException e){
            e.printStackTrace();}



    }

    public void create_complaint_table()
    {

        TableLayout course_assig_table = (TableLayout) findViewById(R.id.complaint_institute_table);
        course_assig_table.setColumnShrinkable(2, true);
        course_assig_table.setStretchAllColumns(true);


        for (int i = 0; i < title_array.length; i++) {
            //Creating new tablerows and textviews
            TableRow row1 = new TableRow(this);
            //layout parameters
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            int leftMargin = 0;
            int topMargin = 0;
            int rightMargin = 0;
            int bottomMargin = 0;
            lp.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            //making textview
            TextView sno = new TextView(this);
            TextView title = new TextView(this);
            //setting the values of textview
            sno.setText(String.valueOf(i + 1) + ". ");
            title.setText(title_array[i]);
            //for giving span to the name
            TableRow.LayoutParams trParam = new TableRow.LayoutParams();
            trParam.column = 1;
            //layout parametrrs for the name
            title.setLayoutParams(trParam);
            title.setTextSize(15);
            title.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            title.setTypeface(Typeface.DEFAULT);
            title.setTextColor(Color.BLUE);
            title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            sno.setTextSize(15);
            sno.setTypeface(null, Typeface.BOLD);
            //add textview to the row

            //finished with setting layout

            row1.addView(sno);
            row1.addView(title);
            row1.setGravity(Gravity.CENTER);
            //set the layoout parameters for the row
            course_assig_table.addView(row1);


//////////////////////////////////////

            TableRow row5 = new TableRow(this);
            TableRow.LayoutParams lp5 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            int leftMargin5 = 0;
            int topMargin5 = 0;
            int rightMargin5 = 0;
            int bottomMargin5 = 0;
            lp5.setMargins(leftMargin5, topMargin5, rightMargin5, bottomMargin5);
            //making textview

            TextView blank5 = new TextView(this);
            //TextView status = new TextView(this);
            TextView posted = new TextView(this);

            // status.setText(status_array[i]);
            blank5.setText("Posted By:");
            posted.setText(posted_array[i]);
            // status.setTextSize(15);
            posted.setTextSize(15);
            row5.addView(blank5);
            row5.addView(posted);
            row5.setLayoutParams(lp5);
            row5.setGravity(Gravity.CENTER);
            course_assig_table.addView(row5);
///////////////////////////////////////////////////////

            TableRow row2 = new TableRow(this);
            TableRow.LayoutParams lp2 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            int leftMargin2 = 0;
            int topMargin2 = 0;
            int rightMargin2 = 0;
            int bottomMargin2 = 0;
            lp2.setMargins(leftMargin2, topMargin2, rightMargin2, bottomMargin2);
            //making textview

            TextView blank2 = new TextView(this);
            TextView status = new TextView(this);
            //TextView time = new TextView(this);

            status.setText(status_array[i]);
            blank2.setText("Status:");
            // time.setText(time_array[i]);
            status.setTextSize(15);
            // time.setTextSize(15);
            row2.addView(blank2);
            row2.addView(status);

            // row3.addView(time);
            row2.setLayoutParams(lp2);
            row2.setGravity(Gravity.CENTER);
            course_assig_table.addView(row2);




///////////////////////////////////////////////////////
            TableRow row3 = new TableRow(this);
            TableRow.LayoutParams lp3 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            int leftMargin3 = 0;
            int topMargin3 = 0;
            int rightMargin3 = 0;
            int bottomMargin3 = 0;
            lp3.setMargins(leftMargin3, topMargin3, rightMargin3, bottomMargin3);
            //making textview

            TextView blank3 = new TextView(this);
            //TextView status = new TextView(this);
            TextView time = new TextView(this);

            // status.setText(status_array[i]);
            blank3.setText("Posted At:");
            time.setText(time_array[i]);
            // status.setTextSize(15);
            time.setTextSize(15);
            row3.addView(blank3);
            row3.addView(time);
            row3.setLayoutParams(lp3);
            row3.setGravity(Gravity.CENTER);
            course_assig_table.addView(row3);

/////////////////////////////////////
            //entering blank row



            TableRow row4 = new TableRow(this);
            TextView blank_1 = new TextView(this);
            TextView blank_2 = new TextView(this);
            TextView blank_3 = new TextView(this);
            blank_1.setText("  ");
            blank_2.setText("  ");
            blank_3.setText("  ");
            row4.addView(blank_1);
            row4.addView(blank_2);
            row4.addView(blank_3);
            row3.setLayoutParams(lp3);
            course_assig_table.addView(row4);
        }

    }



}
