package com.example.greenifylogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    public static final String TAG ="TAG";
    EditText mfullname,memail,mpassword,mphoneno;
    Button mregister;
    TextView mswitching;
    ProgressBar mpg;
    ProgressDialog progressDialog;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    FirebaseAuth mAuth;
    FirebaseUser mUser;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mfullname = findViewById(R.id.editTextTextPersonName3);
        memail = findViewById(R.id.editTextTextEmailAddress);
        mpassword = findViewById(R.id.editTextTextPassword);
        mphoneno = findViewById(R.id.editTextPhone);
        mregister = findViewById(R.id.button2);
        mswitching = findViewById(R.id.textView3);
        progressDialog = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();


        mswitching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });

//        mregister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                performAuth();
////                final String email = memail.getText().toString().trim();
//                String password = mpassword.getText().toString().trim();
//                final String fullname = mfullname.getText().toString();
//                final String phone = mphoneno.getText().toString();
//
//                if(TextUtils.isEmpty(email))
//                {
//                    memail.setError("Email is required");
//                }
//
//                if(TextUtils.isEmpty(password))
//                {
//                    mpassword.setError("password is required");
//                }
//
//                if(password.length()<6)
//                {
//                    mpassword.setError("Password must be > 6 characters");
//                }
//
//                if(fullname.isEmpty())
//                {
//                    mfullname.setError("Please enter your name");
//                }
//                if(phone.isEmpty())
//                {
//                    mphoneno.setError("Please enter your phone number");
//                }
//                if(phone.length()!=10)
//                {
//                    mphoneno.setError("Invalid phone number");
//                }
//                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
//                {
//                    memail.setError("Enter valid email id");
//                }
//
//                mpg.setVisibility(View.VISIBLE);

//
//            }
//
//        });
//    }
        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                performAuth();
            }
        });
    }
    private void performAuth()
    {
        String fullname = mfullname.getText().toString();
        String email = memail.getText().toString();
        String password = mpassword.getText().toString();
        String phno = mphoneno.getText().toString();

        if(!email.matches(emailPattern))
        {
            memail.setError("Enter correct email");
            memail.requestFocus();
        }else if(password.isEmpty() || password.length()<6)
        {
            mpassword.setError("Enter proper password");
            mpassword.requestFocus();
        }else if(phno.isEmpty())
        {
            mphoneno.setError("Please enter your phone number");
            mphoneno.requestFocus();
        } else if(fullname.isEmpty())
        {
            mfullname.setError("Enter your name");
            mfullname.requestFocus();
        }else
        {
            progressDialog.setMessage("Please Wait ....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();


            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        User user = new User(fullname,
                                email,
                                password,
                                phno);
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                Toast.makeText(Register.this, "Registeration Successful", Toast.LENGTH_SHORT).show();


                            }
                        });
//                        FirebaseUser mUser = mAuth.getCurrentUser();
                    }else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Register.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }

                private void sendUserToNextActivity() {
                    Intent intent = new  Intent(Register.this, homepage1.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });

        }
    }


}