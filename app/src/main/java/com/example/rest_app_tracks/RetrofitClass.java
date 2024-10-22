package com.example.rest_app_tracks;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass extends AppCompatActivity{
    final static String URL_server = "http://10.0.2.2:8080/dsaApp/";
    private static Retrofit retrofit;
    private static APIRequests apiRequests;

    public static Retrofit getRetrofit() {
        if(retrofit==null) {
            HttpLoggingInterceptor interceptor= new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL_server)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static APIRequests getAPIRequest() {
        if(apiRequests==null) {
            apiRequests = getRetrofit().create(APIRequests.class);
        }
        return apiRequests;
    }

}
