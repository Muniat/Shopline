package com.example.shopline.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shopline.Controller.JsonPlaceholderAPI;
import com.example.shopline.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://shopline-29362.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(com.example.shopline.Controller.JsonPlaceholderAPI.class);

        Call<List<Post>> call = jsonPlaceholderAPI.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textView.setText(response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post x : posts){
                    String content = "";
                    content += "ID: " + x.getId()+"\n";
                    content+= "User Id : " + x.getUserId()+"\n";
                    content += "Name: " +x.getName() +"\n";
                    content += "Info: " + x.getText()+ "\n\n";
                    textView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });




    }
}
