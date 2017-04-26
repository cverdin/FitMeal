package com.myapps.carlos.fitmeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Carlos on 4/23/2017.
 */

public class SignUpAdapter extends Activity implements View.OnClickListener{
    private Button signUp;
    private EditText editTextEmail;
    private EditText editTextPassword1;
    private EditText editTextPassword2;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Button confirmSign;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword1 = (EditText) findViewById(R.id.password1);
        editTextPassword2 = (EditText) findViewById(R.id.password2);

        confirmSign = (Button) findViewById(R.id.ConfirmSignUp);
        confirmSign.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        //LISTEN FOR SIGN IN AND SIGN OUT
        mAuthListener = new FirebaseAuth.AuthStateListener() {
         @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }

    @Override
    public void onClick(View view){
        //If sign in button click
        if(view == confirmSign){
            addUser();
        }
    }
    private void addUser(){
        final String password1 = editTextPassword1.getText().toString().trim();
        final String password2 = editTextPassword2.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();

        //check if fields are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "No Email entered.", LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password1) || TextUtils.isEmpty(password2)){
            Toast.makeText(this, "Password fields cannot be left blank.", LENGTH_SHORT).show();
            return;
        }
        if(!TextUtils.equals(password1, password2)){
            Toast.makeText(this, "Passwords do NOT match.", LENGTH_SHORT).show();
            return;
        }
       createAccount(email, password1);
       // Intent basic_page = new Intent(SignUpAdapter.this, basic_info_adapter.class);
       // startActivity(basic_page);
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    private void createAccount(final String email, final String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpAdapter.this, "New User Added, Welcome!",
                                    Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(SignUpAdapter.this, "Failure",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}
