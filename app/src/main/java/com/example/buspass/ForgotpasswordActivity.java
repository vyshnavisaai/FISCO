package com.example.buspass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotpasswordActivity extends AppCompatActivity {

    private Button btnSend;
    private EditText txtEmail;
    private String email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        auth=FirebaseAuth.getInstance();
        txtEmail=findViewById(R.id.forgot_email);
        btnSend=findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

    }

    private void validateData() {
        email=txtEmail.getText().toString();
        if(email.isEmpty()){
            txtEmail.setError("Required");
        }else{
            forgetPass();
        }
    }

    private void forgetPass() {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotpasswordActivity.this,"Please Check your Email",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgotpasswordActivity.this,LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(ForgotpasswordActivity.this,"Error :-"+task.getException().getMessage(),Toast.LENGTH_SHORT);
                }
            }
        });
    }
}