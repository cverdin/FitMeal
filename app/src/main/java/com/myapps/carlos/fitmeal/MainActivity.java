package com.myapps.carlos.fitmeal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected CheckBox checkTerms;
    protected Button getStarted;
    TextView terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        terms = (TextView) findViewById(R.id.terms);
        terms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Toast.makeText(MainActivity.this, "By using this app you understand" +
                        " that all health related information is simply an estimate" +
                        " and should not be used for serious consultation.", Toast.LENGTH_LONG).show();
            }
        });

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
