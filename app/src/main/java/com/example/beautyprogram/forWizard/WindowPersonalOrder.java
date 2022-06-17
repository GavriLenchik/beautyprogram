package com.example.beautyprogram.forWizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.beautyprogram.R;

public class WindowPersonalOrder extends AppCompatActivity {

    private TextView textViewCustomName, textViewService, textViewPrice, textViewDate, textViewTime, textViewAddress, textViewCustNumber;
    private Button buttonPrevRec, buttonBack, buttonNextRec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_personal_order);

        buttonBack = findViewById(R.id.buttonBack);
        buttonPrevRec = findViewById(R.id.buttonPrevRec);
        buttonNextRec = findViewById(R.id.buttonNextRec);
        textViewCustomName = findViewById(R.id.textViewCustomName);
        textViewService = findViewById(R.id.textViewService);
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewDate = findViewById(R.id.textViewDate);
        textViewTime = findViewById(R.id.textViewTime);
        textViewAddress = findViewById(R.id.textViewAddress);
        textViewCustNumber = findViewById(R.id.textViewCustNumber);

        buttonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnPersonalOrders(view);
            }
        });
    }
    public void backOnPersonalOrders(View v){
        Intent intentPersonalOrders = new Intent(this, WizardPersonalOrder.class);
        startActivity(intentPersonalOrders);
    }
}