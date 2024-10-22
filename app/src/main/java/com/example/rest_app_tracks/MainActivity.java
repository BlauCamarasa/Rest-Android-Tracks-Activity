package com.example.rest_app_tracks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    public void onClicklistoftracks(View view){
        Intent intent = new Intent(MainActivity.this, ListTracks.class);
        startActivity(intent);
    }
    public void onClickaddremovetrack(View view){
        Intent intent = new Intent(MainActivity.this, AddRemoveTrack.class);
        startActivity(intent);
    }


}