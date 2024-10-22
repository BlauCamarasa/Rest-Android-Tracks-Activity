package com.example.rest_app_tracks;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRemoveTrack extends AppCompatActivity {
    private EditText title;
    private EditText author;
    private final String TAG = AddRemoveTrack.class.getSimpleName();
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_remove_track);
        title = findViewById(R.id.Songtext);
        author = findViewById(R.id.SingerText);

    }

    public void onClickbuttonAddTrack(View view){
        String song = title.getText().toString();
        String singer = author.getText().toString();
        if (song.isEmpty() || singer.isEmpty()) {
            title.setText("");
            author.setText("");
            Toast.makeText(AddRemoveTrack.this, "Please enter the song and the singer.", Toast.LENGTH_SHORT).show();
        } else {
            title.setText("");
            author.setText("");
            Track newsong = new Track(song,singer);

            APIRequests apiRequests = RetrofitClass.getAPIRequest();
            Call<Track> call = apiRequests.AddTrack(newsong);

            call.enqueue(new Callback<Track>() {
                @Override
                public void onResponse(Call<Track> call, Response<Track> response) {
                    switch (response.code()) {
                        case 201:
                            msg = "Successfully added";
                            Toast.makeText(getApplicationContext(), msg+ ", Code: " +String.valueOf(response.code()), Toast.LENGTH_LONG).show();
                            break;
                        case 500:
                            msg = "Song was not added due to problems";
                            Toast.makeText(getApplicationContext(), msg+ ", Code: " +String.valueOf(response.code()), Toast.LENGTH_LONG).show();
                            break;

                    }
                }
                @Override
                public void onFailure(Call<Track> call, Throwable t) {

                    msg = "Error in retrofit: "+t.toString();
                    Log.d(TAG,msg);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
                }
            });

        }
    }
    public void onClickbuttonDeleteTrack(View view){
        String song = title.getText().toString();
        String singer = author.getText().toString();
        if (song.isEmpty() || singer.isEmpty()) {
            title.setText("");
            author.setText("");
            Toast.makeText(AddRemoveTrack.this, "Please enter the song and the singer.", Toast.LENGTH_SHORT).show();
        } else {
            title.setText("");
            author.setText("");

            APIRequests apiRequests = RetrofitClass.getAPIRequest();
            Call<List<Track>> call = apiRequests.getTracks();

            call.enqueue(new Callback<List<Track>>() {
                @Override
                public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                    List<Track> listaTracks = response.body();

                    boolean encontrado = false;
                    for (Track track : listaTracks) {
                        if (track.getTitle().equals(song) || track.getSinger().equals(singer)) {
                            deletesong(track.getId());
                            encontrado = true;
                        }
                    }
                    if (encontrado = true){
                        String msg = "Track found";
                        Log.d(TAG, msg);
                    }
                    else{
                        String msg = "Track not found";
                        Log.d(TAG, msg);
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    }
                    encontrado = false;
                }

                @Override
                public void onFailure(Call<List<Track>> call, Throwable t) {

                    String msg = "Error in retrofit: " + t;
                    Log.d(TAG, msg);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public void deletesong(String id){
        APIRequests apiRequests = RetrofitClass.getAPIRequest();
        Call<Void> call = apiRequests.DeleteTrack(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 201:
                        msg = "Successfully deleted";
                        Toast.makeText(getApplicationContext(), msg+ ", Code: " +response.code(), Toast.LENGTH_LONG).show();
                        break;
                    case 404:
                        msg = "Song was not deleted due to problems";
                        Toast.makeText(getApplicationContext(), msg+ ", Code: " +response.code(), Toast.LENGTH_LONG).show();
                        break;

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                String msg = "Error in retrofit: " + t;
                Log.d(TAG, msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClickbuttonBack(View view){
        finish();
    }

}