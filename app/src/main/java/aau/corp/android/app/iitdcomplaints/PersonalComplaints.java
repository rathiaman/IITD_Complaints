package aau.corp.android.app.iitdcomplaints;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
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

import java.util.LinkedHashMap;

public class PersonalComplaints extends AppCompatActivity {

    Spinner search_list, filter_list;
    ArrayAdapter<CharSequence> search_list_adapter, filter_list_adapter;
    Button launch_personal_complaint;

    String[] title_array,time_array,status_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_complaints);
        onButtonClickListener_Launch_Personal();

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

        displaydata();


    }

    public void onButtonClickListener_Launch_Personal(){
        launch_personal_complaint = (Button) findViewById(R.id.launch_complaint);
        launch_personal_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(PersonalComplaints.this, LaunchPersonalComplaint.class);
                startActivity(in);

            }
        });
    }


    public void displaydata()
    {

        String adder1 = IPAddress.getName();

        final String user_id = Profile_data.getuserid();
        Toast.makeText(PersonalComplaints.this, "good1", Toast.LENGTH_SHORT).show();

        String url;
        url = "http://" + adder1 + "/complaint_system/show_complaints/show_personal_complaints.php";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("hello1", response.toString());
                        Toast.makeText(PersonalComplaints.this, "good2", Toast.LENGTH_SHORT).show();
                        Toast.makeText(PersonalComplaints.this, response.toString(), Toast.LENGTH_SHORT).show();
                        getresponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PersonalComplaints.this, "bad", Toast.LENGTH_SHORT).show();
                        Toast.makeText(PersonalComplaints.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            //used to store data and sent the string request
            @Override
            protected LinkedHashMap<String, String> getParams() {
                LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
                data.put("user_id", user_id);

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

                //title_array[i] = "tit";
                //status_array[i] ="stat";
               time_array[i] = childJSONObject.getString("time");
                Toast.makeText(PersonalComplaints.this, status_array[i], Toast.LENGTH_SHORT).show();


                //  time_array[i] = "time";
            }

            /////////////

//            Toast.makeText(ThreadActivity.this,"size" + title_array.length, Toast.LENGTH_SHORT ).show();

            create_complaint_table();
        }catch (JSONException e){
            e.printStackTrace();}



    }

    public void create_complaint_table()
    {
        TableLayout complaint_table = (TableLayout) findViewById(R.id.complaint_table);

        for(int i =0 ; i< title_array.length ; i++) {

            ///////////////////////////////////
            //Creating new tablerows and textviews
            ///////////////////////////////////
            TableRow row    =   new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView sno = new TextView(this);
            TextView title   =   new TextView(this);
            TextView status=   new TextView(this);
            TextView updated_at   =   new TextView(this);

            ///////////////////////////////////
            //setting the text
            ///////////////////////////////////
           // title.setText(title_array[i]);
            title.setText("hw");
            status.setText("ygd");
            updated_at.setText("hjd");
            sno.setText(String.valueOf(i + 1));
            //status.setText(status_array[i]);
            //updated_at.setText(time_array[i]);

            ///////////////////////////////////
            //setting the allignment
            ///////////////////////////////////
            sno.setGravity(Gravity.CENTER);
            title.setGravity(Gravity.CENTER);
            status.setGravity(Gravity.CENTER);
            updated_at.setGravity(Gravity.CENTER);
            title.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            title.setTypeface(Typeface.DEFAULT);
            title.setTextColor(Color.BLUE);
            title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            ///////////////////////////////////
            //declaration as final so that i can be used in onclicklistener
            ///////////////////////////////////
           // final int idOfThread = id_array[i];

            ///////////////////////////////////
            // On Click Listener
            ///////////////////////////////////
            title.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // Toast.makeText(ThreadActivity.this,"Clicked",Toast.LENGTH_SHORT).show();



                }
            });

            ///////////////////////////////////
            //the textviews have to be added to the row created
            ///////////////////////////////////
            row.addView(sno);
            row.addView(title);
            row.addView(status);
            row.addView(updated_at);

            complaint_table.addView(row);}


    }
}
