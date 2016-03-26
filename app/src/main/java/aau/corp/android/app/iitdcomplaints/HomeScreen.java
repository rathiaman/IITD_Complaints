package aau.corp.android.app.iitdcomplaints;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Field;

public class HomeScreen extends AppCompatActivity {


    public Button personal_complaints, hostel_complaints, institute_complaints, complaints_others;
    public EditText ip_address_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        onButtonClickListener();
        setbuttontext();
        ///////////////////////////////////
        // Function for the 3 dot button in the top right position
        ///////////////////////////////////
        getOverflowMenu();
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            // Ignore
        }

        complaints_others = (Button) findViewById(R.id.other_complaints);
        personal_complaints = (Button) findViewById(R.id.personal_complaints);
        hostel_complaints = (Button) findViewById(R.id.hostel_complaints);
        institute_complaints = (Button) findViewById(R.id.institute_complaints);

        String account_type_data = Profile_data.getAccount_type();

        if(account_type_data == "Student"){complaints_others.setVisibility(View.GONE);}
        else {personal_complaints.setVisibility(View.GONE); hostel_complaints.setVisibility(View.GONE); institute_complaints.setVisibility(View.GONE);}
    }

    public void setbuttontext(){

        complaints_others = (Button) findViewById(R.id.other_complaints);
        if (Profile_data.getAccount_type() == "Worker"){complaints_others.setText(Profile_data.getWorkertype() + " Complaints");}
        else if (Profile_data.getAccount_type() == "Institute Person"){complaints_others.setText("Institute Complaints");}
        else {complaints_others.setText(Profile_data.getHostel() + " Hostel Complaints");}

    }

    public void onButtonClickListener(){
        personal_complaints = (Button) findViewById(R.id.personal_complaints);
        personal_complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), PersonalComplaints.class);
                startActivity(in);

            }
        });
        hostel_complaints = (Button) findViewById(R.id.hostel_complaints);
        hostel_complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), HostelComplaints.class);
                startActivity(in);
            }
        });
        institute_complaints = (Button) findViewById(R.id.institute_complaints);
        institute_complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), InstituteComplaints.class);
                startActivity(in);

            }
        });

    }

    ///////////////////////////////////
    // This function is for back pressed button
    // If you are logged in and you press back, an alert dialogue box will appear and show Yes and No options
    // On Clicking Yes, you will exit the application
    // On Clicking No, nothing will happen and you will be back at Home Screen
    ///////////////////////////////////
    @Override
    public void onBackPressed(){

        // calls the alert dialogue box
        AlertDialog.Builder submit_alert = new AlertDialog.Builder(HomeScreen.this);
        submit_alert.setMessage("Are you sure you want to exit !!!").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // calls the function which send the request
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                System.exit(0);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {    // If no is pressed, you are taken back to the Home screen
                dialog.cancel();
            }
        });

        AlertDialog alert = submit_alert.create();
        alert.setTitle("Alert !!!");
        alert.show();

    }

    ///////////////////////////////////
    // This function is for the three dots button where a small menu open up which displays several items like your name, entry number, profile, signout optons
    ///////////////////////////////////
    private void getOverflowMenu() {

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ///////////////////////////////////
    // This functions inflate the 3 dot menu button with items present in the menu file
    ///////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu1, menu);

        String text = Name_Class.getName();

        MenuItem name_wala_item = menu.findItem(R.id.name_first_menu_display);
        name_wala_item.setTitle("Hi " + Profile_data.getfirst_Name());
        // menu.getItem(R.id.name_first_menu_display).setTitle(text);

        return true;
    }

    ///////////////////////////////////
    // This function handles the selection of options from the 3-dot menu option
    ///////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        // Function if You click on The Profie Item
        ///////////////////////////////////
        if (id == R.id.profile) {

            Intent in = new Intent(HomeScreen.this, ProfileScreen.class);
            startActivity(in);

           }
       /* if (id == R.id.sign_out) {
            signout_method();
        }
       */ return false;
    }


    //////////////////////////////////////////////////////////
/*
    private void sendRequest(int target) {

        String adder1 = IPAddress.getName();

        final String user_id = Profile_data.getuserid();
        final String hostel = Profile_data.getHostel();

        String url;

        if(target==1) {
            url = "http://" + adder1 + "/complaint_system/show_complaints/show_personal_complaints.php";
            Intent in = new Intent(getApplicationContext(), PersonalComplaints.class);
            startActivity(in);


        }
        else if(target==2){
            url = "http://" + adder1 + "/complaint_system/show_complaints/show_hostel_complaints.php";
            Intent in = new Intent(getApplicationContext(), HostelComplaints.class);
            startActivity(in);



        }
        else{
            url = "http://" + adder1 + "/complaint_system/show_complaints/show_institute_complaints.php";
            Intent in = new Intent(getApplicationContext(), InstituteComplaints.class);
            startActivity(in);
        }
        //+ user_name_for_login_string +"&password="+ password_text_for_login_string

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("hello1", response.toString());
                        Toast.makeText(HomeScreen.this, response.toString(), Toast.LENGTH_SHORT).show();
                        //PJson(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomeScreen.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            //used to store data and sent the string request
            @Override
            protected LinkedHashMap<String, String> getParams() {
                LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
                data.put("user_id", user_id);
                data.put("hostel", hostel);
                return data;
            }


        };;

        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(request);


    }

*/

}
