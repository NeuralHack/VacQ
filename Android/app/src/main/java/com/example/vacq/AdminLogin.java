package com.example.vacq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {
    EditText entpass,entemail,entid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        entid=findViewById(R.id.adminid);
        entpass=findViewById (R.id.adminPass) ;
        entemail =findViewById (R.id.adminEmail);
        Button btnlogin =findViewById(R.id.adminLogin);
        FirebaseDatabase database = FirebaseDatabase.getInstance() ;
        final DatabaseReference table_admin = database.getReference ("admin");
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table_admin.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(entid.getText().toString()).getValue(admin.class)!=null) {
                            admin ad = dataSnapshot.child(entid.getText().toString()).getValue(admin.class);
                            if (ad.getEmail().equals(entemail.getText().toString()) && ad.getPass().equals(entpass.getText().toString())){
                                Toast.makeText(AdminLogin.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), AdminView.class));

                                }
                            else
                                Toast.makeText(AdminLogin.this, "Log In Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(AdminLogin.this, "User Does Not Exist", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}