package com.example.beautyprogram.forWizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.beautyprogram.R;
import com.example.beautyprogram.forWizard.WizardAccount;

public class WizardPersonalOrder extends AppCompatActivity {

    private ImageButton imageButtonBack, imageButtonAddPersonalOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_personal_order);

        imageButtonBack = findViewById(R.id.imageButtonBack);


        imageButtonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnWizAcc(view);
            }
        });
    }
    public void backOnWizAcc(View v){
        Intent intentWizAcc = new Intent(this, WizardAccount.class);
        startActivity(intentWizAcc);
    }
}