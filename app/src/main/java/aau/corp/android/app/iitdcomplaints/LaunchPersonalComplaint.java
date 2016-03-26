package aau.corp.android.app.iitdcomplaints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class LaunchPersonalComplaint extends AppCompatActivity {

    Spinner spinner_type_list, spinner_hostel_list;
    ArrayAdapter<CharSequence> type_list_adapter, hostel_list_adapter;
    Button submit;
    private static EditText comment_title_edittext, description_edittext,room_no_edittext,contact_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_personal_complaint);


        spinner_type_list = (Spinner) findViewById(R.id.spinner_complaint_type_list);
        type_list_adapter = ArrayAdapter.createFromResource(this, R.array.search_list, android.R.layout.simple_spinner_item);
        type_list_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type_list.setAdapter(type_list_adapter);

        spinner_type_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " --", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_hostel_list = (Spinner) findViewById(R.id.spinner_hostel_list);
        hostel_list_adapter = ArrayAdapter.createFromResource(this, R.array.hostel_list, android.R.layout.simple_spinner_item);
        hostel_list_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_hostel_list.setAdapter(hostel_list_adapter);

        spinner_hostel_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " --", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        submit = (Button) findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  submitNewComplaint();

            }
        });

    }



    public void submitNewComplaint(){

        comment_title_edittext = (EditText)findViewById(R.id.editText_complaint_title);
        description_edittext = (EditText)findViewById(R.id.editText_complaint_description);
        contact_edittext = (EditText)findViewById(R.id.editText_complaint_contact_info);
        room_no_edittext = (EditText)findViewById(R.id.editText_complaint_room_no);

        final String comment_title = comment_title_edittext.getText().toString();
        final String description = description_edittext.getText().toString();
        final String room_no = room_no_edittext.getText().toString();
        final String contact = contact_edittext.getText().toString();
        final String tag_int = "2";
        String adder1 = IPAddress.getName();


        String url;
        url = "http://" + adder1 + "/complaint_system/create_complaint/personal_complaint.php";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("hello1", response.toString());
                        Toast.makeText(LaunchPersonalComplaint.this, response.toString(), Toast.LENGTH_SHORT).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LaunchPersonalComplaint.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            //used to store data and sent the string request
            @Override
            protected LinkedHashMap<String, String> getParams() {
                LinkedHashMap<String, String> login_credentials = new LinkedHashMap<String, String>();
                login_credentials.put("user_id", Profile_data.getuserid());
                login_credentials.put("first_name",Profile_data.getfirst_Name());
                login_credentials.put("last_name",Profile_data.getlast_Name());
                login_credentials.put("hostel",Profile_data.getHostel());
                login_credentials.put("title",comment_title);
                login_credentials.put("description",description);
                login_credentials.put("contact_info",contact);
                login_credentials.put("tags",tag_int);
                login_credentials.put("room_no",room_no);

                return login_credentials;
            }


        };;

        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(request);

    }

}
