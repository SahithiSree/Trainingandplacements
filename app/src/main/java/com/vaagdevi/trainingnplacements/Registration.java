package com.vaagdevi.trainingnplacements;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    EditText mname,memail,mpassword,mphone;
    Button registerbtn;
    TextView loginbtn;
    FirebaseAuth fAuth;
    //if(fAuth.getUser()!= null){
      //startActivity(new Intent(getApplicationContext(),MainActivity.class));
        //finish();
   // }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mname = findViewById(R.id.editTextName);
        memail = findViewById(R.id.editTextEmail);
       mpassword= findViewById(R.id.editTextPassword);
        mphone = findViewById(R.id.editTextMobile);
        registerbtn = findViewById(R.id.cirRegisterButton);
        loginbtn= findViewById(R.id.cirLoginButton);

        fAuth=FirebaseAuth.getInstance();

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=memail.getText().toString().trim();
                String password=mpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is Required.");
                    return;

                }
                if(TextUtils.isEmpty(password)){
                    mpassword.setError("password is required.");
                    return;
                }
                if(password.length() <6){
                    mpassword.setError("password must be >= 6 Characters");
                    return;

                }
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Registration.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Home.class));
                        }else{
                            Toast.makeText(Registration.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

    }


    public void onLoginClick(View view){
        startActivity(new Intent(this,MainActivity.class));
        //overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

}
