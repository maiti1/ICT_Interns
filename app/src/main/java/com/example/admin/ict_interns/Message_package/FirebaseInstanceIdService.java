package com.example.admin.ict_interns.Message_package;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by Admin on 2017/03/14.
 */

public class FirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh()
    {
//        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        Log.d(TAG,"Refreshed token: " + refreshedToken );
    }

    private void sendRegistrationToServer(String token)
    {
        //You can implement this method to store the token on your server
    }



}
