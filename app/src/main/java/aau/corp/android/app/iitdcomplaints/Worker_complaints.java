package aau.corp.android.app.iitdcomplaints;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class Worker_complaints extends AppCompatActivity {

    Spinner search_list, filter_list;
    ArrayAdapter<CharSequence> search_list_adapter, filter_list_adapter;
    String[] title_array,time_array,status_array,room_array,contact_array,type_array,description_array,id_array, hostel_array, posted_by_first_name_array, posted_by_last_name_array, image_array;
    TableLayout worker_complaint_table,worker_resolved_complaint_table,worker_unresolved_complaint_table;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_complaints);

        worker_unresolved_complaint_table = (TableLayout) findViewById(R.id.worker_unresolved_complaint_table);
        worker_resolved_complaint_table =(TableLayout) findViewById(R.id.worker_resolved_complaint_table);
        worker_complaint_table = (TableLayout) findViewById(R.id.worker_complaint_table);

        worker_unresolved_complaint_table.setVisibility(View.GONE);
        worker_resolved_complaint_table.setVisibility(View.GONE);
        worker_complaint_table.setVisibility(View.VISIBLE);


        filter_list = (Spinner) findViewById(R.id.spinner_worker_filter_list);
        filter_list_adapter = ArrayAdapter.createFromResource(this, R.array.filter_list, android.R.layout.simple_spinner_item);
        filter_list_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter_list.setAdapter(filter_list_adapter);

        filter_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = parent.getItemAtPosition(position).toString();

                if (selectedItem.equals("Show Resolved Only")) {


                    worker_complaint_table.setVisibility(View.GONE);
                    worker_unresolved_complaint_table.setVisibility(View.GONE);
                    worker_resolved_complaint_table.setVisibility(View.VISIBLE);

                } else if (selectedItem.equals("Show Unresolved Only")) {

                    worker_complaint_table.setVisibility(View.GONE);
                    worker_resolved_complaint_table.setVisibility(View.GONE);
                    worker_unresolved_complaint_table.setVisibility(View.VISIBLE);

                } else {

                    worker_resolved_complaint_table.setVisibility(View.GONE);
                    worker_unresolved_complaint_table.setVisibility(View.GONE);
                    worker_complaint_table.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        displaydata();

    }


    public void displaydata()
    {

        final ProgressDialog messageDialog = new ProgressDialog(this);
        messageDialog.setMessage("Logging in");
        messageDialog.show();

        String adder1 = IPAddress.getName();

        final String user_id = Profile_data.getuserid();

        String url;
        url = "http://" + adder1 + "/complaint_system/show_complaints/show_worker_complaints.php";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("hello1", response.toString());

                        Toast.makeText(Worker_complaints.this, response.toString(), Toast.LENGTH_SHORT).show();
                        getresponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Worker_complaints.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            //used to store data and sent the string request
            @Override
            protected LinkedHashMap<String, String> getParams() {
                LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
                if(Profile_data.getWorkertype() == "Electrician")
                {data.put("worker_type", "1");}
                else if(Profile_data.getWorkertype() == "Plumber")
                {data.put("worker_type", "2");}
                else if(Profile_data.getWorkertype() == "Carpenter")
                {data.put("worker_type", "3");}
                else if(Profile_data.getWorkertype() == "Internet Issues")
                {data.put("worker_type", "4");}
                else if(Profile_data.getWorkertype() == "Sweeper")
                {data.put("worker_type", "5");}
                else if(Profile_data.getWorkertype() == "Others")
                {data.put("worker_type", "6");}
                else
                {data.put("worker_type", "0");}


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

        TableLayout worker_complaint_table = (TableLayout) findViewById(R.id.worker_complaint_table);
        worker_complaint_table.setColumnShrinkable(2, true);
        worker_complaint_table.setStretchAllColumns(true);


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

                    Intent in = new Intent(Worker_complaints.this, ParticularPersonalComplaint.class);


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
            worker_complaint_table.addView(row1);
            ///////////////////////////////////////////////////////

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

            // status.setText(posted_by_array[i]);
            blank5.setText("Posted By:");
            posted.setText(posted_by_first_name_array[i] + " " +posted_by_last_name_array[i]);
            // status.setTextSize(15);
            posted.setTextSize(15);
            row5.addView(blank5);
            row5.addView(posted);
            row5.setLayoutParams(lp5);
            row5.setGravity(Gravity.CENTER);
            worker_complaint_table.addView(row5);



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
            worker_complaint_table.addView(row2);


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
            worker_complaint_table.addView(row3);
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
            worker_complaint_table.addView(row4);
        }


//////////////////////////////////////////////
///////show resolved


        TableLayout worker_resolved_complaint_table = (TableLayout) findViewById(R.id.worker_resolved_complaint_table);
        worker_resolved_complaint_table.setColumnShrinkable(2, true);
        worker_resolved_complaint_table.setStretchAllColumns(true);


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

                        Intent in = new Intent(Worker_complaints.this, ParticularPersonalComplaint.class);


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
                worker_resolved_complaint_table.addView(row1);

                ///////////////////////////////////////////////////////

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

                // status.setText(posted_by_array[i]);
                blank5.setText("Posted By:");
                posted.setText(posted_by_first_name_array[i] + " " +posted_by_last_name_array[i]);
                // status.setTextSize(15);
                posted.setTextSize(15);
                row5.addView(blank5);
                row5.addView(posted);
                row5.setLayoutParams(lp5);
                row5.setGravity(Gravity.CENTER);
                worker_resolved_complaint_table.addView(row5);



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
                worker_resolved_complaint_table.addView(row2);


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
                worker_resolved_complaint_table.addView(row3);
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
                worker_resolved_complaint_table.addView(row4);
            } else {
            }

        }


/////////////////////////////////////////////////////

///unresolved complaint table


        TableLayout worker_unresolved_complaint_table = (TableLayout) findViewById(R.id.worker_unresolved_complaint_table);
        worker_unresolved_complaint_table.setColumnShrinkable(2, true);
        worker_unresolved_complaint_table.setStretchAllColumns(true);

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

                        Intent in = new Intent(Worker_complaints.this, ParticularPersonalComplaint.class);


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
                worker_unresolved_complaint_table.addView(row1);


                ///////////////////////////////////////////////////////

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

                // status.setText(posted_by_array[i]);
                blank5.setText("Posted By:");
                posted.setText(posted_by_first_name_array[i] + " " +posted_by_last_name_array[i]);
                // status.setTextSize(15);
                posted.setTextSize(15);
                row5.addView(blank5);
                row5.addView(posted);
                row5.setLayoutParams(lp5);
                row5.setGravity(Gravity.CENTER);
                worker_unresolved_complaint_table.addView(row5);



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
                worker_unresolved_complaint_table.addView(row2);


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
                worker_unresolved_complaint_table.addView(row3);
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
                worker_unresolved_complaint_table.addView(row4);
            } else {
            }


        }

    }




    }
