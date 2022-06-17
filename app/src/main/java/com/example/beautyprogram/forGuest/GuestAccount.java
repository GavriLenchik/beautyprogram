package com.example.beautyprogram.forGuest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beautyprogram.MainActivity;
import com.example.beautyprogram.R;

public class GuestAccount extends AppCompatActivity {

    private Button buttonGWiz, buttonGFreeOrd, buttonWExit3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_account);

        buttonWExit3 = findViewById(R.id.buttonWExit3);
        buttonGFreeOrd = findViewById(R.id.buttonGFreeOrd);
        buttonGWiz = findViewById(R.id.buttonGWiz);

        buttonWExit3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnMain(view);
            }
        });
        buttonGFreeOrd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onWFreeOrder(view);
            }
        });
        buttonGWiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onMastersList(view);
            }
        });
    }
    public void backOnMain(View v){
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
    }
    public void onWFreeOrder(View v){
        Intent intentWFreeOrder = new Intent(this, GuestFreeOrders.class);
        startActivity(intentWFreeOrder);
    }
    public void onMastersList(View v){
        Intent intentMasters = new Intent(this, GuestMastersList.class);
        startActivity(intentMasters);
    }
}