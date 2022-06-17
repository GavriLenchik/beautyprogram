package com.example.beautyprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beautyprogram.forClient.CreateClientAcc;
import com.example.beautyprogram.forWizard.CreatWizardAcc;

public class RegistrationRequest extends AppCompatActivity {
    private Button buttonRegWiz,buttonRegClient,buttonCancel3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_request);

        buttonRegWiz = findViewById(R.id.buttonRegWiz);
        buttonRegClient = findViewById(R.id.buttonRegClient);
        buttonCancel3 = findViewById(R.id.buttonCancel3);

        buttonRegWiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateWizAcc(view);
            }
        });
        buttonRegClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateClientAcc(view);
            }
        });
        buttonCancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backOnMain(view);
            }
        });
    }
    public void onCreateWizAcc(View v){
        Intent intentCW = new Intent(this, CreatWizardAcc.class);
        startActivity(intentCW);
    }
    public void onCreateClientAcc(View v){
        Intent intentCC = new Intent(this, CreateClientAcc.class);
        startActivity(intentCC);
    }
    public void backOnMain(View v){
        Intent intentBackMain = new Intent(this,MainActivity.class);
        startActivity(intentBackMain);
    }
}