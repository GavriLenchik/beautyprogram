package com.example.beautyprogram.forClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.beautyprogram.R;

public class OrderList extends AppCompatActivity {

    private Button buttonManic, buttonMake, buttonHair, buttonMassage, buttonCExit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        String currentLogin = getIntent().getStringExtra("currentLogin");
        buttonManic = findViewById(R.id.buttonManic);
        buttonMake = findViewById(R.id.buttonMake);
        buttonHair = findViewById(R.id.buttonHair);
        buttonMassage = findViewById(R.id.buttonMassage);
        buttonCExit2 = findViewById(R.id.buttonCExit2);

        buttonCExit2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnClientAcc(view);
            }
        });
        buttonMake.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onWindOrderMake(view,currentLogin);
            }
        });
        buttonManic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onWindOrderManic(view,currentLogin);
            }
        });
    }
    public void backOnClientAcc(View v){
        Intent intentClientAcc = new Intent(this, ClientAccount.class);
        startActivity(intentClientAcc);
    }
    public void onWindOrderMake(View v, String currentLogin){
        Intent intentWindOrderMake = new Intent(this, WindowOrder.class);
        intentWindOrderMake.putExtra("currentLogin", currentLogin);
        startActivity(intentWindOrderMake);
    }
    public void onWindOrderManic(View v, String currentLogin){
        Intent intentWindOrderManic = new Intent(this,WindowOrderManic.class);
        intentWindOrderManic.putExtra("currentLogin", currentLogin);
        startActivity(intentWindOrderManic);
    }

}