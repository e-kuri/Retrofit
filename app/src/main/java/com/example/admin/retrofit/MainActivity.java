package com.example.admin.retrofit;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView mTV;
    private EditText pTitle;
    private EditText pBody;
    private ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTV = (TextView) findViewById(R.id.mTV);
        pTitle = (EditText) findViewById(R.id.title);
        pBody = (EditText) findViewById(R.id.pBody);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseBody> call = apiService.getPosts();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                InputStream in = response.body().byteStream();
                StringBuilder sb = new StringBuilder();
                String temp;
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                JSONArray jsonArray;
                try {
                    while((temp = br.readLine()) != null){
                        sb.append(temp);
                    }
                    mTV.setText(sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Retrofit -->", t.toString());
            }
        });

        Call<List<Post>> call2 = apiService.getAllPosts();
        call2.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.i("Retrofit -->", response.body().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.wtf("Retrofit -->", t.toString());
            }
        });
    }

    public void onClick(View view) {
    }

    public void onPost(View view) {
        Post p1 = new Post(101,1,pTitle.getText().toString().trim(),pBody.getText().toString().trim());
        Call<Post> call = apiService.createPost(p1);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i("SUCCESS -->", response.code() + " " + response.body().getId());
                mTV.setTextColor(Color.RED);
                mTV.setText("Success!!! \n Response code is: " + response.code());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.wtf("Retrofit -->", t.toString());
            }
        });
    }
}