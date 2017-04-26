package com.myapps.carlos.fitmeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Carlos on 4/22/2017.
 */

public class basic_info_adapter extends Activity{
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_info);

        ok = (Button) findViewById(R.id.OK_info);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent personal_page = new Intent(basic_info_adapter.this, MealCategoryAdapter.class);
                startActivity(personal_page);
            }
        });

    }

}
