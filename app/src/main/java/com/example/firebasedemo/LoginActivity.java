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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText id,password;
    private Button login;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id=(EditText)findViewById(R.id.txt_id_ln);
        password=(EditText)findViewById(R.id.txt_password_ln);
        login=(Button)findViewById(R.id.btn_login);

        auth=FirebaseAuth.getInstance(); //creating an object [auth] of [FirebaseAuth] class.

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_id=id.getText().toString();
                String text_password=password.getText().toString();
                if(TextUtils.isEmpty(text_id)||TextUtils.isEmpty(text_password))
                {
                    Toast.makeText(LoginActivity.this,"invalid credientiala",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    loginUser(text_id, text_password);
                }
            }
        });
    }

    private void loginUser(String txt_id, String txt_password) {
        auth.signInWithEmailAndPassword(txt_id,txt_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult)
            {

                Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });
        auth.signInWithEmailAndPassword(txt_id,txt_password).addOnFailureListener(LoginActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Toast.makeText(LoginActivity.this,"register first ",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
