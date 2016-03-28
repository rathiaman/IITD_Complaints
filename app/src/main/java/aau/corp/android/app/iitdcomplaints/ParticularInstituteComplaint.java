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

public class ParticularInstituteComplaint extends AppCompatActivity {


    Button institute_post_comment,button_upvote,button_downvote;
    private static EditText institute_comment;
    ImageView particular_institute_complaint_image;
    String complaint_id1,complaint_title1,complaint_contactinfo1,complaint_complainttype1,complaint_status1,complaint_description1;
    String complaint_hostel1,complaint_posted_by_first_name_1, complaint_posted_by_last_name_1;

    String logged_in_user_first_name, logged_in_user_last_name;

    String[] comment_array,time_comment_array,first_name_array,last_name_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_institute_complaint);

        institute_comment = (EditText)findViewById(R.id.particular_institute_complaint_add_comment_answer);
        institute_post_comment = (Button)findViewById(R.id.particular_institute_complaint_post_comment);
        button_downvote = (Button)findViewById(R.id.button_downvote);
        button_upvote = (Button)findViewById(R.id.button_upvote);
        button_downvote.setVisibility(View.GONE);
        button_upvote.setVisibility(View.GONE);

        show_image();
      
        ///////////////////////////////////
        // getting from institute complaint details
        //////////////////////////////////

        complaint_title1 = InstituteComplaintDetails.getParticular_institute_complaint_title();
        complaint_contactinfo1 = InstituteComplaintDetails.getParticular_institute_complaint_contact_info();
        complaint_complainttype1 = InstituteComplaintDetails.getParticular_institute_complaint_complaint_type();
        complaint_status1 = InstituteComplaintDetails.getParticular_institute_complaint_status();
        complaint_description1 = InstituteComplaintDetails.getParticular_institute_complaint_description();
        complaint_id1 = InstituteComplaintDetails.getParticular_institute_complaint_id();
        complaint_hostel1 = InstituteComplaintDetails.getParticular_institute_complaint_hostel();
        complaint_posted_by_first_name_1 = InstituteComplaintDetails.getParticular_institute_complaint_posted_by_first_name();
        complaint_posted_by_last_name_1 = InstituteComplaintDetails.getParticular_institute_complaint_posted_by_last_name();

        TextView complaint_title = (TextView)findViewById(R.id.particular_institute_complaint_title_answer);
        TextView complaint_postedby= (TextView)findViewById(R.id.particular_institute_complaint_posted_by_answer);
        TextView complaint_type =(TextView)findViewById(R.id.particular_institute_complaint_type_answer);
       // TextView complaint_contactinfo =(TextView)findViewById(R.id.particular_institute_complaint_conatct_info_answer);
        TextView complaint_status =(TextView)findViewById(R.id.particular_institute_complaint_status_answer);
        TextView complaint_description =(TextView)findViewById(R.id.particular_institute_complaint_description_answer);



        complaint_title.setText(complaint_title1);
        complaint_postedby.setText(complaint_posted_by_first_name_1 + " " + complaint_posted_by_last_name_1);
        complaint_type.setText(complaint_complainttype1);
      //  complaint_contactinfo.setText(complaint_contactinfo1);
        complaint_status.setText(complaint_status1);
        complaint_description.setText(complaint_description1);

        //logged_in_user = Profile_data.getfirst_Name() + " " + Profile_data.getlast_Name();
        logged_in_user_first_name = Profile_data.getfirst_Name();
        logged_in_user_last_name = Profile_data.getlast_Name();

        if (logged_in_user_first_name == complaint_posted_by_first_name_1){
            if (logged_in_user_last_name == complaint_posted_by_last_name_1){

                complaint_postedby.setVisibility(View.GONE);
            }
        }

        display_votes();
        display_comment_data();



///////////////////////////////////////////////////////////////
        //posting a new comment
        institute_post_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitNewComment();
                institute_comment.setText("");
                Intent in = new Intent(ParticularInstituteComplaint.this, ParticularInstituteComplaint.class);
                startActivity(in);

            }
        });


    }

    public void submitNewComment(){

        final String post_comment = institute_comment.getText().toString();

        final ProgressDialog messageDialog = new ProgressDialog(this);
        messageDialog.setMessage("sending the information");
        messageDialog.show();

        //url for grades
        String adder1 = IPAddress.getName();
        String url="http://" + adder1 + "/complaint_system/tools/add_comment.php";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        messageDialog.hide();
                        //      Toast.makeText(Particular_thread.this, response.toString(), Toast.LENGTH_SHORT).show();
                        //dialog box
                        JSONObject mainObject ;

                        try {
                            mainObject = new JSONObject(response);
                            String json_success = mainObject.getString("success");

                            if (json_success == "true")
                            {
                                Toast.makeText(ParticularInstituteComplaint.this, "Comment Posted", Toast.LENGTH_SHORT).show();}
                            else
                            {Toast.makeText(ParticularInstituteComplaint.this, "Unable to Post Comment ", Toast.LENGTH_SHORT).show();}

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ParticularInstituteComplaint.this, "Network Error", Toast.LENGTH_SHORT).show();

                    }
                }){

            //used to store data and sent the string request
            @Override
            protected LinkedHashMap<String, String> getParams() {
                LinkedHashMap<String, String> login_credentials = new LinkedHashMap<String, String>();
                login_credentials.put("complaint_id",complaint_id1 );
                login_credentials.put("complaint_type","2");
                login_credentials.put("first_name", Profile_data.getfirst_Name());
                login_credentials.put("last_name",Profile_data.getlast_Name());
                login_credentials.put("comment_text", post_comment);
                return login_credentials;
            }


        };;

        MySingleton.getInstance(this).addToRequestQueue(request);

    }

    public void display_comment_data()
    {

        String adder1 = IPAddress.getName();



        String url;
        url = "http://" + adder1 + "/complaint_system/tools/show_comments.php";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  Toast.makeText(ParticularInstituteComplaint.this, response.toString(), Toast.LENGTH_SHORT).show();
                        getresponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ParticularInstituteComplaint.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            //used to store data and sent the string request
            @Override
            protected LinkedHashMap<String, String> getParams() {
                LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
                data.put("complaint_id", complaint_id1);
                data.put("complaint_type","2");

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

            comment_array = new String[mainObject.length()];
            first_name_array = new String[mainObject.length()];
            last_name_array = new String[mainObject.length()];
            time_comment_array = new String[mainObject.length()];


            for (int i = 0; i < mainObject.length(); i++) {
                JSONObject childJSONObject = mainObject.getJSONObject(i);
                comment_array[i] = childJSONObject.getString("comment");
                time_comment_array[i] = childJSONObject.getString("time");
                first_name_array[i]= childJSONObject.getString("first_name");
                last_name_array[i]=childJSONObject.getString("last_name");


            }

            create_complaint_table();
        }catch (JSONException e){
            e.printStackTrace();}
    }

    public void display_votes()
    {
        String adder1 = IPAddress.getName();

        String url = "http://" + adder1 + "/complaint_system/tools/show_votes.php";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Toast.makeText(ParticularInstituteComplaint.this, response.toString(), Toast.LENGTH_SHORT).show();
                        update_vote(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ParticularInstituteComplaint.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            //used to store data and sent the string request
            @Override
            protected LinkedHashMap<String, String> getParams() {
                LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
                data.put("complaint_id", complaint_id1);
                data.put("complaint_type", "3");
                data.put("user_id",Profile_data.getuserid());


                return data;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(request);
    }

    public void update_vote(String response){
        JSONObject object;
        try{
            object = new JSONObject(response);

            Integer upvotes = object.getInt("upvotes");
            Integer downvotes = object.getInt("downvotes");
            Integer has_voted = object.getInt("has_voted");

            TextView complaint_upvotes = (TextView)findViewById(R.id.particular_institute_complaint_upvotes_answer);
            TextView complaint_downvotes = (TextView)findViewById(R.id.particular_institute_complaint_downvotes_answer);

            complaint_upvotes.setText(upvotes.toString());
            complaint_downvotes.setText(downvotes.toString());


            if(has_voted ==0) {
                button_downvote.setVisibility(View.VISIBLE);
                button_upvote.setVisibility(View.VISIBLE);

                button_downvote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vote_now("-1");

                    }
                });

                button_upvote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vote_now("1");
                    }
                });
            }

/*
            if(has_voted==1)
            {
                button_downvote.setVisibility(View.GONE);
                button_upvote.setVisibility(View.GONE);
            }
*/

            if(has_voted == 1)
            {

                // button_upvote.setClickable(false);
                button_upvote.setVisibility(View.VISIBLE);
                button_upvote.setText("Upvoted");
                button_upvote.setClickable(false);
                Toast.makeText(ParticularInstituteComplaint.this, "You have already UPVOTED this Complaint", Toast.LENGTH_SHORT).show();
            }
            if(has_voted == -1)
            {
                //button_downvote.setClickable(false);
                button_downvote.setVisibility(View.VISIBLE);
                button_downvote.setText("Downvoted");
                button_downvote.setClickable(false);
                Toast.makeText(ParticularInstituteComplaint.this, "You have already DOWNVOTED this Complaint", Toast.LENGTH_SHORT).show();
            }




            //create_complaint_table();
        }catch (JSONException e){
            e.printStackTrace();}

    }



    public void vote_now(String result){
        final String res = result;
        String adder1 = IPAddress.getName();

        String url = "http://" + adder1 + "/complaint_system/tools/add_vote.php";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  Toast.makeText(ParticularInstituteComplaint.this, response.toString(), Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(ParticularInstituteComplaint.this, ParticularInstituteComplaint.class);
                        startActivity(in);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ParticularInstituteComplaint.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            //used to store data and sent the string request
            @Override
            protected LinkedHashMap<String, String> getParams() {
                LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
                data.put("complaint_id", complaint_id1);
                data.put("complaint_type", "3");
                data.put("user_id",Profile_data.getuserid());
                data.put("vote", res);


                return data;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(request);



    }


//////////////////////////////////////////////////////////

    public void create_complaint_table()
    {

        TableLayout course_assig_table = (TableLayout) findViewById(R.id.particular_institute_complaint_comment_table);
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

        particular_institute_complaint_image = (ImageView) findViewById(R.id.particular_institute_complaint_image);

        String address = InstituteComplaintDetails.getParticular_institute_complaint_image();
        String adder1 = IPAddress.getName();
         if (address.equals("no_image")) {
            particular_institute_complaint_image.setVisibility(View.GONE);
        } else {
            Picasso.with(this).load("http://" + adder1 + address).into(particular_institute_complaint_image);
        }
    }
}
