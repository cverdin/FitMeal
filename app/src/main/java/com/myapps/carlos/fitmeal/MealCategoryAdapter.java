package com.myapps.carlos.fitmeal;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * Created by Carlos on 4/24/2017.
 */

public class MealCategoryAdapter extends Activity implements View.OnClickListener{

    ImageButton breakfastButton;
    ImageButton lunchButton;
    ImageButton dinnerButton;
    ImageButton snackButton;

    double BMR;
    String name;
    EditText userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealcategory);

        breakfastButton = (ImageButton) findViewById(R.id.breakfastButton);
        breakfastButton.setOnClickListener(this);
        lunchButton = (ImageButton) findViewById(R.id.lunchButton);
        lunchButton.setOnClickListener(this);
        dinnerButton = (ImageButton) findViewById(R.id.dinnerButton);
        dinnerButton.setOnClickListener(this);
        snackButton = (ImageButton) findViewById(R.id.snackButton);
        snackButton.setOnClickListener(this);

       BMR = getIntent().getExtras().getDouble("BMR");
        name = getIntent().getExtras().getString("name");

        userInfo = (EditText) findViewById(R.id.userInfoDisplay);
        userInfo.setText("Hello " + name + ", Your Basal metabolic rate (BMR) is " + (int)(BMR));
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.breakfastButton:
                Toast.makeText(this, "BREAKFAST", Toast.LENGTH_SHORT).show();


        }

    }
}
