package com.example.vacq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserLogin extends AppCompatActivity {
    EditText entpass,entphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        entpass=findViewById (R.id.entpass) ;
        entphone =findViewById (R.id.entphone);
        Button btnlogin =findViewById(R.id.button2);
        FirebaseDatabase database = FirebaseDatabase.getInstance() ;
        final DatabaseReference table_user = database.getReference ("user");
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(entphone.getText().toString()).exists()) {
                            user user = dataSnapshot.child(entphone.getText().toString()).getValue(user.class);
                            if (user.getPass().equals(entpass.getText().toString())){
                                Toast.makeText(UserLogin.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                                newscreen();}
                            else
                                Toast.makeText(UserLogin.this, "Log In Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(UserLogin.this, "User Does Not Exist", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                    public void newscreen(){
                        Intent loginsuccess=new Intent(UserLogin.this,UserAfterRegisteredAndLogin.class);
                        startActivity(loginsuccess);
                    }
                });
            }
        });


    }
}