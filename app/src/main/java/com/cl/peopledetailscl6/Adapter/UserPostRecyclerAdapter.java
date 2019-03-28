package com.cl.peopledetailscl6.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cl.peopledetailscl6.Model.UserPost;
import com.cl.peopledetailscl6.R;

import java.util.List;

public class UserPostRecyclerAdapter extends RecyclerView.Adapter<UserPostRecyclerAdapter.MyViewHolder> {

    private List<UserPost> userPostList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvBody;

        public MyViewHolder(View view) {
            super(view);

            tvTitle = view.findViewById(R.id.tvTitle);
            tvBody = view.findViewById(R.id.tvBody);
        }
    }


    public UserPostRecyclerAdapter(List<UserPost> userPostList) {
        this.userPostList = userPostList;
    }

    @Override
    public UserPostRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_posts, parent, false);

        return new UserPostRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserPostRecyclerAdapter.MyViewHolder holder, final int position) {
        final UserPost mUserPost = userPostList.get(position);

        holder.tvTitle.setText(mUserPost.getTitle());
        holder.tvBody.setText(mUserPost.getBody());
    }

    @Override
    public int getItemCount() {
        return userPostList.size();
    }
}

