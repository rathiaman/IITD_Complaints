package aau.corp.android.app.iitdcomplaints;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.net.Uri;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class LaunchPersonalComplaint extends AppCompatActivity {

    Spinner spinner_type_list, spinner_hostel_list;
    ArrayAdapter<CharSequence> type_list_adapter, hostel_list_adapter;
    Button submit, choose_image;
    private static EditText comment_title_edittext, description_edittext,room_no_edittext,contact_edittext;
    Integer complaint_typo;

    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    private ImageView upload_image_launch_personal_complaint;

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

            String selectedItem = parent.getItemAtPosition(position).toString();

            if (selectedItem.equals("Electricity")) {
                complaint_typo = 1;
            }else if (selectedItem.equals("Plumber")) {
                complaint_typo = 2;
            }else if (selectedItem.equals("Carpentry")) {
                complaint_typo = 3;
            }else if (selectedItem.equals("Internet Issues")) {
                complaint_typo = 4;
            }else if (selectedItem.equals("Sweeper")) {
                complaint_typo = 5;
            }else {
                complaint_typo = 6;
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
        });

        choose_image = (Button) findViewById(R.id.personal_complaint_image_button);
        choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });
        upload_image_launch_personal_complaint = (ImageView) findViewById(R.id.imageView_personal_complaint_launch);
        submit = (Button) findViewById(R.id.submit_button_personal_complaint_launch);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder submit_alert = new AlertDialog.Builder(LaunchPersonalComplaint.this);
                submit_alert.setMessage("").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // calls the function which send the request to the server
                        submitNewComplaint();

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

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                upload_image_launch_personal_complaint.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
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
        final String tag_int = complaint_typo.toString();
        String adder1 = IPAddress.getName();

        if(comment_title.length() == 0){
            Toast.makeText(LaunchPersonalComplaint.this, "Title Required", Toast.LENGTH_SHORT).show();
        }

        else if(description.length() == 0){
            Toast.makeText(LaunchPersonalComplaint.this, "Description Required", Toast.LENGTH_SHORT).show();
        }

        else if(room_no.length() == 0){
            Toast.makeText(LaunchPersonalComplaint.this, "Room No Required", Toast.LENGTH_SHORT).show();
        }

        else if(CheckInput.check_contact_info(contact) == false){
            Toast.makeText(LaunchPersonalComplaint.this, "Invalid 10-digit Contact Info", Toast.LENGTH_SHORT).show();
        }

        else {
            final ProgressDialog loading = new ProgressDialog(this);
            loading.setTitle("Submitting your Complaint");
            loading.setMessage("Please wait");
            loading.show();
//            ProgressDialog.show(LaunchPersonalComplaint.this,"Uploading...","Please wait...",false,false);
            String url;
            url = "http://" + adder1 + "/complaint_system/create_complaint/personal_complaint.php";

            StringRequest request = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("hello1", response.toString());
                          //  Toast.makeText(LaunchPersonalComplaint.this,response.toString(), Toast.LENGTH_SHORT).show();
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
                    login_credentials.put("first_name", Profile_data.getfirst_Name());
                    login_credentials.put("last_name", Profile_data.getlast_Name());
                    login_credentials.put("hostel", Integer.toString(HostelDetails.get_Particular_hostel_id(Profile_data.getHostel())));
                    login_credentials.put("title", comment_title);
                    login_credentials.put("description", description);
                    login_credentials.put("contact_info", contact);
                    login_credentials.put("tags", tag_int);
                    login_credentials.put("room_no", room_no);

                    if (bitmap != null) {
                        String image = getStringImage(bitmap);
                        login_credentials.put("image", image);
                    }
                    return login_credentials;
                }
            };
            ;

            // Add a request (in this example, called stringRequest) to your RequestQueue.
            MySingleton.getInstance(this).addToRequestQueue(request);

            Intent in = new Intent(LaunchPersonalComplaint.this, HomeScreen.class);
            startActivity(in);
            ///loading.hide();
        }
    }

}
