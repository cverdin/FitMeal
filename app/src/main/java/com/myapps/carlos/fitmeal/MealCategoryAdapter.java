package com.myapps.carlos.fitmeal;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Carlos on 4/24/2017.
 */

public class MealCategoryAdapter extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    FragmentManager fragmentManager;

    Spinner active_amount;
    TextView calories;
    String physical_activity = "";

    //spinner drop down items
    String[] exercise = {"Sedentary (No exercise)", "Light Active (1-3 days/week)", "Moderately Active (3-5 days/week)",
            "Very Active(6-7 days/week)", "Extra Active (2X hard training per day)"};
    double BMR;
    String name;
    TextView userInfo;

    EditText searchInput;
    Button queryButton;
    String queryString = "";
    String myUrl= "http://www.recipepuppy.com/api/";
    String result = "";
    HttpGetRequest getRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealcategory);

        /**
         * Fragment shall be used for recipe
         * */
       fragmentManager = getFragmentManager();

        //spinner element
        active_amount = (Spinner) findViewById(R.id.physical_activity);
        active_amount.setOnItemSelectedListener(this);

        //adapter for spinner
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, exercise);
        active_amount.setAdapter(mAdapter);


        BMR = getIntent().getExtras().getDouble("BMR");
        name = getIntent().getExtras().getString("name");

        userInfo = (TextView) findViewById(R.id.userInfoDisplay);
        userInfo.setText("Hello " + name + ", Your Basal metabolic rate (BMR) is " + (int) (BMR));

        calories = (TextView) findViewById(R.id.calories_req);



        //Used for the execution of query search**************************
        queryButton = (Button) findViewById(R.id.recipe_button);
        queryButton.setOnClickListener(this);
        searchInput = (EditText) findViewById(R.id.recipe_search);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //sets amount of physical activity from array to global variable
        physical_activity = adapterView.getItemAtPosition(i).toString();

        switch (physical_activity) {
            case "Sedentary (No exercise)":
                calories.setText("Calories per day: " + (int)(BMR * 1.2));
                break;
            case "Light Active (1-3 days/week)":
                calories.setText("Calories per day: " + (int)(BMR * 1.375));
                break;
            case "Moderately Active (3-5 days/week))":
                calories.setText("Calories per day: " + (int)(BMR * 1.55));
                break;
            case "Very Active(6-7 days/week)":
                calories.setText("Calories per day: " + (int)(BMR * 1.725));
                break;
            case "Extra Active (2X hard training per day)":
                calories.setText("Calories per day: " + (int)(BMR * 1.9));
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        queryString = "?q="+ searchInput.toString();

        getRequest = new HttpGetRequest();
        try {
            result = getRequest.execute(myUrl.concat(queryString)).get();
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();

        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
        }

    }

}
