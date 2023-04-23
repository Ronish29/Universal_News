package com.example.universalnews;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class News_Display extends AppCompatActivity {
    private static ArrayList<String> selectedGenres;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Article> articles = new ArrayList<>();
    private String apiKey="b2173fbbb1ff42958d1eeb5618589d2f";
    private String country="in";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_display);

        recyclerView = findViewById(R.id.news_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapter = new NewsAdapter(articles,new NewsAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Article article) {
                Intent intent = new Intent(News_Display.this, WebView.class);

                // Pass the url of the selected news article as an extra
                intent.putExtra("url", article.getUrl());

                // Start the activity
                startActivity(intent);

            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        selectedGenres = getIntent().getStringArrayListExtra("selectedGenres");
        System.out.println(selectedGenres);
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<NewsResponse> call = apiInterface.getNewsByGenres(country,selectedGenres, apiKey);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    articles.clear(); // clear the existing list
                    articles.addAll(response.body().getArticles()); // add the new articles
                    adapter.notifyDataSetChanged();
//                    articles = response.body().getArticles();
//                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(News_Display.this, "Failed to retrieve news articles", Toast.LENGTH_SHORT).show();
            }
        });

    }
}