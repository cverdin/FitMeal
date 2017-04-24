package com.myapps.carlos.fitmeal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected CheckBox checkTerms;
    protected Button trialBut;
    protected Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    public void init(){

        checkTerms = (CheckBox) findViewById(R.id.termsBox);
        trialBut = (Button) findViewById(R.id.trial);
       signUp = (Button) findViewById(R.id.signUp);


            trialBut.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent getInfo = new Intent(MainActivity.this, basic_info_adapter.class);
                    if(checkTerms.isChecked()) {
                        startActivity(getInfo);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Please accept our Terms before Continuing",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent signUp = new Intent(MainActivity.this, SignUpAdapter.class);
                if(checkTerms.isChecked()) {
                    startActivity(signUp);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please accept our Terms before Continuing",
                            Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}
