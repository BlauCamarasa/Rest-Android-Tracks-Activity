package com.example.rest_app_tracks;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateTrack extends AppCompatActivity {
    private EditText song;
    private EditText author;
    private String id;
    String title;
    String singer;
    private final String TAG = UpdateTrack.class.getSimpleName();
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_track);
        //Recuperem el que ens ha passat la activitat ListTracks amb el id,titol i cantant de
        //la canço que han clickat
        id = getIntent().getStringExtra("keyForStringId");
        title = getIntent().getStringExtra("keyForStringTitle");
        singer = getIntent().getStringExtra("keyForStringSinger");


        song = findViewById(R.id.Songtext);
        author = findViewById(R.id.SingerText);

        //posem la info de la canço que han clickat al edittext per a que l'usuari vegi
        //i pugui editar sobre una base
        song.setText(title);
        author.setText(singer);
    }

    public void onClickbuttonUpdateTrack(View view){
        String editedsong = song.getText().toString();
        String editedsinger = author.getText().toString();
        if(editedsinger.equals(singer) & editedsong.equals(title)){
            String msg = "You have not edited anything";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }
        else{
            song.setText("");
            author.setText("");
            Track editedtrack = new Track(editedsong,editedsinger);
            editedtrack.setId(id);
            APIRequests apiRequests = RetrofitClass.getAPIRequest();
            Call<Void> call = apiRequests.UpdateTrack(editedtrack);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    switch (response.code()) {
                        case 201:
                            msg = "Successfully updated";
                            Toast.makeText(getApplicationContext(), msg+ ", Code: " +response.code(), Toast.LENGTH_LONG).show();
                            break;
                        case 404:
                            msg = "Song was not updated due to problems";
                            Toast.makeText(getApplicationContext(), msg+ ", Code: " +response.code(), Toast.LENGTH_LONG).show();
                            break;

                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                    msg = "Error in retrofit: "+t;
                    Log.d(TAG,msg);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
            });

        }
    }
    public void onClickbuttonBack(View view){
        //Crea un nuevo Intent para ir a MainActivity. Usamos FLAG_ACTIVITY_CLEAR_TOP y
        // FLAG_ACTIVITY_NEW_TASK. Esto asegura que si MainActivity ya está en la pila, se llevará
        // a la parte superior y todas las actividades que estén encima se eliminarán.
        Intent intent = new Intent(UpdateTrack.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //Esto cierra la actividad UpdateTrack, asegurando que no regreses a ella al presionar el
        //botón de retroceso.
        finish();
    }



}