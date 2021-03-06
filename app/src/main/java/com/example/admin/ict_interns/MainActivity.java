package com.example.admin.ict_interns;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private final int RC_SIGN_IN = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!= null)
        {
            //user already logged in
            Log.d("AUTH",mAuth.getCurrentUser().getEmail());
        }
        else
        {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false)
                    .setTheme(R.style.Background)
                    .setProviders(AuthUI.EMAIL_PROVIDER,
                            AuthUI.FACEBOOK_PROVIDER,
                            AuthUI.GOOGLE_PROVIDER)
                    .build(),RC_SIGN_IN);
        }

        findViewById(R.id.logout).setOnClickListener(this);
        findViewById(R.id.add_details).setOnClickListener(this);
        findViewById(R.id.add_project).setOnClickListener(this);
        findViewById(R.id.leave).setOnClickListener(this);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN)
        {
            if(resultCode == RESULT_FIRST_USER)
            {
                //user logged in
                Log.d("AUTH",mAuth.getCurrentUser().getEmail());
            }
            else
            {
                //user not authenticated
                Log.d("AUTH","NOT AUTHENTICATED");
            }
        }

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.logout)
        {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                           Log.d("AUTH","USER LOGGED OUT");

                        }
                    });
            finish();
        }

        if(view.getId() == R.id.add_details)
        {
            Intent add_details = new Intent(MainActivity.this,Personal_Details.class);
            startActivity(add_details);
        }

        if(view.getId() == R.id.add_project)
        {
            Intent add = new Intent(MainActivity.this,AddProject.class);
            startActivity(add);
        }

        if(view.getId() == R.id.leave)
        {
            Intent take_leave = new Intent(MainActivity.this,LeaveActivity.class);
            startActivity(take_leave);
        }
    }
}
