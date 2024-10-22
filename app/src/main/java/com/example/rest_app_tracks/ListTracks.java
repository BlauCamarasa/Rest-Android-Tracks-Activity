package com.example.rest_app_tracks;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTracks extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private final String TAG = ListTracks.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_tracks);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(ListTracks.this);
        recyclerView.setLayoutManager(layoutManager);

        // Set the adapter
        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);

        CallAPI();
    }

    private void CallAPI() {
        APIRequests apiRequests = RetrofitClass.getAPIRequest();
        Call<List<Track>> call = apiRequests.getTracks();

        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                // set the results to the adapter
                myAdapter.setData(response.body());
            }
            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {

                String msg = "Error in retrofit: "+t;
                Log.d(TAG,msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClickBotonRetroceder(View view){
        finish();
    }
}