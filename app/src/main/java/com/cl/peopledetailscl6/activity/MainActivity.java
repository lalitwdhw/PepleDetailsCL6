package com.cl.peopledetailscl6.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cl.peopledetailscl6.adapter.UserRecyclerAdapter;
import com.cl.peopledetailscl6.Constants;
import com.cl.peopledetailscl6.DataInterfaceUserPosition;
import com.cl.peopledetailscl6.fragment.DetailsFragment;
import com.cl.peopledetailscl6.model.User;
import com.cl.peopledetailscl6.R;
import com.cl.peopledetailscl6.retrofit.RestClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DataInterfaceUserPosition {


    private RecyclerView recyclerViewUserList;
    private ProgressBar progressBar;
    private UserRecyclerAdapter mAdapterUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Call<List<User>> call = RestClient.getApiInterface().getUsers();
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

    /**
     * initialize the variables and put the details fragment in the bottom fragment
     */
    private void init()
    {
        recyclerViewUserList = findViewById(R.id.recyclerPeople);
        progressBar = findViewById(R.id.progress);
    }

    /**
     * initialize and set adapter to recycler view for user list
     * @param mUsers
     */
    private void populateRecyclerUsers(List<User> mUsers)
    {
        mAdapterUserList = new UserRecyclerAdapter(mUsers, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerViewUserList.setLayoutManager(layoutManager);
        recyclerViewUserList.setAdapter(mAdapterUserList);
    }

    /**
     * interface method to pass the selected user data from people recycler adapter to main activity
     * @param mUser
     */
    @Override
    public void userSelected(User mUser) {

        findViewById(R.id.tvSelect).setVisibility(View.GONE);

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
