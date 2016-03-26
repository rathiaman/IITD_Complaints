package aau.corp.android.app.iitdcomplaints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Field;

public class InstituteComplaints extends AppCompatActivity {

    Spinner search_list, filter_list;
    ArrayAdapter<CharSequence> search_list_adapter, filter_list_adapter;
    Button launch_institute_complaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_complaints);
        onButtonClickListener_Launch_Institute();

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
    }

    public void onButtonClickListener_Launch_Institute(){
        launch_institute_complaint = (Button) findViewById(R.id.launch_complaint_institute);
        launch_institute_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(InstituteComplaints.this, LaunchInstituteComplaint.class);
                startActivity(in);

            }
        });
    }

}
