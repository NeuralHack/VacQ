package com.example.vacq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class PriorityList extends AppCompatActivity {

        FirebaseDatabase database;
        DatabaseReference booklistdat;
        RecyclerView bookrecyclerView;
        RecyclerView.LayoutManager layoutManager;
        String GenreID="";
        FirebaseRecyclerAdapter<user,PriorityListViewholder> bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority_list);

            database=FirebaseDatabase.getInstance();
            booklistdat=database.getReference("user");

            bookrecyclerView=(RecyclerView)findViewById(R.id.recyclerview);
            bookrecyclerView.setHasFixedSize(true);
            layoutManager=new LinearLayoutManager(this);
            bookrecyclerView.setLayoutManager(layoutManager);

            if(getIntent()!=null){
                GenreID=getIntent().getStringExtra("GenreID");
            }
            if(!GenreID.isEmpty() && GenreID!=null){
                loadListBook(GenreID);
            }
        }

        private void loadListBook(String genreID) {
            bookAdapter=new FirebaseRecyclerAdapter<user,PriorityListViewholder>(user.class,
                    R.layout.usercard,
                    PriorityListViewholder.class,
                    booklistdat.orderByChild("priority").equalTo(GenreID)
            ) {
                @Override
                protected void populateViewHolder(PriorityListViewholder booklist_viewholder, user book, int i) {
                    booklist_viewholder.uname.setText(book.getName());
                    booklist_viewholder.uage.setText(book.getAge());
                    booklist_viewholder.ugender.setText(book.getGender());
                    booklist_viewholder.ucity.setText(book.getCity());
                    booklist_viewholder.udisease.setText(book.getDiseases());
                    booklist_viewholder.uprofession.setText(book.getProfession());
                }
            };
            bookrecyclerView.setAdapter(bookAdapter);
        }

}


