package com.example.universalnews;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Register extends Fragment {
    private Button loginButton;
    Button submit;
    EditText name,email,mobile_no,password,cnf_password;
    DatabaseReference database=FirebaseDatabase.getInstance().getReference();
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    boolean validEmail=false;

    public Register() {
        // Required empty public constructor
    }
    private int currentIndex = 0;
    private Handler handler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Get reference to sign up button
        loginButton = view.findViewById(R.id.button5);
        submit=view.findViewById(R.id.button4);
        name=view.findViewById(R.id.editTextTextPersonName4);
        email=view.findViewById(R.id.editTextTextPersonName2);
        mobile_no=view.findViewById(R.id.editphone);
        password=view.findViewById(R.id.pass);
        cnf_password=view.findViewById(R.id.confirm_pass);

        // Set onClickListener on sign up button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create new instance of fragment to show on sign up button click
                Login loginFragment = new Login();

                // Replace current fragment with new fragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, loginFragment);
                getParentFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Validate()&& validEmail){
                    String mobile=mobile_no.getText().toString();
                    database.child("users").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(mobile)){
                                Toast.makeText(getContext(), "Phone Number is Already Registred", Toast.LENGTH_SHORT).show();
                            }else {
//                    Toast.makeText(getContext(), "All Validation are true", Toast.LENGTH_SHORT).show();
                                database.child("users").child(mobile).child("Name").setValue(name.getText().toString());
                                database.child("users").child(mobile).child("Email").setValue(email.getText().toString());
                                database.child("users").child(mobile).child("Mobile").setValue(mobile);
                                database.child("users").child(mobile).child("Password").setValue(password.getText().toString());
                                Toast.makeText(getContext(), "Registration is Done", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), HomeActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                name.setBackground(getResources().getDrawable(R.drawable.round));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                email.setBackground(getResources().getDrawable(R.drawable.round));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mobile_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mobile_no.setBackground(getResources().getDrawable(R.drawable.round));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password.setBackground(getResources().getDrawable(R.drawable.round));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        cnf_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cnf_password.setBackground(getResources().getDrawable(R.drawable.round));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }
    private boolean Validate(){
        if(name.getText().toString().isEmpty()){
            name.setBackground(getResources().getDrawable(R.drawable.invalid));
            name.setError("Name is Required");
            return false;
        }
        if(email.getText().toString().isEmpty()){
            email.setBackground(getResources().getDrawable(R.drawable.invalid));
            email.setError("Email is Required");
            return false;
        }
        String email_text=email.getText().toString().trim();
        if(email_text.matches(emailPattern)){
            validEmail=true;
        }
        if(mobile_no.getText().toString().isEmpty()){
            mobile_no.setBackground(getResources().getDrawable(R.drawable.invalid));
            mobile_no.setError("Mobile Number is required");
            return false;
        }else if(mobile_no.getText().length()<10){
            mobile_no.setError("Mobile Number is Invalid");
            return false;
        }
        if(password.getText().toString().isEmpty()){
            password.setBackground(getResources().getDrawable(R.drawable.invalid));
            password.setError("Password is Required");
            return false;
        }
        if(cnf_password.getText().toString().isEmpty()){
            cnf_password.setBackground(getResources().getDrawable(R.drawable.invalid));
            cnf_password.setError("Confirm Password is Required");
            return false;
        }
        if(!password.getText().toString().equals(cnf_password.getText().toString())){
            cnf_password.setError("Confirm Password is Not Match");
            return false;
        }
        return true;
    }
}