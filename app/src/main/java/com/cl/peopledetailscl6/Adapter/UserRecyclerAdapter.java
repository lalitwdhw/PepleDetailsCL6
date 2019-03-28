package com.cl.peopledetailscl6.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cl.peopledetailscl6.DataInterfaceUserPosition;
import com.cl.peopledetailscl6.Model.User;
import com.cl.peopledetailscl6.R;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.MyViewHolder> {

    private final DataInterfaceUserPosition dt;
    private List<User> userList;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tvName;
    CardView cardUser;

    public MyViewHolder(View view) {
        super(view);

        tvName = view.findViewById(R.id.tvName);
        cardUser = view.findViewById(R.id.cardUser);
    }
}


    public UserRecyclerAdapter(List<User> userList, DataInterfaceUserPosition dt) {
        this.userList = userList;
        this.dt = dt;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final User mUser = userList.get(position);
        holder.tvName.setText(mUser.getName());
        holder.cardUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dt.userSelected(mUser);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
