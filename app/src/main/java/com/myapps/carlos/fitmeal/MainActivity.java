package com.myapps.carlos.fitmeal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected CheckBox checkTerms;
    protected Button getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){

        checkTerms = (CheckBox) findViewById(R.id.termsBox);
        getStarted = (Button) findViewById(R.id.getStarted);
            getStarted.setOnClickListener(new View.OnClickListener(){
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

    }
}
