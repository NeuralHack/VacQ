package com.example.vacq;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PriorityListViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView uname, uage, ugender,uprofession,udisease,ucity;

    private ItemClickListener itemClickListener;
    public PriorityListViewholder(@NonNull View itemView) {
        super(itemView);
        uname=(TextView)itemView.findViewById(R.id.username);
        uage=(TextView)itemView.findViewById(R.id.userage);
        ugender=(TextView)itemView.findViewById(R.id.usergender);
        uprofession=(TextView)itemView.findViewById(R.id.userprofession);
        ucity=(TextView)itemView.findViewById(R.id.usercity);
        udisease=(TextView)itemView.findViewById(R.id.userdisease);

        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    @Override
    public void onClick(View view) {

    }
}


