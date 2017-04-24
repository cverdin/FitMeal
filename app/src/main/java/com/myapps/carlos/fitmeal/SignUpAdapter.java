package com.myapps.carlos.fitmeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Carlos on 4/23/2017.
 */

public class SignUpAdapter extends Activity{
    Button confirmSign;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        final EditText p1 = (EditText) findViewById(R.id.password1);
        final String password1 = p1.getText().toString();
        final EditText p2 = (EditText) findViewById(R.id.password2);
        final String password2 = p2.getText().toString();
        confirmSign = (Button) findViewById(R.id.ConfirmSignUp);

        confirmSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(password1.equals(password2)){
                    Toast.makeText(SignUpAdapter.this, "Thanks for Signing Up!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpAdapter.this, basic_info_adapter.class);
                    startActivity(intent);
                }
                else if(!password1.equals(password2)){
                    Toast.makeText(SignUpAdapter.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
