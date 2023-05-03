package com.example.universalnews;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.universalnews.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Login extends Fragment {
    private ImageButton google_login;
    private Button signUpButton;
    private Button login;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    private int[] colors = {Color.parseColor("#09CCC8"), Color.parseColor("#ffd6d3"),
            Color.parseColor("#FD7BCE"), Color.parseColor("#A4DDF8"),
            Color.parseColor("#26F30F"), Color.parseColor("#F6F8F0")};

    private int currentIndex = 0;
    private Handler handler = new Handler();
    public Login() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

        final View view = getView();
        if (view != null) {
            // Create new gradient drawables using the XML files
            GradientDrawable gradient1 = (GradientDrawable) getResources().getDrawable(R.drawable.gradient_one);
            GradientDrawable gradient2 = (GradientDrawable) getResources().getDrawable(R.drawable.gradient_two);
            GradientDrawable gradient3 = (GradientDrawable) getResources().getDrawable(R.drawable.gradient_three);

            // Create an array of the gradient drawables
            final GradientDrawable[] gradients = {gradient1, gradient2, gradient3};

            // Set initial background
            view.setBackground(gradients[currentIndex % gradients.length]);

            // Define a runnable to cycle through the gradients
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    currentIndex++;
                    GradientDrawable nextGradient = gradients[currentIndex % gradients.length];
                    TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[] {view.getBackground(), nextGradient});
                    view.setBackground(transitionDrawable);
                    transitionDrawable.startTransition(1750);
                    handler.postDelayed(this, 1750);
                }
            };

            // Start the runnable to cycle through the gradients
            handler.post(runnable);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient= GoogleSignIn.getClient(getContext(),googleSignInOptions);
        // Get reference to sign up button
        signUpButton = view.findViewById(R.id.button3);
        google_login= view.findViewById(R.id.google);
        login=view.findViewById(R.id.button2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Set onClickListener on sign up button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create new instance of fragment to show on sign up button click
                Register signUpFragment = new Register();

                // Replace current fragment with new fragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, signUpFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SignIn();


            }
        });
        return view;
    }

    private void SignIn() {
        Intent intent=googleSignInClient.getSignInIntent();
        startActivityForResult(intent,100);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                HomeActivity();
            } catch (ApiException e) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void HomeActivity() {
        getActivity().finish();
        Intent intent=new Intent(getContext(),HomeActivity.class);
        startActivity(intent);
    }
}
