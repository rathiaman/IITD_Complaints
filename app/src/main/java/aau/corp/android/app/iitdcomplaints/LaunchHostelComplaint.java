package aau.corp.android.app.iitdcomplaints;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LaunchHostelComplaint extends AppCompatActivity {

    Spinner spinner_type_list_hostel;
    ArrayAdapter<CharSequence> type_list_adapter_hostel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_hostel_complaint);


        spinner_type_list_hostel = (Spinner) findViewById(R.id.spinner_hostel_complaint_type_list);
        type_list_adapter_hostel = ArrayAdapter.createFromResource(this, R.array.search_list, android.R.layout.simple_spinner_item);
        type_list_adapter_hostel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type_list_hostel.setAdapter(type_list_adapter_hostel);

        spinner_type_list_hostel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
