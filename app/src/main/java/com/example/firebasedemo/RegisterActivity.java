package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
   private EditText id,password;
   private Button register;
   private FirebaseAuth auth; //declearing of object [auth] of [FirebaseAuth] class


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        id=(EditText)findViewById(R.id.txt_id_reg);
        password=(EditText)findViewById(R.id.txt_password_reg);
        register=(Button)findViewById(R.id.btn_register);

        auth=FirebaseAuth.getInstance(); //creating an object [auth] of [FirebaseAuth] class.

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID=id.getText().toString();
                String PASSWORD=password.getText().toString();

                if(TextUtils.isEmpty(ID) || TextUtils.isEmpty(PASSWORD))
                {
                    Toast.makeText(RegisterActivity.this,"invalid Credentials",Toast.LENGTH_SHORT).show();
                }
                else if (PASSWORD.length()<6)
                {
                    Toast.makeText(RegisterActivity.this,"password too short",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    RegisterUser(ID,PASSWORD);
                }

            }
        });
    }

    private void RegisterUser(String id, String password)
    {// calling a method [createUserWithEmailAndPassword] of FirebaseAuth class. for registering the user with 'id' and 'password'.
        auth.createUserWithEmailAndPassword(id,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
