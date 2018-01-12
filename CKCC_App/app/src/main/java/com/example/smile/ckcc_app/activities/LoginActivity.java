package com.example.smile.ckcc_app.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smile.ckcc_app.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button btnLogin;
    private TextView txtSignup;

    private DatabaseReference mDatabase;
    private FirebaseAuth stuAuth;
    //private FirebaseAuth.AuthStateListener stuAuthListener;

//    private String email, password;
    private EditText edtEmail, edtPassword;
    private ProgressDialog progressDialog;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText)findViewById(R.id.user_email);
        edtPassword = (EditText)findViewById(R.id.user_password);
        progressDialog = new ProgressDialog(this);

        stuAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnLogin = (Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        txtSignup = (TextView)findViewById(R.id.txt_link_signup);
        txtSignup.setOnClickListener(this);

        //check if user already login
//        stuAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//
//                if(firebaseAuth.getCurrentUser() != null){
//
//                    startActivity(new Intent(LoginActivity.this, NavigationDrawer.class));
//
//                }
//            }
//        };


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_login:
                signIn();
                break;

            case R.id.txt_link_signup:
                Intent intentSignup = new Intent(this, SignupActivity.class);
                startActivity(intentSignup);
                break;

            default:
                break;
        }

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        stuAuth.addAuthStateListener(stuAuthListener);
//    }

    private void signIn() {

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "fields cannot empty...", Toast.LENGTH_SHORT).show();
        } else {

            progressDialog.setMessage("Loggin in....");
            progressDialog.show();

            stuAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Email or password is incorrect", Toast.LENGTH_SHORT).show();
                    }else {
                        startActivity(new Intent(LoginActivity.this, NavigationDrawer.class));

                        progressDialog.dismiss();
                    }
                }
            });
        }

    }

}
