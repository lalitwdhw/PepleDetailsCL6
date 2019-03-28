package com.cl.peopledetailscl6.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.cl.peopledetailscl6.Activity.MainActivity;
import com.cl.peopledetailscl6.Adapter.UserPostRecyclerAdapter;
import com.cl.peopledetailscl6.Adapter.UserRecyclerAdapter;
import com.cl.peopledetailscl6.Constants;
import com.cl.peopledetailscl6.DataInterfaceUserPosition;
import com.cl.peopledetailscl6.Model.User;
import com.cl.peopledetailscl6.Model.UserPost;
import com.cl.peopledetailscl6.R;
import com.cl.peopledetailscl6.Retrofit.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsFragment extends Fragment {

    private ScrollView llDetails;
    private TextView tvSelect;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvAddress;
    private TextView tvPhone;
    private TextView tvCompany;
    private TextView tvWebsite;
    private Button buttonShowPosts;
    private RelativeLayout rlPosts;
    private ProgressBar progress;
    private RecyclerView recyclerView;
    private UserPostRecyclerAdapter mAdapter;

    boolean isPostsShowing = false;
    private User presentUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_details, container, false);

        overideBack(view);

        init(view);

        if(getArguments() != null)
        {
            User mBundle = getArguments().getParcelable(Constants.USER);
            populateDetails(mBundle);
        }

        return view;
    }

    private void init(View view)
    {
        llDetails = view.findViewById(R.id.llDetails);
        tvSelect = view.findViewById(R.id.tvSelect);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvAddress = view.findViewById(R.id.tvAddress);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvCompany = view.findViewById(R.id.tvCompany);
        tvWebsite = view.findViewById(R.id.tvWebsite);
        buttonShowPosts = view.findViewById(R.id.buttonShowPosts);
        rlPosts = view.findViewById(R.id.rlPosts);
        progress = view.findViewById(R.id.progress);
        recyclerView = view.findViewById(R.id.recyclerPosts);
    }

    private void populateDetails(final User mUser)
    {
        llDetails.setVisibility(View.VISIBLE);
        tvSelect.setVisibility(View.GONE);
        rlPosts.setVisibility(View.GONE);

        presentUser = mUser;

        tvName.setText(String.format(getResources().getString(R.string.user_identification),mUser.getName(),mUser.getUsername()));
        tvEmail.setText(mUser.getEmail());
        tvAddress.setText(String.format(getResources().getString(R.string.adddress),mUser.getAddress().getSuite(),mUser.getAddress().getStreet(),mUser.getAddress().getCity(),mUser.getAddress().getZipcode()));
        tvPhone.setText(String.format(getResources().getString(R.string.phone_number),mUser.getPhone()));
        tvCompany.setText(String.format(getResources().getString(R.string.company_name),mUser.getCompany().getName()));
        tvWebsite.setText(String.format(getResources().getString(R.string.website),mUser.getName(),mUser.getWebsite()));
        buttonShowPosts.setText(String.format(getResources().getString(R.string.show_posts_button),mUser.getName()));

        buttonShowPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSelect.setVisibility(View.VISIBLE);
                tvSelect.setText(String.format(getResources().getString(R.string.user_posts),mUser.getName()));
                fetchUsersDetails(mUser.getId());
            }
        });

        isPostsShowing = false;
    }

    private void fetchUsersDetails(int id)
    {

        llDetails.setVisibility(View.GONE);
        rlPosts.setVisibility(View.VISIBLE);

        Call<List<UserPost>> call = RestClient.getApiInterface(getContext()).getUserPosts(id);
        call.enqueue(new Callback<List<UserPost>>() {
            @Override
            public void onResponse(Call<List<UserPost>>call, Response<List<UserPost>> response) {
                progress.setVisibility(View.GONE);
                populatePosts(response.body());
            }

            @Override
            public void onFailure(Call<List<UserPost>>call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populatePosts(List<UserPost> posts)
    {
        mAdapter = new UserPostRecyclerAdapter(posts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        isPostsShowing = true;
    }

    private void overideBack(View view)
    {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP)
                {
                    if(isPostsShowing)
                    {
                        populateDetails(presentUser);
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                return false;
            }
        });
    }


}