package com.example.ltdd_18042022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText name_et, email_et, password_et;
    private Button register_btn;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name_et = findViewById(R.id.et_name);
        email_et = findViewById(R.id.et_email);
        password_et = findViewById(R.id.et_pass);

        register_btn = findViewById(R.id.btn_register);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void registerUser() {
        String name = name_et.getText().toString();
        String email = email_et.getText().toString();
        String password = password_et.getText().toString();

        if (name.isEmpty()) {
            name_et.setError("Name is required!");
            name_et.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            email_et.setError("Email is required!");
            email_et.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            password_et.setError("Password is required!");
            password_et.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_et.setError("Please provide valid email");
            email_et.requestFocus();
            return;
        }

        System.out.println(email + "   " + name + "  " + password);

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            User user = new User(name, email);
//                            System.out.println(user);
//                            FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()){
//                                        Toast.makeText(RegisterActivity.this, "Dang ki thanh cong", Toast.LENGTH_SHORT).show();
//                                    }else {
//                                        Toast.makeText(RegisterActivity.this, "Dang ki that bai ", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
                            Toast.makeText(RegisterActivity.this, "Dang ki thanh cong", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Dang ki that bai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}