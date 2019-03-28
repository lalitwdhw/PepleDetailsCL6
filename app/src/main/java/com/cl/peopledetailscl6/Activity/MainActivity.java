package com.cl.peopledetailscl6.Activity;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cl.peopledetailscl6.Adapter.UserRecyclerAdapter;
import com.cl.peopledetailscl6.Constants;
import com.cl.peopledetailscl6.DataInterfaceUserPosition;
import com.cl.peopledetailscl6.Fragment.DetailsFragment;
import com.cl.peopledetailscl6.Model.User;
import com.cl.peopledetailscl6.R;
import com.cl.peopledetailscl6.Retrofit.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DataInterfaceUserPosition {


    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private UserRecyclerAdapter mAdapter;
    private FrameLayout frameBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Call<List<User>> call = RestClient.getApiInterface(MainActivity.this).getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>>call, Response<List<User>> response) {
                progressBar.setVisibility(View.GONE);
                populateRecyclerUsers(response.body());
            }

            @Override
            public void onFailure(Call<List<User>>call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void init()
    {
        recyclerView = findViewById(R.id.recyclerPeople);
        progressBar = findViewById(R.id.progress);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameBottom, new DetailsFragment())
                .commit();
    }

    private void populateRecyclerUsers(List<User> mUsers)
    {
        mAdapter = new UserRecyclerAdapter(mUsers, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void userSelected(User mUser) {
        //Toast.makeText(MainActivity.this,mUser.getName(),Toast.LENGTH_SHORT).show();

        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle mBundle = new Bundle();
        mBundle.putParcelable(Constants.USER,mUser);
        detailsFragment.setArguments(mBundle);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameBottom, new DetailsFragment());
        fragmentTransaction.commit();


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameBottom, detailsFragment)
                .addToBackStack("")
                .commit();
    }

}
