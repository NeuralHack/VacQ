package com.example.vacq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAfterRegisteredAndLogin extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_after_registered_and_login);
        Button logout=findViewById(R.id.button);
        ProgressBar progressBar=findViewById(R.id.progressBar);
        textView=findViewById(R.id.textView4);
        progressBar.setVisibility(View.VISIBLE);
        FirebaseDatabase database = FirebaseDatabase.getInstance() ;
        final DatabaseReference table_user = database.getReference ("user");
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null) {
            String Age = extras.getString("Age");
            String Age = extras.getString("Age");
            String Age = extras.getString("Age");
            String Age = extras.getString("Age");
            String Age = extras.getString("Age");
            String Age = extras.getString("Age");
            String Age = extras.getString("Age");

        }
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://vacqapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api=retrofit.create(API.class);
        Call<List<Priority>> call=api.predict();
        call.enqueue(new Callback<List<Priority>>() {
                         @Override
                         public void onResponse(Call<List<Priority>> call, Response<List<Priority>> response) {

                         }

                         @Override
                         public void onFailure(Call<List<Priority>> call, Throwable t) {

                         }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserAfterRegisteredAndLogin.this,"Logout successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(userphone.getText().toString()).exists()){
                    Toast.makeText(UserRegister.this, "User Already Registered", Toast.LENGTH_SHORT).show();
                }
                else{
                    user user = new user(userage.getText().toString(),usercity,
                            userdisease,usergender,username.getText().toString(),userpass.getText().toString(),
                            userprofession,userzone);
                    table_user.child(userphone.getText().toString()).setValue(user);
                    Toast.makeText(UserRegister.this, "Register Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), UserAfterRegisteredAndLogin.class));
                    finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
});

        }
    }
}