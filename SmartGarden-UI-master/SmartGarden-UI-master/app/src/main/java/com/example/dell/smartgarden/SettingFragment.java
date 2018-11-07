package com.example.dell.smartgarden;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SettingFragment extends Fragment {

    Button btnSignOut;
    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog PD;

    private static final String TAG = "EmailPassword";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false); }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSignOut = (Button) getActivity().findViewById(R.id.sign_out_button);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {
               try {
                   auth.signOut();
                   Toast.makeText(getActivity(), "User Sign out!", Toast.LENGTH_LONG).show();
                   Intent intent = new Intent(getActivity(), LoginActivity.class);
                   startActivity(intent);
               } catch (Exception e) {
                   Log.e(TAG, "onClick: Exception " + e.getMessage(), e);
               }
            }
        });

    //protected void onCreate(Bundle savedInstanceState) {
        //  super.onCreateView(savedInstanceState);
     //  setContentView(R.layout.fragment_setting);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        PD = new ProgressDialog(getActivity());
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);

     //   btnSignOut = (Button) getActivity().findViewById(R.id.sign_out_button);

    //    btnSignOut.setOnClickListener(new View.OnClickListener() {
    //        @Override
     //       public void onClick(View view) {
     //           try {
      //              auth.signOut();
       //             Toast.makeText(getActivity(), "User Sign out!", Toast.LENGTH_LONG).show();
        //            Intent intent = new Intent(getActivity(), LoginActivity.class);
        //            startActivity(intent);
        //        } catch (Exception e) {
         //           Log.e(TAG, "onClick: Exception " + e.getMessage(), e);
         //       }

                //  auth.signOut();
                //  FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                //     @Override
                //     public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //        FirebaseUser user = firebaseAuth.getCurrentUser();
                //        if ( user == null) {
                //   startActivity(new Intent(MainActivity.this, LoginActivity.class));
                //         finish();
                //  finishAffinity();
                //     }
                //    }
                //  };
                //  auth.addAuthStateListener(authListener);
                //  Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                //  startActivity(intent);
         //   }
      //  });
        //getActivity().findViewById(R.id.change_password_button).setOnClickListener(new View.OnClickListener() {}
                getActivity().findViewById(R.id.change_password_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ForgetAndChangePasswordActivity.class).putExtra("Mode", 1));
            }
        });

        getActivity().findViewById(R.id.change_email_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ForgetAndChangePasswordActivity.class).putExtra("Mode", 2));
            }
        });

        getActivity().findViewById(R.id.delete_user_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ForgetAndChangePasswordActivity.class).putExtra("Mode", 3));
            }
        });
    }

    @Override
    public void onResume() {
        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
          //  finish();
        }
        super.onResume();
    }
}