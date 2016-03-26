package aau.corp.android.app.iitdcomplaints;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

///////////////////////////////////
// This class is for the user profile screen
// This class will open when user taps on the profile option at the 3-dot menu item in Home screen
// Contains data of the user like first name, last name, entry number, email address, account type
///////////////////////////////////

public class ProfileScreen extends AppCompatActivity {

    TextView first_name, last_name, user_id, email_address, account_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        first_name = (TextView) findViewById(R.id.first_name_answer);
        last_name = (TextView) findViewById(R.id.last_name_answer);
        user_id = (TextView) findViewById(R.id.user_id_answer);
        email_address = (TextView) findViewById(R.id.email_address_answer);
        account_type = (TextView) findViewById(R.id.email_address_answer);

        first_name.setText(Profile_data.getfirst_Name());
        last_name.setText(Profile_data.getlast_Name());
        user_id.setText(Profile_data.getuserid());
        email_address.setText(Profile_data.get_email());
        account_type.setText(Profile_data.getAccount_type());

    }


}