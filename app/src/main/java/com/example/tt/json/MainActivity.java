package com.example.tt.json;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends ActionBarActivity {
    CallbackManager callbackManager;
    TextView textView;
    LoginButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();

        FacebookSdk.sdkInitialize(getApplicationContext());


        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

         loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions("user_friends");
        //loginButton.setFragment(this);
       // loginButton.registerCallback(callbackManager, mcallback);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
               textView.setText("user ID :"+ loginResult.getAccessToken().getUserId()+"\n"+
                                    "Auth Token:" + loginResult.getAccessToken().getToken()+"\n");
            }

            @Override
            public void onCancel() {
                textView.setText("Login attemp canceled");
            }

            @Override
            public void onError(FacebookException exception) {
                textView.setText("Login attemp failed");
                // App code
            }
        });

    }

    public void mes(String mess) {
        textView.setText(mess);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
}
