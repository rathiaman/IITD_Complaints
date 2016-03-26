package aau.corp.android.app.iitdcomplaints;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LaunchInstituteComplaint extends AppCompatActivity {

    Spinner spinner_type_list_institute;
    ArrayAdapter<CharSequence> type_list_adapter_institute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_institute_complaint);

        spinner_type_list_institute = (Spinner) findViewById(R.id.spinner_institute_complaint_type_list);
        type_list_adapter_institute = ArrayAdapter.createFromResource(this, R.array.search_list, android.R.layout.simple_spinner_item);
        type_list_adapter_institute.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type_list_institute.setAdapter(type_list_adapter_institute);

        spinner_type_list_institute.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " --", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
