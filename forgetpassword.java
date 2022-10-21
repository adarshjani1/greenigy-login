package com.example.greenifylogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {

    EditText resetemail;
    Button btn;
    TextView ba;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        resetemail = findViewById(R.id.editTextTextPersonName);
        btn = findViewById(R.id.button);
        ba = findViewById(R.id.textView9);
        progressDialog = new ProgressDialog(this);

        auth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }

            private void resetPassword() {
                String remail = resetemail.getText().toString().trim();
                if(remail.isEmpty())
                {
                    resetemail.setError("Email is required");
                    resetemail.requestFocus();
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(remail).matches())
                {
                    resetemail.setError("Please enter a valid email");
                    resetemail.requestFocus();
                }

                auth.sendPasswordResetEmail(remail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(forgetpassword.this, "Check your email to reset your password", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), login.class));
                        }else
                        {
                            Toast.makeText(forgetpassword.this, "Try again! Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });


    }
}