package com.example.universalnews;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    TextView name;
    Button logout;
    News_Gener_Adapter news_gener_adapter;
    RecyclerView recyclerView;
    private List<Genre> genres = new ArrayList<>();
    private ArrayList<String> selectedGenres = new ArrayList<>();

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String[] genreNames = {"Business", "Entertainment", "Health", "Science", "Sports", "Technology",};
        for (String name : genreNames) {
            genres.add(new Genre(name,false));
        }

        name=findViewById(R.id.name);
        logout=findViewById(R.id.log_out);
        News_Gener_Adapter adapter = new News_Gener_Adapter(genres);
        RecyclerView recyclerView = findViewById(R.id.news_gener);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient= GoogleSignIn.getClient(this,googleSignInOptions);
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            String Name=account.getDisplayName();
            name.setText(Name);
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Genre genre : genres) {
                    if (genre.isChecked()) {
                        selectedGenres.add(genre.getName());
                    }
                }

                Intent intent=new Intent(HomeActivity.this
                        ,News_Display.class);
                intent.putStringArrayListExtra("selectedGenres", selectedGenres);
                startActivity(intent);
                Log.e(TAG, String.valueOf(selectedGenres));
            }
        });

    }

    private void Signout() {
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });
    }
}