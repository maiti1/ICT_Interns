package com.example.admin.ict_interns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Personal_Details extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private EditText full_name,id_no,contact,name_of_school,district,mentor_details,contact_m;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal__details);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        full_name = (EditText)findViewById(R.id.full_name);
        id_no = (EditText)findViewById(R.id.id_no);
        contact = (EditText)findViewById(R.id.contact);
        name_of_school = (EditText)findViewById(R.id.name_of_school);
        district = (EditText)findViewById(R.id.district);
        mentor_details = (EditText)findViewById(R.id.mentor_details);
        contact_m = (EditText)findViewById(R.id.contact_m);

        save = (Button)findViewById(R.id.save);
        save.setOnClickListener(this);
    }

    private void saveUserInformation()
    {
        String name = full_name.getText().toString().trim();
        String idno = id_no.getText().toString().trim();
        String cellNumber = contact.getText().toString().trim();
        String nameOfSchool = name_of_school.getText().toString().trim();
        String districts = district.getText().toString().trim();
        String mentor = mentor_details.getText().toString().trim();
        String mentorContact = contact_m.getText().toString().trim();


        UserInformation userInformation = new UserInformation(name,idno,cellNumber,nameOfSchool,districts,mentor,mentorContact);

        FirebaseUser user = mAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(Personal_Details.this,"Saving Information...",Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.save)
        {
            saveUserInformation();
            Intent intent = new Intent(Personal_Details.this,AddProject.class);
            startActivity(intent);

        }
    }
}
