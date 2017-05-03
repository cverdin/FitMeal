package com.myapps.carlos.fitmeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


/**
 * Created by Carlos on 4/22/2017.
 */

public class basic_info_adapter extends Activity {
    Button ok;
    ArrayAdapter<String> ftAdapter;
    ArrayAdapter<String> inAdapter;
    ArrayAdapter<String> genAdapter;
    ArrayAdapter<String> wtAdapter;
    String height_ft;
    String height_in;
    String weight;
    String gender;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_info);

        String[] feet= {"1","2","3","4","5","6"};
        String[] inches={"1","2","3","4","5","6","7","8","9","10","11"};
        String[] gender= {"Male", "Female"};
        String[] weight={"100","120","140","160","180","200","220","240","260","280"};

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

        ftSpinner.setAdapter(ftAdapter);
        inSpinner.setAdapter(inAdapter);
        wtSpinner.setAdapter(wtAdapter);
        genSpinner.setAdapter(genAdapter);

        ftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                height_ft = adapter.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Toast.makeText(getApplicationContext(),
                        "Selected Country : " + height_ft, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent personal_page = new Intent(basic_info_adapter.this, MealCategoryAdapter.class);
                startActivity(personal_page);
            }
        });
    }

}
