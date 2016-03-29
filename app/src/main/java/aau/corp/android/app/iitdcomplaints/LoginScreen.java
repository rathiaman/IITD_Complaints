package aau.corp.android.app.iitdcomplaints;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.LinkedHashMap;

public class LoginScreen extends AppCompatActivity {

    public EditText edit_text_Username;
    public Button login_button;
    public EditText password_text;
    public EditText ip_address_text;
    public CheckBox show_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        onButtonClickListener();

        ///////////////////////////////////
        // Declaring ids for the variables made
        ///////////////////////////////////
        show_password = (CheckBox) findViewById(R.id.show_password);
        password_text = (EditText) findViewById(R.id.edit_text_password);

        ///////////////////////////////////
        // Show password option
        // On clicking the checkbox, the password will be immediately converted to the entered text
        // On checked change listenen
        ///////////////////////////////////
        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    password_text.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    password_text.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

    }


    ///////////////////////////////////
    // This function is for back pressed button
    // If you press back, an alert dialogue box will appear and show Yes and No options
    // On Clicking Yes, you will exit the application
    // On Clicking No, nothing will happen and you will be back at Login Screen
    ///////////////////////////////////
    @Override
    public void onBackPressed() {

        AlertDialog.Builder submit_alert = new AlertDialog.Builder(LoginScreen.this);
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
            public void onClick(DialogInterface dialog, int which) {    // If no is pressed, you are taken back to the login screen
                dialog.cancel();
            }
        });

        AlertDialog alert = submit_alert.create();
        alert.setTitle("Alert !!!");
        alert.show();

    }

    ///////////////////////////////////
    // This is the Button Click Listener for the login button
    // On clicking the login button, a dialogue box is created which asks for confirmaton
    // If you press No, you are taken back to login screen with respeective details entered
    // If you press yes, server request in sent and if correct credentials are entered, ypu are correctly logged in to your  home screen
    ///////////////////////////////////
    public void onButtonClickListener() {
        login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calls the alert dialogue box
                AlertDialog.Builder submit_alert = new AlertDialog.Builder(LoginScreen.this);
                submit_alert.setMessage("").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // calls the function which send the request to the server
                        sendRequest();

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {    // If no is pressed, you are taken back to the login screen
                        dialog.cancel();
                    }
                });

                AlertDialog alert = submit_alert.create();
                alert.setTitle("Confirm Your Details  !!!");
                alert.show();

            }
        });
    }
    ///////////////////////////////////
    //function defined for sending the string request
    ///////////////////////////////////
    private void sendRequest() {

        final ProgressDialog messageDialog = new ProgressDialog(this);
        messageDialog.setMessage("Logging in");
        messageDialog.show();

        ip_address_text = (EditText) findViewById(R.id.edit_text_IPAddress);
        IPAddress.setName(ip_address_text.getText().toString());
        String adder1 = IPAddress.getName();

        TextView password_text_for_login = (EditText) findViewById(R.id.edit_text_password);
        TextView user_name_for_login     = (EditText) findViewById(R.id.edit_text_username);

        final String password_text_for_login_string   = password_text_for_login.getText().toString().trim();
        final String user_name_for_login_string       = user_name_for_login.getText().toString().trim();

       /* final String password_text_for_login_string   = "aman1";
        final String user_name_for_login_string       = "ee1";
*/

        //String url="http://" + adder1 + "/default/login.json?userid=cs1110200&password=john";

        String url="http://" + adder1 + "/complaint_system/user_action/signin_action.php";
        //+ user_name_for_login_string +"&password="+ password_text_for_login_string

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("hello1", response.toString());
                      //  Toast.makeText(LoginScreen.this, response.toString(), Toast.LENGTH_SHORT).show();
                        PJson(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginScreen.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            //used to store data and sent the string request
            @Override
            protected LinkedHashMap<String, String> getParams() {
                LinkedHashMap<String, String> login_credentials = new LinkedHashMap<String, String>();
                login_credentials.put("user_id", user_name_for_login_string);
                login_credentials.put("password",password_text_for_login_string);
                return login_credentials;
            }


        };;

        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(request);

        //for handling cookies
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
    }

    private void PJson(String res) {
        int result = 0;
        try {
            JSONObject Object = new JSONObject(res);
            JSONObject outcome = Object.getJSONObject("outcome");
            Boolean success_value = outcome.getBoolean("status");
            if (!success_value)
                result = 0;

            else {
                result =1 ;
            }
            if (result == 1) {

                // String first_name_text = getIntent().getExtras().getString("Name");


                // Toast.makeText(MainActivity.this, myinteger, Toast.LENGTH_SHORT).show();
                JSONObject detail = Object.getJSONObject("detail");
                String user_id = detail.getString("user_id");
                String first_name = detail.getString("first_name");
                String last_name = detail.getString("last_name");
                String email = detail.getString("email");
                String user_type = detail.getString("user_type");
                String hostel = detail.getString("hostel");
                String worker_type = detail.getString("worker_type");

                Profile_data.setfirst_Name(first_name);
                Profile_data.setlast_Name(last_name);
                Profile_data.setuserid(user_id);
                Profile_data.set_email(email);
                Profile_data.setaccount_type(user_type);
                Profile_data.setHostel(hostel);
                Profile_data.setWorkertype(worker_type);


                Intent in = new Intent(getApplicationContext(), HomeScreen.class);
                startActivity(in);
            }
            else{
                Toast.makeText(LoginScreen.this, "invalid login. Please Check Yor Login Details", Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
