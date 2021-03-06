package aau.corp.android.app.iitdcomplaints;

import android.app.ProgressDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class ParticularPersonalComplaint extends AppCompatActivity {


    Button personal_post_comment, mark_as_resolved;
    ImageView particular_personal_complaint_image;
    private static EditText personal_comment;
    String complaint_id1, complaint_title1, complaint_roomno1, complaint_contactinfo1, complaint_complainttype1, complaint_status1, complaint_description1;
    String complaint_hostel1, complaint_posted_by_first_name_1, complaint_posted_by_last_name_1;

    String logged_in_user_first_name, logged_in_user_last_name;

    String[] comment_array, time_comment_array, first_name_array, last_name_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_personal_complaint);

        mark_as_resolved_button();
        personal_comment = (EditText) findViewById(R.id.particular_personal_complaint_add_comment_answer);
        personal_post_comment = (Button) findViewById(R.id.particular_personal_complaint_post_comment);

        Bundle extras = getIntent().getExtras();

        show_image();

        ///////////////////////////////////
        // Extracting from intent bundle
        ///////////////////////////////////

        complaint_title1 = PersonalComplaintDetails.getParticular_personal_complaint_title();
        complaint_roomno1 = PersonalComplaintDetails.getParticular_personal_complaint_room_no();
        complaint_contactinfo1 = PersonalComplaintDetails.getParticular_personal_complaint_contact_info();
        complaint_complainttype1 = PersonalComplaintDetails.getParticular_personal_complaint_complaint_type();
        complaint_status1 = PersonalComplaintDetails.getParticular_personal_complaint_status();
        complaint_description1 = PersonalComplaintDetails.getParticular_personal_complaint_description();
        complaint_id1 = PersonalComplaintDetails.getParticular_personal_complaint_id();
        complaint_hostel1 = PersonalComplaintDetails.getParticular_personal_complaint_hostel();
        complaint_posted_by_first_name_1 = PersonalComplaintDetails.getParticular_personal_complaint_posted_by_first_name();
        complaint_posted_by_last_name_1 = PersonalComplaintDetails.getParticular_personal_complaint_posted_by_last_name();

        TextView complaint_title = (TextView) findViewById(R.id.particular_personal_complaint_title_answer);
        TextView complaint_postedby = (TextView) findViewById(R.id.particular_personal_complaint_posted_by_answer);
        TextView complaint_roomno = (TextView) findViewById(R.id.particular_personal_complaint_room_no_answer);
        TextView complaint_hostel = (TextView) findViewById(R.id.particular_personal_complaint_hostel_answer);
        TextView complaint_type = (TextView) findViewById(R.id.particular_personal_complaint_type_answer);
        TextView complaint_contactinfo = (TextView) findViewById(R.id.particular_personal_complaint_contact_info_answer);
        TextView complaint_status = (TextView) findViewById(R.id.particular_personal_complaint_status_answer);
        TextView complaint_description = (TextView) findViewById(R.id.particular_personal_complaint_description_answer);


        complaint_title.setText(complaint_title1);
        complaint_postedby.setText(complaint_posted_by_first_name_1 + " " + complaint_posted_by_last_name_1);
        complaint_roomno.setText(complaint_roomno1);
        complaint_hostel.setText(complaint_hostel1);

        complaint_type.setText(complaint_complainttype1);
        complaint_contactinfo.setText(complaint_contactinfo1);
        complaint_status.setText(complaint_status1);
        complaint_description.setText(complaint_description1);

        //logged_in_user = Profile_data.getfirst_Name() + " " + Profile_data.getlast_Name();
        logged_in_user_first_name = Profile_data.getfirst_Name();
        logged_in_user_last_name = Profile_data.getlast_Name();

      //  Toast.makeText(ParticularPersonalComplaint.this, logged_in_user_first_name + " ==== logged in user first name", Toast.LENGTH_LONG).show();
      //  Toast.makeText(ParticularPersonalComplaint.this, logged_in_user_last_name + " ==== logged in user last name", Toast.LENGTH_LONG).show();

        if (logged_in_user_first_name == complaint_posted_by_first_name_1) {
            if (logged_in_user_last_name == complaint_posted_by_last_name_1) {
             //   Toast.makeText(ParticularPersonalComplaint.this, " Ghusa andar", Toast.LENGTH_LONG).show();
                complaint_postedby.setVisibility(View.GONE);
            }
        }

        display_comment_data();


///////////////////////////////////////////////////////////////
        //posting a new comment
        personal_post_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitNewComment();
                personal_comment.setText("");
                Intent in = new Intent(ParticularPersonalComplaint.this, ParticularPersonalComplaint.class);
                startActivity(in);

            }
        });


    }

    public void submitNewComment() {

        final String post_comment = personal_comment.getText().toString();

        final ProgressDialog messageDialog = new ProgressDialog(this);
        messageDialog.setMessage("sending the information");
        messageDialog.show();

        //url for grades
        String adder1 = IPAddress.getName();
        String url = "http://" + adder1 + "/complaint_system/tools/add_comment.php";


        if (post_comment.length() == 0){Toast.makeText(ParticularPersonalComplaint.this, "The comment size is zero", Toast.LENGTH_SHORT).show();}

        else {
            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            messageDialog.hide();
                            //      Toast.makeText(Particular_thread.this, response.toString(), Toast.LENGTH_SHORT).show();
                            //dialog box
                            JSONObject mainObject;

                            try {
                                mainObject = new JSONObject(response);
                                String json_success = mainObject.getString("success");

                                if (json_success == "true") {
                                    Toast.makeText(ParticularPersonalComplaint.this, "Comment Posted. Reload ", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ParticularPersonalComplaint.this, "Unable to Post Comment ", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ParticularPersonalComplaint.this, "Network Error", Toast.LENGTH_SHORT).show();

                        }
                    }) {

                //used to store data and sent the string request
                @Override
                protected LinkedHashMap<String, String> getParams() {
                    LinkedHashMap<String, String> login_credentials = new LinkedHashMap<String, String>();
                    login_credentials.put("complaint_id", complaint_id1);
                    login_credentials.put("complaint_type", "1");
                    login_credentials.put("first_name", Profile_data.getfirst_Name());
                    login_credentials.put("last_name", Profile_data.getlast_Name());
                    login_credentials.put("comment_text", post_comment);
                    return login_credentials;
                }


            };

            MySingleton.getInstance(this).addToRequestQueue(request);

        }

    }

    public void onBackPressed() {

        if (Profile_data.getAccount_type() == "Worker"){
            Intent in = new Intent(ParticularPersonalComplaint.this, Worker_complaints.class);
            startActivity(in);

        }
       else {
            Intent in = new Intent(ParticularPersonalComplaint.this, PersonalComplaints.class);
            startActivity(in);
        }
    }
    public void display_comment_data() {

        String adder1 = IPAddress.getName();
        String url = "http://" + adder1 + "/complaint_system/tools/show_comments.php";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  Toast.makeText(ParticularPersonalComplaint.this, response.toString(), Toast.LENGTH_SHORT).show();
                        getresponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ParticularPersonalComplaint.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            //used to store data and sent the string request
            @Override
            protected LinkedHashMap<String, String> getParams() {
                LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
                data.put("complaint_id", complaint_id1);
                data.put("complaint_type", "1");

                return data;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(request);
    }

    public void getresponse(String response) {

        JSONArray mainObject;

        try {
            mainObject = new JSONArray(response);


            //declaring the size of the array

            comment_array = new String[mainObject.length()];
            first_name_array = new String[mainObject.length()];
            last_name_array = new String[mainObject.length()];
            time_comment_array = new String[mainObject.length()];


            for (int i = 0; i < mainObject.length(); i++) {
                JSONObject childJSONObject = mainObject.getJSONObject(i);
                comment_array[i] = childJSONObject.getString("comment");
                time_comment_array[i] = childJSONObject.getString("time");
                first_name_array[i] = childJSONObject.getString("first_name");
                last_name_array[i] = childJSONObject.getString("last_name");


            }

            create_complaint_table();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void create_complaint_table() {

        TableLayout course_assig_table = (TableLayout) findViewById(R.id.particular_personal_complaint_comment_table);
        course_assig_table.setColumnShrinkable(2, true);
        course_assig_table.setStretchAllColumns(true);


        for (int i = 0; i < comment_array.length; i++) {
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
            TextView name = new TextView(this);
            //setting the values of textview
            sno.setText(String.valueOf(i + 1) + ". ");
            name.setText(first_name_array[i] + " " + last_name_array[i]);
            //for giving span to the name
            TableRow.LayoutParams trParam = new TableRow.LayoutParams();
            trParam.column = 1;
            //layout parametrrs for the name
            name.setLayoutParams(trParam);
            name.setTextSize(15);
            name.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            name.setTypeface(Typeface.DEFAULT);

            sno.setTextSize(15);
            sno.setTypeface(null, Typeface.BOLD);
            //add textview to the row

            //finished with setting layout

            row1.addView(sno);
            row1.addView(name);
            row1.setGravity(Gravity.CENTER);
            //set the layoout parameters for the row
            course_assig_table.addView(row1);
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
            TextView comment_given = new TextView(this);
            //TextView time = new TextView(this);

            comment_given.setText(comment_array[i]);
            blank2.setText("Comment:");
            // time.setText(time_array[i]);
            comment_given.setTextSize(15);
            // time.setTextSize(15);
            row2.addView(blank2);
            row2.addView(comment_given);

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
            time.setText(time_comment_array[i]);
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

    public void show_image() {

        particular_personal_complaint_image = (ImageView) findViewById(R.id.particular_personal_complaint_image);

        String address = PersonalComplaintDetails.getParticular_personal_complaint_image();
        String adder1 = IPAddress.getName();

        if (address.equals("no_image")) {
            particular_personal_complaint_image.setVisibility(View.GONE);
        } else {
            Picasso.with(this).load("http://" + adder1 + address).into(particular_personal_complaint_image);
        }
    }

    public void mark_as_resolved_button() {
        mark_as_resolved = (Button) findViewById(R.id.particular_personal_complaint_mark_as_resolved);

        if (PersonalComplaintDetails.getParticular_personal_complaint_status().equals("resolved")){
            mark_as_resolved.setVisibility(View.GONE);
        }

        else {
            if (Profile_data.getAccount_type().equals("Student")){

                mark_as_resolved.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // calls the alert dialogue box
                        AlertDialog.Builder submit_alert = new AlertDialog.Builder(ParticularPersonalComplaint.this);
                        submit_alert.setMessage("").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // calls the function which send the request to the server
                                marke_resolve_request();
                                mark_as_resolved.setVisibility(View.GONE);
                                TextView complaint_status = (TextView) findViewById(R.id.particular_personal_complaint_status_answer);
                                complaint_status.setText("Resolved");

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {    // If no is pressed, you are taken back to the login screen
                                dialog.cancel();
                            }
                        });

                        AlertDialog alert = submit_alert.create();
                        alert.setTitle("Are you sure you want to mark this complaint as RESOLVED  !!!");
                        alert.show();

                    }
                });
            }

            else {
                mark_as_resolved.setVisibility(View.GONE);
            }

        }

}
    private void marke_resolve_request() {

        String adder1 = IPAddress.getName();
        String url = "http://" + adder1 + "/complaint_system/tools/change_status.php";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                     //   Toast.makeText(ParticularPersonalComplaint.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ParticularPersonalComplaint.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            //used to store data and sent the string request
            @Override
            protected LinkedHashMap<String, String> getParams() {
                LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
                data.put("complaint_id", complaint_id1);
                data.put("complaint_type","1");

                return data;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(request);
    }

}