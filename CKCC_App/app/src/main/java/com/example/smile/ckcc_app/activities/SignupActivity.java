package com.example.smile.ckcc_app.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.smile.ckcc_app.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPassword, edtComfirmPass;
    private Button btnSignup;

    private FirebaseAuth stuAuth;
    private DatabaseReference stuDatabase;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        stuDatabase  = FirebaseDatabase.getInstance().getReference();
        stuAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        edtEmail = (EditText)findViewById(R.id.user_email);
        edtPassword = (EditText)findViewById(R.id.user_password);
        edtName = (EditText)findViewById(R.id.user_name);
        edtComfirmPass = (EditText)findViewById(R.id.edt_comfirm_password);

        btnSignup = (Button)findViewById(R.id.btn_signup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signup();

            }
        });

    }

    private void signup() {

        final String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        final String comfirmPassword = edtComfirmPass.getText().toString().trim();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(comfirmPassword)){

            if(password.contentEquals(comfirmPassword)){

                progressDialog.setMessage("Singing Up....");
                progressDialog.show();

                stuAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful() ){

//                        String stu_id = stuAuth.getCurrentUser().getUid();
//
//                        //create new child of user id
//                        DatabaseReference current_stu_db = stuDatabase.child(stu_id);
//                        current_stu_db.child("name").setValue(name);
//                        current_stu_db.child("image").setValue("default");

                            FirebaseUser user = stuAuth.getCurrentUser();

                            String uid = user.getUid();

                            stuDatabase.child("Students").child(uid).child("username").setValue(name);

                            Intent signupIntent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(signupIntent);

                            Toast.makeText(getApplicationContext(), "Created user", Toast.LENGTH_SHORT).show();

                            progressDialog.dismiss();

                        } else {
                            Toast.makeText(SignupActivity.this, "Error while creating account", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }else {
                Toast.makeText(SignupActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(SignupActivity.this, "Fields cannot empty", Toast.LENGTH_SHORT).show();

        }
    }

}
