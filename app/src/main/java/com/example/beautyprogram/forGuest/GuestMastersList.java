package com.example.beautyprogram.forGuest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.beautyprogram.R;
import com.example.beautyprogram.database.WizardBase;
import com.example.beautyprogram.forClient.WizardProfileForClient;
import com.example.beautyprogram.forGuest.GuestAccount;

public class GuestMastersList extends AppCompatActivity {

    LinearLayout linGuestMastList;
    WizardBase wizardBase;
    private ImageButton imageButtonBack4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_masters_list);

        wizardBase = new WizardBase(this);
        imageButtonBack4 = findViewById(R.id.imageButtonBack4);
        linGuestMastList = findViewById(R.id.linGuestMastList);

        SQLiteDatabase dbWiz = wizardBase.getWritableDatabase();
        String[] columns = {WizardBase.KEY_ID,
                WizardBase.KEY_NAME,
                WizardBase.KEY_SURNAME,
                WizardBase.KEY_MAIL,
                WizardBase.KEY_PHONE,
                WizardBase.KEY_LOGIN,
                WizardBase.KEY_PASSWORD,
                WizardBase.KEY_STATUS,
                WizardBase.KEY_RANGE
        };

        Cursor c;
        c = dbWiz.query(WizardBase.DATABASE_NAME, columns, null, null, null, null, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        int loginWIndex = c.getColumnIndex(WizardBase.KEY_LOGIN);
        int idWIndex = c.getColumnIndex(WizardBase.KEY_ID);
        while (c.moveToNext()) {
            Button btnNew = new Button(this);
            btnNew.setText(c.getString(loginWIndex));
            btnNew.setBackgroundColor(Color.parseColor("#f0a1e6"));
            btnNew.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            btnNew.setId(Integer.parseInt(c.getString(idWIndex)));
            linGuestMastList.addView(btnNew,layoutParams);
            btnNew.findViewById(btnNew.getId()).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    onWizForClient(view,btnNew.getId());
                }
            });
        }
        c.close();
        imageButtonBack4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnGuestAcc(view);
            }
        });
    }
    public void backOnGuestAcc(View v){
        Intent intentClientAcc = new Intent(this, GuestAccount.class);
        startActivity(intentClientAcc);
    }
    public void onWizForClient(View v,int idOrder ){
        Intent intentClientAcc = new Intent(this, WizardProfileForClient.class);
        intentClientAcc.putExtra("idOrder", idOrder);
        startActivity(intentClientAcc);
    }
}