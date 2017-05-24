package com.myapps.carlos.fitmeal;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * Created by Carlos on 4/22/2017.
 */

public class basic_info_adapter extends Activity implements  AdapterView.OnItemSelectedListener, View.OnClickListener{

    String[] feet= {"1","2","3","4","5","6","7"};
    String[] inches={"1","2","3","4","5","6","7","8","9","10","11"};
    String[] gender= {"Male", "Female"};
    String[] weight={"100","110","120","130","140","150","160","170","180","190","200","210","220","230","240","250"};
    Button ok;
    EditText ageInput;
    EditText nameInput;
    ArrayAdapter<String> ftAdapter;
    ArrayAdapter<String> inAdapter;
    ArrayAdapter<String> genAdapter;
    ArrayAdapter<String> wtAdapter;
    double height_ft;
    double height_in;
    double lbs;
    int age;
    String gen;
    double BMR =0;
    String name;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_info);


        ok = (Button) findViewById(R.id.OK_info);

        Spinner ftSpinner = (Spinner) findViewById(R.id.ft_spinner);
        Spinner inSpinner = (Spinner) findViewById(R.id.in_spinner);
        Spinner wtSpinner = (Spinner) findViewById(R.id.weight_spinner);
        Spinner genSpinner = (Spinner) findViewById(R.id.gender_spinner);

        ftAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,feet);
        ftAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,inches);
        inAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wtAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,weight);
        wtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,gender);
        genAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //binds adapter and its data source to the spinner item
        ftSpinner.setAdapter(ftAdapter);
        inSpinner.setAdapter(inAdapter);
        wtSpinner.setAdapter(wtAdapter);
        genSpinner.setAdapter(genAdapter);

        //sets spinner listener for implmented onItemsSelected methods
        ftSpinner.setOnItemSelectedListener(this);
        inSpinner.setOnItemSelectedListener(this);
        wtSpinner.setOnItemSelectedListener(this);
        genSpinner.setOnItemSelectedListener(this);

        ok.setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        /**Do calculations and send results to MealCategory**/
      //  Men: BMR = 66 + (6.23 x weight in pounds) + (12.7 x height in inches) - (6.8 x age in years
        // Women: BMR = 655 + (4.35 x weight in pounds) + (4.7 x height in inches) - (4.7 x age in years)

        ageInput = (EditText) findViewById(R.id.age);
        String input = ageInput.getText().toString().trim();
        nameInput = (EditText) findViewById(R.id.userName);
        name = nameInput.getText().toString();

        if(input.isEmpty()){
            Toast.makeText(this, "Please Enter Your Age.", Toast.LENGTH_SHORT).show();
        }
        else{
            try{
                age = Integer.parseInt(input);
                if(gen.equals("Male")){
                    BMR = 66+ (6.23*lbs) + (12.7 *((height_ft*12)+(height_in))) - (6.8*age);

                }
                else{
                    BMR = 655 + (4.35 * lbs) + (4.7 *((height_ft*12)+(height_in))) - (4.7 * age);
                }
                /**Pass the selected information to the new activity */
               Intent intent= new Intent(basic_info_adapter.this, MealCategoryAdapter.class);
                intent.putExtra("BMR", BMR);
                intent.putExtra("name", name);
                startActivity(intent);
//                Toast.makeText(this, "" + BMR, Toast.LENGTH_LONG).show();

            }catch(Exception e){
                Toast.makeText(this, "Invalid Age", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        // On selecting a spinner item
        switch(adapterView.getId()){
            case R.id.ft_spinner:
                height_ft = Double.parseDouble(adapterView.getItemAtPosition(position).toString());
             //  Toast.makeText(this, height_ft+ "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.in_spinner:
                height_in = Double.parseDouble(adapterView.getItemAtPosition(position).toString());
              //  Toast.makeText(this, height_in+ "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.weight_spinner:
                lbs = Double.parseDouble(adapterView.getItemAtPosition(position).toString());
             //   Toast.makeText(this, lbs + "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gender_spinner:
                gen = adapterView.getItemAtPosition(position).toString();
             //   Toast.makeText(this, height_ft+ "", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "Please make a selection.", Toast.LENGTH_SHORT).show();
    }

}
