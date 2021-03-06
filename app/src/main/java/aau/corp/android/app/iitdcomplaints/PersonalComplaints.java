package aau.corp.android.app.iitdcomplaints;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
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

    String[] title_array,time_array,status_array,room_array,contact_array,type_array,description_array,id_array, hostel_array, posted_by_first_name_array, posted_by_last_name_array, image_array;

    TableLayout all_complaint_table,resolved_complaint_table,unresolved_complaint_table;
    TableLayout electricity_complaint_table,plumber_complaint_table,carpentry_complaint_table;
    TableLayout internet_complaint_table,sweeper_complaint_table,other_complaint_table;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_complaints);
        onButtonClickListener_Launch_Personal();

        unresolved_complaint_table = (TableLayout) findViewById(R.id.unresolved_complaint_table);
        resolved_complaint_table =(TableLayout) findViewById(R.id.resolved_complaint_table);
        all_complaint_table = (TableLayout) findViewById(R.id.complaint_table);
        electricity_complaint_table=(TableLayout)findViewById(R.id.electricity_complaint_table);
        plumber_complaint_table = (TableLayout)findViewById(R.id.plumber_complaint_table);
        carpentry_complaint_table = (TableLayout) findViewById(R.id.carpentry_complaint_table);
        internet_complaint_table = (TableLayout)findViewById(R.id.internet_complaint_table);
        sweeper_complaint_table = (TableLayout)findViewById(R.id.sweeper_complaint_table);
        other_complaint_table = (TableLayout)findViewById(R.id.other_complaint_table);


        unresolved_complaint_table.setVisibility(View.GONE);
        resolved_complaint_table.setVisibility(View.GONE);
        all_complaint_table.setVisibility(View.VISIBLE);
        electricity_complaint_table.setVisibility(View.GONE);
        plumber_complaint_table.setVisibility(View.GONE);
        carpentry_complaint_table.setVisibility(View.GONE);
        internet_complaint_table.setVisibility(View.GONE);
        sweeper_complaint_table.setVisibility(View.GONE);
        other_complaint_table.setVisibility(View.GONE);



        search_list = (Spinner) findViewById(R.id.spinner_search_list);
        search_list_adapter = ArrayAdapter.createFromResource(this, R.array.search_list, android.R.layout.simple_spinner_item);
        search_list_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        search_list.setAdapter(search_list_adapter);

        search_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " --", Toast.LENGTH_LONG).show();

                String selectedItem = parent.getItemAtPosition(position).toString();

                if(selectedItem.equals("Electricity"))
                {

                    unresolved_complaint_table.setVisibility(View.GONE);
                    resolved_complaint_table.setVisibility(View.GONE);
                    all_complaint_table.setVisibility(View.GONE);
                    electricity_complaint_table.setVisibility(View.VISIBLE);
                    plumber_complaint_table.setVisibility(View.GONE);
                    carpentry_complaint_table.setVisibility(View.GONE);
                    internet_complaint_table.setVisibility(View.GONE);
                    sweeper_complaint_table.setVisibility(View.GONE);
                    other_complaint_table.setVisibility(View.GONE);


                }

                else if(selectedItem.equals("Plumber"))
                {

                    unresolved_complaint_table.setVisibility(View.GONE);
                    resolved_complaint_table.setVisibility(View.GONE);
                    all_complaint_table.setVisibility(View.GONE);
                    electricity_complaint_table.setVisibility(View.GONE);
                    plumber_complaint_table.setVisibility(View.VISIBLE);
                    carpentry_complaint_table.setVisibility(View.GONE);
                    internet_complaint_table.setVisibility(View.GONE);
                    sweeper_complaint_table.setVisibility(View.GONE);
                    other_complaint_table.setVisibility(View.GONE);

                }

                else if (selectedItem.equals("Carpentry"))
                {

                    unresolved_complaint_table.setVisibility(View.GONE);
                    resolved_complaint_table.setVisibility(View.GONE);
                    all_complaint_table.setVisibility(View.GONE);
                    electricity_complaint_table.setVisibility(View.GONE);
                    plumber_complaint_table.setVisibility(View.GONE);
                    carpentry_complaint_table.setVisibility(View.VISIBLE);
                    internet_complaint_table.setVisibility(View.GONE);
                    sweeper_complaint_table.setVisibility(View.GONE);
                    other_complaint_table.setVisibility(View.GONE);

                }

                else if (selectedItem.equals("Internet Issues"))
                {

                    unresolved_complaint_table.setVisibility(View.GONE);
                    resolved_complaint_table.setVisibility(View.GONE);
                    all_complaint_table.setVisibility(View.GONE);
                    electricity_complaint_table.setVisibility(View.GONE);
                    plumber_complaint_table.setVisibility(View.GONE);
                    carpentry_complaint_table.setVisibility(View.GONE);
                    internet_complaint_table.setVisibility(View.VISIBLE);
                    sweeper_complaint_table.setVisibility(View.GONE);
                    other_complaint_table.setVisibility(View.GONE);

                }

                else if (selectedItem.equals("Sweeper"))
                {

                    unresolved_complaint_table.setVisibility(View.GONE);
                    resolved_complaint_table.setVisibility(View.GONE);
                    all_complaint_table.setVisibility(View.GONE);
                    electricity_complaint_table.setVisibility(View.GONE);
                    plumber_complaint_table.setVisibility(View.GONE);
                    carpentry_complaint_table.setVisibility(View.GONE);
                    internet_complaint_table.setVisibility(View.GONE);
                    sweeper_complaint_table.setVisibility(View.VISIBLE);
                    other_complaint_table.setVisibility(View.GONE);

                }

                else if (selectedItem.equals("Others"))
                {

                    unresolved_complaint_table.setVisibility(View.GONE);
                    resolved_complaint_table.setVisibility(View.GONE);
                    all_complaint_table.setVisibility(View.GONE);
                    electricity_complaint_table.setVisibility(View.GONE);
                    plumber_complaint_table.setVisibility(View.GONE);
                    carpentry_complaint_table.setVisibility(View.GONE);
                    internet_complaint_table.setVisibility(View.GONE);
                    sweeper_complaint_table.setVisibility(View.GONE);
                    other_complaint_table.setVisibility(View.VISIBLE);

                }

                else
                {

                    unresolved_complaint_table.setVisibility(View.GONE);
                    resolved_complaint_table.setVisibility(View.GONE);
                    all_complaint_table.setVisibility(View.VISIBLE);
                    electricity_complaint_table.setVisibility(View.GONE);
                    plumber_complaint_table.setVisibility(View.GONE);
                    carpentry_complaint_table.setVisibility(View.GONE);
                    internet_complaint_table.setVisibility(View.GONE);
                    sweeper_complaint_table.setVisibility(View.GONE);
                    other_complaint_table.setVisibility(View.GONE);

                }




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

                String selectedItem = parent.getItemAtPosition(position).toString();

                if(selectedItem.equals("Show Resolved Only"))
                {


                    all_complaint_table.setVisibility(View.GONE);
                    unresolved_complaint_table.setVisibility(View.GONE);
                    resolved_complaint_table.setVisibility(View.VISIBLE);
                    electricity_complaint_table.setVisibility(View.GONE);
                    plumber_complaint_table.setVisibility(View.GONE);
                    carpentry_complaint_table.setVisibility(View.GONE);
                    internet_complaint_table.setVisibility(View.GONE);
                    sweeper_complaint_table.setVisibility(View.GONE);
                    other_complaint_table.setVisibility(View.GONE);


                }

                else if(selectedItem.equals("Show Unresolved Only"))
                {

                    all_complaint_table.setVisibility(View.GONE);
                    resolved_complaint_table.setVisibility(View.GONE);
                    unresolved_complaint_table.setVisibility(View.VISIBLE);
                    electricity_complaint_table.setVisibility(View.GONE);
                    plumber_complaint_table.setVisibility(View.GONE);
                    carpentry_complaint_table.setVisibility(View.GONE);
                    internet_complaint_table.setVisibility(View.GONE);
                    sweeper_complaint_table.setVisibility(View.GONE);
                    other_complaint_table.setVisibility(View.GONE);


                }

                else
                {

                     resolved_complaint_table.setVisibility(View.GONE);
                    unresolved_complaint_table.setVisibility(View.GONE);
                    all_complaint_table.setVisibility(View.VISIBLE);
                    electricity_complaint_table.setVisibility(View.GONE);
                    plumber_complaint_table.setVisibility(View.GONE);
                    carpentry_complaint_table.setVisibility(View.GONE);
                    internet_complaint_table.setVisibility(View.GONE);
                    sweeper_complaint_table.setVisibility(View.GONE);
                    other_complaint_table.setVisibility(View.GONE);


                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        displaydata();


    }


    public void onBackPressed() {
        Intent in = new Intent(PersonalComplaints.this, HomeScreen.class);
        startActivity(in);
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

        final ProgressDialog messageDialog = new ProgressDialog(this);
        messageDialog.setMessage("Logging in");
        messageDialog.show();

        String adder1 = IPAddress.getName();

        final String user_id = Profile_data.getuserid();

        String url;
        url = "http://" + adder1 + "/complaint_system/show_complaints/show_personal_complaints.php";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("hello1", response.toString());

                       // Toast.makeText(PersonalComplaints.this, response.toString(), Toast.LENGTH_SHORT).show();
                        getresponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
        messageDialog.hide();
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
            room_array = new String[mainObject.length()];
            contact_array = new String[mainObject.length()];
            type_array = new String[mainObject.length()];
            description_array = new String[mainObject.length()];
            id_array = new String[mainObject.length()];
            hostel_array = new String[mainObject.length()];
            posted_by_first_name_array = new String[mainObject.length()];
            posted_by_last_name_array = new String[mainObject.length()];
            image_array = new String[mainObject.length()];

            for (int i = 0; i < mainObject.length(); i++) {
                JSONObject childJSONObject = mainObject.getJSONObject(i);
               title_array[i] = childJSONObject.getString("title");
               int stat = childJSONObject.getInt("status");
                if (stat ==0 )
                {status_array[i] = "unresolved";}
                else if (stat == 1)
                {status_array[i] = "resolved";}
                else
                {
                    status_array[i] = "resolved";
                }
               time_array[i] = childJSONObject.getString("time");
                room_array[i]= childJSONObject.getString("room_no");
                contact_array[i]=childJSONObject.getString("contact_info");
                type_array[i]= childJSONObject.getString("tags");
                description_array[i] = childJSONObject.getString("description");
                id_array[i]=childJSONObject.getString("complaint_id");
                hostel_array[i] = childJSONObject.getString("hostel");
                posted_by_first_name_array[i] = childJSONObject.getString("first_name");
                posted_by_last_name_array[i] = childJSONObject.getString("last_name");
                image_array[i] = childJSONObject.getString("image");


                //  time_array[i] = "time";
            }

            create_complaint_table();
        }catch (JSONException e){
            e.printStackTrace();}



    }

    public void create_complaint_table() {

        TableLayout all_complaint_table = (TableLayout) findViewById(R.id.complaint_table);
        all_complaint_table.setColumnShrinkable(2, true);
        all_complaint_table.setStretchAllColumns(true);


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
            final String complaint_title = title_array[i];
            final String room_no = room_array[i];
            final String contact_info = contact_array[i];
            final String complaint_type = type_array[i];
            final String complaint_status = status_array[i];
            final String complaint_description = description_array[i];
            final String complaint_id = id_array[i];
            final String complaint_hostel = hostel_array[i];
            final String complaint_posted_by_first_name = posted_by_first_name_array[i];
            final String complaint_posted_by_last_name = posted_by_last_name_array[i];
            final String complaint_image = image_array[i];


            title.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    PersonalComplaintDetails.setparticular_personal_complaint_title(complaint_title);
                    PersonalComplaintDetails.setParticular_personal_complaint_room_no(room_no);
                    PersonalComplaintDetails.setParticular_personal_complaint_contact_info(contact_info);
                    PersonalComplaintDetails.setParticular_personal_complaint_complaint_type(complaint_type);
                    PersonalComplaintDetails.setParticular_personal_complaint_status(complaint_status);
                    PersonalComplaintDetails.setParticular_personal_complaint_description(complaint_description);
                    PersonalComplaintDetails.setParticular_personal_complaint_id(complaint_id);
                    PersonalComplaintDetails.setParticular_personal_complaint_hostel(complaint_hostel);
                    PersonalComplaintDetails.setParticular_personal_complaint_posted_by_first_name(complaint_posted_by_first_name);
                    PersonalComplaintDetails.setParticular_personal_complaint_posted_by_last_name(complaint_posted_by_last_name);
                    PersonalComplaintDetails.setParticular_personal_complaint_image(complaint_image);

                    Intent in = new Intent(PersonalComplaints.this, ParticularPersonalComplaint.class);


                    startActivity(in);

                }
            });

            sno.setTextSize(15);
            sno.setTypeface(null, Typeface.BOLD);
            //add textview to the row

            //finished with setting layout

            row1.addView(sno);
            row1.addView(title);
            row1.setGravity(Gravity.CENTER);
            //set the layoout parameters for the row
            all_complaint_table.addView(row1);
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
            all_complaint_table.addView(row2);


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
            all_complaint_table.addView(row3);
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
            all_complaint_table.addView(row4);
        }


//////////////////////////////////////////////
///////show resolved


        TableLayout resolved_complaint_table = (TableLayout) findViewById(R.id.resolved_complaint_table);
        resolved_complaint_table.setColumnShrinkable(2, true);
        resolved_complaint_table.setStretchAllColumns(true);


        for (int i = 0; i < title_array.length; i++) {
            //Creating new tablerows and textviews
            if (status_array[i] == "resolved") {
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
                final String complaint_title = title_array[i];
                final String room_no = room_array[i];
                final String contact_info = contact_array[i];
                final String complaint_type = type_array[i];
                final String complaint_status = status_array[i];
                final String complaint_description = description_array[i];
                final String complaint_id = id_array[i];
                final String complaint_hostel = hostel_array[i];
                final String complaint_posted_by_first_name = posted_by_first_name_array[i];
                final String complaint_posted_by_last_name = posted_by_last_name_array[i];
                final String complaint_image = image_array[i];

                title.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        PersonalComplaintDetails.setparticular_personal_complaint_title(complaint_title);
                        PersonalComplaintDetails.setParticular_personal_complaint_room_no(room_no);
                        PersonalComplaintDetails.setParticular_personal_complaint_contact_info(contact_info);
                        PersonalComplaintDetails.setParticular_personal_complaint_complaint_type(complaint_type);
                        PersonalComplaintDetails.setParticular_personal_complaint_status(complaint_status);
                        PersonalComplaintDetails.setParticular_personal_complaint_description(complaint_description);
                        PersonalComplaintDetails.setParticular_personal_complaint_id(complaint_id);
                        PersonalComplaintDetails.setParticular_personal_complaint_hostel(complaint_hostel);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_first_name(complaint_posted_by_first_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_last_name(complaint_posted_by_last_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_image(complaint_image);

                        Intent in = new Intent(PersonalComplaints.this, ParticularPersonalComplaint.class);


                        startActivity(in);

                    }
                });
                sno.setTextSize(15);
                sno.setTypeface(null, Typeface.BOLD);
                //add textview to the row

                //finished with setting layout

                row1.addView(sno);
                row1.addView(title);
                row1.setGravity(Gravity.CENTER);
                //set the layoout parameters for the row
                resolved_complaint_table.addView(row1);
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
                resolved_complaint_table.addView(row2);


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
                resolved_complaint_table.addView(row3);
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
                resolved_complaint_table.addView(row4);
            } else {
            }

        }


/////////////////////////////////////////////////////

///unresolved complaint table


        TableLayout unresolved_complaint_table = (TableLayout) findViewById(R.id.unresolved_complaint_table);
        unresolved_complaint_table.setColumnShrinkable(2, true);
        unresolved_complaint_table.setStretchAllColumns(true);

        for (int i = 0; i < title_array.length; i++) {
            //Creating new tablerows and textviews
            if (status_array[i] == "unresolved") {

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
                final String complaint_title = title_array[i];
                final String room_no = room_array[i];
                final String contact_info = contact_array[i];
                final String complaint_type = type_array[i];
                final String complaint_status = status_array[i];
                final String complaint_description = description_array[i];
                final String complaint_id = id_array[i];
                final String complaint_hostel = hostel_array[i];
                final String complaint_posted_by_first_name = posted_by_first_name_array[i];
                final String complaint_posted_by_last_name = posted_by_last_name_array[i];
                final String complaint_image = image_array[i];

                title.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        PersonalComplaintDetails.setparticular_personal_complaint_title(complaint_title);
                        PersonalComplaintDetails.setParticular_personal_complaint_room_no(room_no);
                        PersonalComplaintDetails.setParticular_personal_complaint_contact_info(contact_info);
                        PersonalComplaintDetails.setParticular_personal_complaint_complaint_type(complaint_type);
                        PersonalComplaintDetails.setParticular_personal_complaint_status(complaint_status);
                        PersonalComplaintDetails.setParticular_personal_complaint_description(complaint_description);
                        PersonalComplaintDetails.setParticular_personal_complaint_id(complaint_id);
                        PersonalComplaintDetails.setParticular_personal_complaint_hostel(complaint_hostel);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_first_name(complaint_posted_by_first_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_last_name(complaint_posted_by_last_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_image(complaint_image);

                        Intent in = new Intent(PersonalComplaints.this, ParticularPersonalComplaint.class);


                        startActivity(in);

                    }
                });
                sno.setTextSize(15);
                sno.setTypeface(null, Typeface.BOLD);
                //add textview to the row

                //finished with setting layout

                row1.addView(sno);
                row1.addView(title);
                row1.setGravity(Gravity.CENTER);
                //set the layoout parameters for the row
                unresolved_complaint_table.addView(row1);
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
                unresolved_complaint_table.addView(row2);


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
                unresolved_complaint_table.addView(row3);
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
                unresolved_complaint_table.addView(row4);
            } else {
            }

        }


///////////////////////////////////////////////
        ////////////////electricity complaints


        TableLayout electricity_complaint_table = (TableLayout) findViewById(R.id.electricity_complaint_table);
        electricity_complaint_table.setColumnShrinkable(2, true);
        electricity_complaint_table.setStretchAllColumns(true);

        for (int i = 0; i < title_array.length; i++) {
            //Creating new tablerows and textviews
            if (Integer.parseInt(type_array[i]) == 1) {


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
                final String complaint_title = title_array[i];
                final String room_no = room_array[i];
                final String contact_info = contact_array[i];
                final String complaint_type = type_array[i];
                final String complaint_status = status_array[i];
                final String complaint_description = description_array[i];
                final String complaint_id = id_array[i];
                final String complaint_hostel = hostel_array[i];
                final String complaint_posted_by_first_name = posted_by_first_name_array[i];
                final String complaint_posted_by_last_name = posted_by_last_name_array[i];
                final String complaint_image = image_array[i];

                title.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        PersonalComplaintDetails.setparticular_personal_complaint_title(complaint_title);
                        PersonalComplaintDetails.setParticular_personal_complaint_room_no(room_no);
                        PersonalComplaintDetails.setParticular_personal_complaint_contact_info(contact_info);
                        PersonalComplaintDetails.setParticular_personal_complaint_complaint_type(complaint_type);
                        PersonalComplaintDetails.setParticular_personal_complaint_status(complaint_status);
                        PersonalComplaintDetails.setParticular_personal_complaint_description(complaint_description);
                        PersonalComplaintDetails.setParticular_personal_complaint_id(complaint_id);
                        PersonalComplaintDetails.setParticular_personal_complaint_hostel(complaint_hostel);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_first_name(complaint_posted_by_first_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_last_name(complaint_posted_by_last_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_image(complaint_image);

                        Intent in = new Intent(PersonalComplaints.this, ParticularPersonalComplaint.class);


                        startActivity(in);

                    }
                });
                sno.setTextSize(15);
                sno.setTypeface(null, Typeface.BOLD);
                //add textview to the row

                //finished with setting layout

                row1.addView(sno);
                row1.addView(title);
                row1.setGravity(Gravity.CENTER);
                //set the layoout parameters for the row
                electricity_complaint_table.addView(row1);
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
                electricity_complaint_table.addView(row2);


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
                electricity_complaint_table.addView(row3);
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
                electricity_complaint_table.addView(row4);
            } else {}

        }



////////////////////////////////

        ////////////plumber table

        TableLayout plumber_complaint_table = (TableLayout) findViewById(R.id.plumber_complaint_table);
        plumber_complaint_table.setColumnShrinkable(2, true);
        plumber_complaint_table.setStretchAllColumns(true);


        for (int i = 0; i < title_array.length; i++) {
            //Creating new tablerows and textviews
            if (Integer.parseInt(type_array[i]) == 2) {
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
                final String complaint_title = title_array[i];
                final String room_no = room_array[i];
                final String contact_info = contact_array[i];
                final String complaint_type = type_array[i];
                final String complaint_status = status_array[i];
                final String complaint_description = description_array[i];
                final String complaint_id = id_array[i];
                final String complaint_hostel = hostel_array[i];
                final String complaint_posted_by_first_name = posted_by_first_name_array[i];
                final String complaint_posted_by_last_name = posted_by_last_name_array[i];
                final String complaint_image = image_array[i];

                title.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        PersonalComplaintDetails.setparticular_personal_complaint_title(complaint_title);
                        PersonalComplaintDetails.setParticular_personal_complaint_room_no(room_no);
                        PersonalComplaintDetails.setParticular_personal_complaint_contact_info(contact_info);
                        PersonalComplaintDetails.setParticular_personal_complaint_complaint_type(complaint_type);
                        PersonalComplaintDetails.setParticular_personal_complaint_status(complaint_status);
                        PersonalComplaintDetails.setParticular_personal_complaint_description(complaint_description);
                        PersonalComplaintDetails.setParticular_personal_complaint_id(complaint_id);
                        PersonalComplaintDetails.setParticular_personal_complaint_hostel(complaint_hostel);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_first_name(complaint_posted_by_first_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_last_name(complaint_posted_by_last_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_image(complaint_image);

                        Intent in = new Intent(PersonalComplaints.this, ParticularPersonalComplaint.class);


                        startActivity(in);

                    }
                });
                sno.setTextSize(15);
                sno.setTypeface(null, Typeface.BOLD);
                //add textview to the row

                //finished with setting layout

                row1.addView(sno);
                row1.addView(title);
                row1.setGravity(Gravity.CENTER);
                plumber_complaint_table.addView(row1);
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
                plumber_complaint_table.addView(row2);


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
                plumber_complaint_table.addView(row3);
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
                plumber_complaint_table.addView(row4);
            } else {}

        }


//////////////////////////////////

        ///////////carpentry

        TableLayout carpentry_complaint_table = (TableLayout) findViewById(R.id.carpentry_complaint_table);
        carpentry_complaint_table.setColumnShrinkable(2, true);
        carpentry_complaint_table.setStretchAllColumns(true);


        for (int i = 0; i < title_array.length; i++) {
            //Creating new tablerows and textviews
            if (Integer.parseInt(type_array[i]) == 3) {
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
                final String complaint_title = title_array[i];
                final String room_no = room_array[i];
                final String contact_info = contact_array[i];
                final String complaint_type = type_array[i];
                final String complaint_status = status_array[i];
                final String complaint_description = description_array[i];
                final String complaint_id = id_array[i];
                final String complaint_hostel = hostel_array[i];
                final String complaint_posted_by_first_name = posted_by_first_name_array[i];
                final String complaint_posted_by_last_name = posted_by_last_name_array[i];
                final String complaint_image = image_array[i];

                title.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        PersonalComplaintDetails.setparticular_personal_complaint_title(complaint_title);
                        PersonalComplaintDetails.setParticular_personal_complaint_room_no(room_no);
                        PersonalComplaintDetails.setParticular_personal_complaint_contact_info(contact_info);
                        PersonalComplaintDetails.setParticular_personal_complaint_complaint_type(complaint_type);
                        PersonalComplaintDetails.setParticular_personal_complaint_status(complaint_status);
                        PersonalComplaintDetails.setParticular_personal_complaint_description(complaint_description);
                        PersonalComplaintDetails.setParticular_personal_complaint_id(complaint_id);
                        PersonalComplaintDetails.setParticular_personal_complaint_hostel(complaint_hostel);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_first_name(complaint_posted_by_first_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_last_name(complaint_posted_by_last_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_image(complaint_image);

                        Intent in = new Intent(PersonalComplaints.this, ParticularPersonalComplaint.class);


                        startActivity(in);

                    }
                });
                sno.setTextSize(15);
                sno.setTypeface(null, Typeface.BOLD);
                //add textview to the row

                //finished with setting layout

                row1.addView(sno);
                row1.addView(title);
                row1.setGravity(Gravity.CENTER);
                //set the layoout parameters for the row
                carpentry_complaint_table.addView(row1);
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
                carpentry_complaint_table.addView(row2);


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
                carpentry_complaint_table.addView(row3);
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
                carpentry_complaint_table.addView(row4);
            } else {}

        }




////////////////////////////////////////////////////////////////////
        ///////////////internet

        TableLayout internet_complaint_table = (TableLayout) findViewById(R.id.internet_complaint_table);
        internet_complaint_table.setColumnShrinkable(2, true);
        internet_complaint_table.setStretchAllColumns(true);


        for (int i = 0; i < title_array.length; i++) {
            //Creating new tablerows and textviews
            if (Integer.parseInt(type_array[i]) == 4) {
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
                final String complaint_title = title_array[i];
                final String room_no = room_array[i];
                final String contact_info = contact_array[i];
                final String complaint_type = type_array[i];
                final String complaint_status = status_array[i];
                final String complaint_description = description_array[i];
                final String complaint_id = id_array[i];
                final String complaint_hostel = hostel_array[i];
                final String complaint_posted_by_first_name = posted_by_first_name_array[i];
                final String complaint_posted_by_last_name = posted_by_last_name_array[i];
                final String complaint_image = image_array[i];

                title.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        PersonalComplaintDetails.setparticular_personal_complaint_title(complaint_title);
                        PersonalComplaintDetails.setParticular_personal_complaint_room_no(room_no);
                        PersonalComplaintDetails.setParticular_personal_complaint_contact_info(contact_info);
                        PersonalComplaintDetails.setParticular_personal_complaint_complaint_type(complaint_type);
                        PersonalComplaintDetails.setParticular_personal_complaint_status(complaint_status);
                        PersonalComplaintDetails.setParticular_personal_complaint_description(complaint_description);
                        PersonalComplaintDetails.setParticular_personal_complaint_id(complaint_id);
                        PersonalComplaintDetails.setParticular_personal_complaint_hostel(complaint_hostel);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_first_name(complaint_posted_by_first_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_last_name(complaint_posted_by_last_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_image(complaint_image);

                        Intent in = new Intent(PersonalComplaints.this, ParticularPersonalComplaint.class);


                        startActivity(in);

                    }
                });
                sno.setTextSize(15);
                sno.setTypeface(null, Typeface.BOLD);
                //add textview to the row

                //finished with setting layout

                row1.addView(sno);
                row1.addView(title);
                row1.setGravity(Gravity.CENTER);
                //set the layoout parameters for the row
                internet_complaint_table.addView(row1);
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
                internet_complaint_table.addView(row2);


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
                internet_complaint_table.addView(row3);
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
                internet_complaint_table.addView(row4);
            } else {}

        }




///////////////////////////////////////////////////////////////////////

        //////////////////sweeper


        TableLayout sweeper_complaint_table = (TableLayout) findViewById(R.id.sweeper_complaint_table);
        sweeper_complaint_table.setColumnShrinkable(2, true);
        sweeper_complaint_table.setStretchAllColumns(true);


        for (int i = 0; i < title_array.length; i++) {
            //Creating new tablerows and textviews
            if (Integer.parseInt(type_array[i]) == 5) {
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
                final String complaint_title = title_array[i];
                final String room_no = room_array[i];
                final String contact_info = contact_array[i];
                final String complaint_type = type_array[i];
                final String complaint_status = status_array[i];
                final String complaint_description = description_array[i];
                final String complaint_id = id_array[i];
                final String complaint_hostel = hostel_array[i];
                final String complaint_posted_by_first_name = posted_by_first_name_array[i];
                final String complaint_posted_by_last_name = posted_by_last_name_array[i];
                final String complaint_image = image_array[i];

                title.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        PersonalComplaintDetails.setparticular_personal_complaint_title(complaint_title);
                        PersonalComplaintDetails.setParticular_personal_complaint_room_no(room_no);
                        PersonalComplaintDetails.setParticular_personal_complaint_contact_info(contact_info);
                        PersonalComplaintDetails.setParticular_personal_complaint_complaint_type(complaint_type);
                        PersonalComplaintDetails.setParticular_personal_complaint_status(complaint_status);
                        PersonalComplaintDetails.setParticular_personal_complaint_description(complaint_description);
                        PersonalComplaintDetails.setParticular_personal_complaint_id(complaint_id);
                        PersonalComplaintDetails.setParticular_personal_complaint_hostel(complaint_hostel);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_first_name(complaint_posted_by_first_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_last_name(complaint_posted_by_last_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_image(complaint_image);

                        Intent in = new Intent(PersonalComplaints.this, ParticularPersonalComplaint.class);


                        startActivity(in);

                    }
                });
                sno.setTextSize(15);
                sno.setTypeface(null, Typeface.BOLD);
                //add textview to the row

                //finished with setting layout

                row1.addView(sno);
                row1.addView(title);
                row1.setGravity(Gravity.CENTER);
                //set the layoout parameters for the row
                sweeper_complaint_table.addView(row1);
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
                sweeper_complaint_table.addView(row2);


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
                sweeper_complaint_table.addView(row3);
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
                sweeper_complaint_table.addView(row4);
            } else {}

        }




///////////////////////////////////////////////////////////////

        //////////////////others

        TableLayout other_complaint_table = (TableLayout) findViewById(R.id.other_complaint_table);
        other_complaint_table.setColumnShrinkable(2, true);
        other_complaint_table.setStretchAllColumns(true);


        for (int i = 0; i < title_array.length; i++) {
            //Creating new tablerows and textviews
            if (Integer.parseInt(type_array[i]) == 6) {
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
                final String complaint_title = title_array[i];
                final String room_no = room_array[i];
                final String contact_info = contact_array[i];
                final String complaint_type = type_array[i];
                final String complaint_status = status_array[i];
                final String complaint_description = description_array[i];
                final String complaint_id = id_array[i];
                final String complaint_hostel = hostel_array[i];
                final String complaint_posted_by_first_name = posted_by_first_name_array[i];
                final String complaint_posted_by_last_name = posted_by_last_name_array[i];
                final String complaint_image = image_array[i];

                title.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        PersonalComplaintDetails.setparticular_personal_complaint_title(complaint_title);
                        PersonalComplaintDetails.setParticular_personal_complaint_room_no(room_no);
                        PersonalComplaintDetails.setParticular_personal_complaint_contact_info(contact_info);
                        PersonalComplaintDetails.setParticular_personal_complaint_complaint_type(complaint_type);
                        PersonalComplaintDetails.setParticular_personal_complaint_status(complaint_status);
                        PersonalComplaintDetails.setParticular_personal_complaint_description(complaint_description);
                        PersonalComplaintDetails.setParticular_personal_complaint_id(complaint_id);
                        PersonalComplaintDetails.setParticular_personal_complaint_hostel(complaint_hostel);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_first_name(complaint_posted_by_first_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_posted_by_last_name(complaint_posted_by_last_name);
                        PersonalComplaintDetails.setParticular_personal_complaint_image(complaint_image);

                        Intent in = new Intent(PersonalComplaints.this, ParticularPersonalComplaint.class);


                        startActivity(in);

                    }
                });
                sno.setTextSize(15);
                sno.setTypeface(null, Typeface.BOLD);
                //add textview to the row

                //finished with setting layout

                row1.addView(sno);
                row1.addView(title);
                row1.setGravity(Gravity.CENTER);
                //set the layoout parameters for the row
                other_complaint_table.addView(row1);
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
                other_complaint_table.addView(row2);


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
                other_complaint_table.addView(row3);
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
                other_complaint_table.addView(row4);
            } else {}

        }

    }





}
