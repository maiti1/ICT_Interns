package com.example.admin.ict_interns;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Locale;

public class LeaveActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText emp_name;
    private EditText designation;
    private EditText school;
    private EditText start_date;
    private EditText end_date;
    private EditText leave_purpose;
    private EditText leave_address;
    private Button leave_submit;
    private Button return_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        emp_name = (EditText)findViewById(R.id.employee_name);
        designation = (EditText)findViewById(R.id.designation);
        school = (EditText)findViewById(R.id.name_of_school);
        start_date = (EditText)findViewById(R.id.start_date);
        end_date = (EditText)findViewById(R.id.end_date);
        leave_purpose = (EditText)findViewById(R.id.purpose_of_leave);
        leave_address = (EditText)findViewById(R.id.address);
        leave_submit = (Button)findViewById(R.id.submit_leave);
        return_home = (Button)findViewById(R.id.return_back);


        leave_submit.setOnClickListener(this);
        return_home.setOnClickListener(this);

    }

    private String leaveApplication(String emp_nm,String position, String school_name,String date_s, String date_e,String leave_p,String leave_add)
    {
        String leave_appplication = "Employee name :" + emp_nm;
        leave_appplication = leave_appplication + "\nDesignation :" + position;
        leave_appplication = leave_appplication + "\nSchool name :" + school_name;
        leave_appplication = leave_appplication + "\nStart Date :" + date_s;
        leave_appplication = leave_appplication + "\nEnd Date :" + date_e;
        leave_appplication = leave_appplication + "\nPurpose For Leave :" + leave_p;
        leave_appplication = leave_appplication + "\nAddress of Leave :" + leave_add;


        return leave_appplication;
    }

    @Override
    public void onClick(View v) {

        String employee = emp_name.getText().toString();
        String desgn = designation.getText().toString();
        String schools = school.getText().toString();
        String date1 = start_date.getText().toString();
        String date2 = end_date.getText().toString();
        String leave = leave_purpose.getText().toString();
        String address = leave_address.getText().toString();


        String message = leaveApplication(employee,desgn,schools,date1,date2,leave,address);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "APPLICATION FOR LEAVE ");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        if(v.getId() == R.id.return_back)
        {
            Intent intent1 = new Intent(LeaveActivity.this,MainActivity.class);
            startActivity(intent1);
        }



    }

}

