package com.example.beautyprogram.forClient;

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
import com.example.beautyprogram.database.ClientBase;
import com.example.beautyprogram.database.FeedbackBaze;
import com.example.beautyprogram.database.WizardBase;
import com.example.beautyprogram.forWizard.WizardAccount;

public class FeedbackList extends AppCompatActivity {

    LinearLayout linFeedList;
    WizardBase wizardBase;
    FeedbackBaze feedbackBaze;
    private ImageButton imageButtonBack6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);

        wizardBase = new WizardBase(this);
        feedbackBaze = new FeedbackBaze(this);
        imageButtonBack6 = findViewById(R.id.imageButtonBack6);
        String currentLogin = getIntent().getStringExtra("currentLogin");
        linFeedList = findViewById(R.id.linFeedList);
        imageButtonBack6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnClAcc(view);
            }
        });
        SQLiteDatabase dbfeed = feedbackBaze.getWritableDatabase();
        SQLiteDatabase dbWiz = feedbackBaze.getWritableDatabase();
        String selectionF = FeedbackBaze.KEY_TOLOGIN + "=?";
        String[] selectionArgsF = {currentLogin};
        String[] columsF = {
            FeedbackBaze.KEY_ID,
                    FeedbackBaze.KEY_FROMLOGIN,
                    FeedbackBaze.KEY_TOLOGIN
        };
        Cursor cF;
        cF = dbfeed.query(FeedbackBaze.DATABASE_NAME, columsF, selectionF, selectionArgsF, null, null, null);
        int loginFromWIndex = cF.getColumnIndex(FeedbackBaze.KEY_FROMLOGIN);
        int loginToIndex = cF.getColumnIndex(FeedbackBaze.KEY_TOLOGIN);
        String curWiz = "";
        while (cF.moveToNext()){
            curWiz = cF.getString(loginFromWIndex);
        }
        String[] columns = { WizardBase.KEY_ID,
                WizardBase.KEY_NAME,
                WizardBase.KEY_SURNAME,
                WizardBase.KEY_MAIL,
                WizardBase.KEY_PHONE,
                WizardBase.KEY_LOGIN,
                WizardBase.KEY_PASSWORD,
                WizardBase.KEY_STATUS,
                WizardBase.KEY_RANGE
        };
        String selectionW = WizardBase.KEY_LOGIN + "=?";
        String[] selectionArgsW = {curWiz};
        Cursor c;
        c = dbWiz.query(WizardBase.DATABASE_NAME, columns, selectionW, selectionArgsW, null, null, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        int loginWIndex = c.getColumnIndex(WizardBase.KEY_LOGIN);
        int idWIndex = c.getColumnIndex(WizardBase.KEY_ID);
        while (c.moveToNext()) {
            Button btnNew = new Button(this);
            btnNew.setText(c.getString(loginWIndex));
            btnNew.setBackgroundColor(Color.parseColor("#da87ed"));
            btnNew.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            btnNew.setId(Integer.parseInt(c.getString(idWIndex)));
            linFeedList.addView(btnNew,layoutParams);
            btnNew.findViewById(btnNew.getId()).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    onWizForClient(view,btnNew.getId(),currentLogin);
                }
            });
        }
        c.close();
    }

     public void backOnClAcc(View v){
        Intent intentClAcc = new Intent(this, ClientProfile.class);
        startActivity(intentClAcc);
    }
    public void onWizForClient(View v, int wizId, String currentLogin){
        Intent intentWForCl = new Intent(this, WizardProfileForClient.class);
        intentWForCl.putExtra("idOrder", wizId);
        intentWForCl.putExtra("currentLogin", currentLogin);
        startActivity(intentWForCl);
    }
}