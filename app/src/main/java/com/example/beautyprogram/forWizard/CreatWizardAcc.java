package com.example.beautyprogram.forWizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beautyprogram.MainActivity;
import com.example.beautyprogram.R;
import com.example.beautyprogram.RegistrationRequest;
import com.example.beautyprogram.database.WizardBase;

public class CreatWizardAcc extends AppCompatActivity {

    WizardBase wizardBase;
    private Button buttonCancel, buttonCreateWiz;
    private EditText editTextTextPersonName, editTextSurname, editTextRange, editTextStatus, editTextEmail, editTextPhone, editTextLogin, editTextPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_wizard_acc);

        wizardBase = new WizardBase(this);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonCreateWiz = findViewById(R.id.buttonCreateWiz);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextRange = findViewById(R.id.editTextRange);
        editTextStatus = findViewById(R.id.editTextStatus);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextLogin = findViewById(R.id.editTextLogin);
        editTextPass = findViewById(R.id.editTextPass);

        buttonCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnReg(view);
            }
        });
        buttonCreateWiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                insertWiz();
                finish();
                backOnMain(view);
            }
        });
    }
    private void insertWiz(){
        String name = editTextTextPersonName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String login = editTextLogin.getText().toString().trim();
        String pass = editTextPass.getText().toString().trim();
        String status = editTextStatus.getText().toString().trim();
        String range = editTextRange.getText().toString().trim();

        SQLiteDatabase dbWiz = wizardBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(WizardBase.KEY_NAME, name);
        contentValues.put(WizardBase.KEY_SURNAME, surname);
        contentValues.put(WizardBase.KEY_MAIL, email);
        contentValues.put(WizardBase.KEY_PHONE, phone);
        contentValues.put(WizardBase.KEY_PASSWORD, pass);
        contentValues.put(WizardBase.KEY_LOGIN, login);
        contentValues.put(WizardBase.KEY_STATUS, status);
        contentValues.put(WizardBase.KEY_RANGE, range);
        contentValues.put(WizardBase.KEY_CITY, "Odessa");
        contentValues.put(WizardBase.KEY_ACTIVITY, "Visagiste");
        contentValues.put(WizardBase.KEY_SPECIAL, "");

        long newWiz = dbWiz.insert(WizardBase.DATABASE_NAME, null, contentValues);
        if (newWiz == -1) {
            // Если ID  -1, значит произошла ошибка
            Toast.makeText(this, "Error creating user", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CreatWizardAcc.this, R.string.created,Toast.LENGTH_LONG).show();
        }
    }
    public void backOnReg(View v){
        Intent intentBackReg = new Intent(this, RegistrationRequest.class);
        startActivity(intentBackReg);
    }
    public void backOnMain(View v){
        Intent intentBackMain = new Intent(this, MainActivity.class);
        startActivity(intentBackMain);
    }

}