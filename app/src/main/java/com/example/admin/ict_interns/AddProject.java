package com.example.admin.ict_interns;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.twitter.sdk.android.core.models.User;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class AddProject extends AppCompatActivity implements View.OnClickListener{

    private EditText project_description;
    private TextView start_time;
    private TextView end_time;
    private TextView date;
    private EditText totalHours;
    private Button submit;
    private TextView mentor_details;
    private Button return_home;

    private FirebaseAuth mAuth;

    TimePicker timePicker;
    TimePicker timePicker1;
    DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        totalHours = (EditText) findViewById(R.id.total_hours_worked);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Getting the data from snapshot
                    UserInformation user = postSnapshot.getValue(UserInformation.class);

                    //Adding it to a string
                    String mentor = user.getMentordetails().toString();
                    String mContact = user.getmContact().toString();
                    String school = user.getNameofschool().toString();
                    String district = user.getDistrict().toString();

                    mentor_details = (TextView)findViewById(R.id.mentor);
                    //Displaying it on textview
                    mentor_details.setText(mentor + " " + mContact + " " + school + " " + district);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {

                System.out.println("The read failed: " + error.getMessage());
            }
        });

        start_time = (TextView) findViewById(R.id.time);
        end_time = (TextView) findViewById(R.id.time_out);
        date = (TextView) findViewById(R.id.date);
        submit = (Button) findViewById(R.id.submit);
        project_description = (EditText) findViewById(R.id.project_description);
        return_home = (Button)findViewById(R.id.return_back);

        return_home.setOnClickListener(this);

        timePicker = (TimePicker) findViewById(R.id.simpleTimePicker);
        timePicker1 = (TimePicker) findViewById(R.id.simpleTimePicker1);
        datePicker = (DatePicker) findViewById(R.id.dp_date);


        submit.setOnClickListener(this);
        timePicker.setIs24HourView(false);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                start_time.setText(  hourOfDay + " : " + minute);
            }
        });
        timePicker1.setIs24HourView(false);
        timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                end_time.setText( hourOfDay + " : " + minute);
            }
        });
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        date.setText( day + " / " + month + " / " + year);







    }

    private String addProject(String mentor,String ProjectDescription, String time_in,String time_out, String date,String totalHours)
    {
        String addProjects = "Project Description :" + ProjectDescription;
        addProjects = addProjects + "\nTime in :" + time_in;
        addProjects = addProjects + "\nTime out :" + time_out;
        addProjects = addProjects + "\nDate :" + date;
        addProjects = addProjects + "\nTotal hours :" + totalHours;
        addProjects = addProjects + "\nMentor details :" + mentor ;


        return addProjects;
    }


    @Override
    public void onClick(View v) {

        UserInformation user = new UserInformation();
        String ProjectDescription = project_description.getText().toString();
        String time_in = start_time.getText().toString();
        String time_out = end_time.getText().toString();
        String Date = date.getText().toString() ;
        String TotalHours = totalHours.getText().toString();
        String mentor_details1 = mentor_details.getText().toString();

        String message = addProject(mentor_details1,ProjectDescription,time_in,time_out,Date,TotalHours);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "SUBMISSION OF TIMESHEET ");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        if(v.getId() == R.id.return_back)
        {
            Intent intent1 = new Intent(AddProject.this,MainActivity.class);
            startActivity(intent1);
            finish();
        }


    }
}






