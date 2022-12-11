package com.example.hblmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SIGNUPActivity extends AppCompatActivity {
    Button btnreg;
    EditText editText1,editText2,editText3,editText4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        btnreg = findViewById(R.id.REG);
        editText1 = findViewById(R.id.PERSON);

        editText3 = findViewById(R.id.editTextTextEmailAddress);
        editText4 = findViewById(R.id.editTextTextPassword);




        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = editText1.getText().toString().trim();
                String pin = editText2.getText().toString().trim();


                if (mail==null||pin==null) {
                    Toast.makeText(SIGNUPActivity.this, "Enter Email Or Pin", Toast.LENGTH_SHORT).show();
                }

                else {
                    openSIGNINActivity();}
            }


        });

    }

    public void openSIGNINActivity(){
        Intent intent = new Intent(this, SIGNINActivity.class);
        startActivity(intent);
    }
}