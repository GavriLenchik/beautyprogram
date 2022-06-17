package com.example.beautyprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beautyprogram.database.ClientBase;
import com.example.beautyprogram.database.WizardBase;
import com.example.beautyprogram.forClient.ClientAccount;
import com.example.beautyprogram.forGuest.GuestAccount;
import com.example.beautyprogram.forWizard.WizardAccount;

public class MainActivity extends AppCompatActivity {

    private EditText polePass, poleLogin;
    private Button buttonLog, buttonGuest, buttonReg;
    WizardBase wizardBase;
    ClientBase clientBase;
    String currentLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        wizardBase = new WizardBase(this);
        clientBase = new ClientBase(this);
        poleLogin =(EditText) findViewById(R.id.poleLogin);
        polePass = findViewById(R.id.polePass);
        buttonLog = findViewById(R.id.buttonLog);
        buttonReg = findViewById(R.id.buttonReg);
        buttonGuest = findViewById(R.id.buttonGuest);

        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(poleLogin.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, R.string.no_input_login,Toast.LENGTH_LONG).show();
                }
                else if(polePass.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, R.string.no_input_pass,Toast.LENGTH_LONG).show();
                }
                else{

                    boolean connect = false;
                    SQLiteDatabase dbWiz = wizardBase.getWritableDatabase();

                    String[] projectionW = {
                            WizardBase.KEY_ID,
                            WizardBase.KEY_NAME,
                            WizardBase.KEY_SURNAME,
                            WizardBase.KEY_MAIL,
                            WizardBase.KEY_LOGIN,
                            WizardBase.KEY_PASSWORD,
                            WizardBase.KEY_PHONE,
                            WizardBase.KEY_RANGE,
                            WizardBase.KEY_STATUS
                    };
                    String selectionW = WizardBase.KEY_LOGIN + "=?";
                    String[] selectionArgsW = {poleLogin.getText().toString()};
                    Cursor c;
                    c = dbWiz.query(WizardBase.DATABASE_NAME, projectionW, selectionW, selectionArgsW, null, null, null);
                    int loginWIndex = c.getColumnIndex(WizardBase.KEY_LOGIN);
                    int passWIndex = c.getColumnIndex(WizardBase.KEY_PASSWORD);
                    while (c.moveToNext()){
                        String currentWLogin = c.getString(loginWIndex);
                        String currentWPass = c.getString(passWIndex);
                        if(poleLogin.getText().toString().trim().equals(currentWLogin) && polePass.getText().toString().trim().equals(currentWPass)){
                            connect = true;
                            currentLogin = currentWLogin;
                            onWizAcc(view);
                        }
                        else{
                            connect = false;
                        }
                    }

                    if(connect==false){
                        SQLiteDatabase dbCl = clientBase.getWritableDatabase();
                        String[] projectionC = {
                                ClientBase.KEY_ID,
                                ClientBase.KEY_NAME,
                                ClientBase.KEY_SURNAME,
                                ClientBase.KEY_LOGIN,
                                ClientBase.KEY_PASSWORD,
                                ClientBase.KEY_PHONE
                        };
                        String selectionC = ClientBase.KEY_LOGIN + "=?";
                        String[] selectionArgsC = {poleLogin.getText().toString()};
                        c = dbCl.query(ClientBase.DATABASE_NAME, projectionC, selectionC, selectionArgsC, null, null, null);
                        int loginCIndex = c.getColumnIndex(ClientBase.KEY_LOGIN);
                        int passCIndex = c.getColumnIndex(ClientBase.KEY_PASSWORD);
                        while (c.moveToNext()){
                            String currentLoginC = c.getString(loginCIndex);
                            String currentPassC = c.getString(passCIndex);
                            if(poleLogin.getText().toString().trim().equals(currentLoginC) && polePass.getText().toString().trim().equals(currentPassC)){
                                currentLogin = currentLoginC;
                                connect = true;
                                onClientAcc(view);
                            }
                            else{
                                connect = false;
                            }
                            c.close();
                        }
                        if(connect==false){
                            Toast.makeText(MainActivity.this, R.string.no_account,Toast.LENGTH_LONG).show();
                        }
                    }
            }
        }});
        buttonReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onRegAcc(view);
            }
        });
        buttonGuest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onGuestAcc(view);
            }
        });
    }
    public void onWizAcc(View v){
        Intent intentW = new Intent(this, WizardAccount.class);
        intentW.putExtra("currentLogin", currentLogin);
        startActivity(intentW);
    }
    public void onClientAcc(View v){
        Intent intentC = new Intent(this, ClientAccount.class);
        intentC.putExtra("currentLogin", currentLogin);
        startActivity(intentC);
    }
    public void onRegAcc(View v){
        Intent intentReg = new Intent(this,RegistrationRequest.class);
        startActivity(intentReg);
    }
    public void onGuestAcc(View v){
        Intent intentReg = new Intent(this, GuestAccount.class);
        startActivity(intentReg);
    }
}