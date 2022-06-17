package com.example.beautyprogram.forClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beautyprogram.MainActivity;
import com.example.beautyprogram.R;

public class ClientAccount extends AppCompatActivity {

    private Button buttonWExit2, buttonCFreeMasters, buttonCOrder, buttonCProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_account);

        String currentLogin = getIntent().getStringExtra("currentLogin");
        buttonWExit2 = findViewById(R.id.buttonWExit2);
        buttonCFreeMasters = findViewById(R.id.buttonCFreeMasters);
        buttonCOrder = findViewById(R.id.buttonCOrder);
        buttonCProfile = findViewById(R.id.buttonCProfile);

        buttonWExit2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnMain(view);
            }
        });
        buttonCProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onClientProfile(view, currentLogin);
            }
        });
        buttonCOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onOrder(view, currentLogin);
            }
        });
        buttonCFreeMasters.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onMastersList(view, currentLogin);
            }
        });
    }
    public void backOnMain(View v){
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
    }
    public void onClientProfile(View v, String currentLogin){
        Intent intentClientProfile = new Intent(this, ClientProfile.class);
        intentClientProfile.putExtra("currentLogin", currentLogin);
        startActivity(intentClientProfile);
    }
    public void onOrder(View v, String currentLogin){
        Intent intentOrd = new Intent(this, OrderList.class);
        intentOrd.putExtra("currentLogin", currentLogin);
        startActivity(intentOrd);
    }
    public void onMastersList(View v, String currentLogin){
        Intent intentMasters = new Intent(this, MastersList.class);
        intentMasters.putExtra("currentLogin", currentLogin);
        startActivity(intentMasters);
    }
}