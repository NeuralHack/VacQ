package com.example.vacq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        Button p1=findViewById(R.id.p1);
        Button p2=findViewById(R.id.p2);
        Button p3=findViewById(R.id.p3);
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent booklist=new Intent(AdminView.this,PriorityList.class);
                booklist.putExtra("GenreID","1");
                startActivity(booklist);
            }
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent booklist=new Intent(AdminView.this,PriorityList.class);
                booklist.putExtra("GenreID","2");
                startActivity(booklist);
            }
        });
        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent booklist=new Intent(AdminView.this,PriorityList.class);
                booklist.putExtra("GenreID","3");
                startActivity(booklist);
            }
        });


    }
}