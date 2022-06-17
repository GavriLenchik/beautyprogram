package com.example.beautyprogram.forWizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beautyprogram.MainActivity;
import com.example.beautyprogram.R;

public class WizardAccount extends AppCompatActivity {


    private Button buttonWProfile, buttonWOrders, buttonWFreeOrd, buttonWExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_account);


        String currentLogin = getIntent().getStringExtra("currentLogin");
        buttonWProfile = findViewById(R.id.buttonWProfile);
        buttonWOrders = findViewById(R.id.buttonWOrders);
        buttonWFreeOrd = findViewById(R.id.buttonWFreeOrd);
        buttonWExit = findViewById(R.id.buttonWExit);

        buttonWExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnMain(view);
            }
        });
        buttonWProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onWProfile(view, currentLogin);
            }
        });
        buttonWOrders.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onWPersOrders(view);
            }
        });
        buttonWFreeOrd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onWFreeOrder(view,currentLogin);
            }
        });
    }
    public void backOnMain(View v){
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
    }
    public void onWProfile(View v, String currentLogin){
        Intent intentWProf = new Intent(this, WizardProfile.class);
        intentWProf.putExtra("currentLogin", currentLogin);
        startActivity(intentWProf);
    }
    public void onWPersOrders(View v){
        Intent intentWPersOrders = new Intent(this, WizardPersonalOrder.class);
        startActivity(intentWPersOrders);
    }
    public void onWFreeOrder(View v, String currentLogin){
        Intent intentWFreeOrder = new Intent(this, WizardFreeOrders.class);
        intentWFreeOrder.putExtra("currentLogin", currentLogin);
        startActivity(intentWFreeOrder);
    }
}