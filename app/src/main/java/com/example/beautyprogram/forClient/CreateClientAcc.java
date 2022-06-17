package com.example.beautyprogram.forClient;

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
import com.example.beautyprogram.database.ClientBase;

public class CreateClientAcc extends AppCompatActivity {

    ClientBase clientBase;
    private Button buttonCancel2, buttonCreateClient;
    private EditText editTextTextPersonName2, editTextSurname2, editTextNumber2, editTextLogin2, editTextPass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client_acc);

        clientBase = new ClientBase(this);
        buttonCancel2 = findViewById(R.id.buttonCancel2);
        buttonCreateClient = findViewById(R.id.buttonCreateClient);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextSurname2 = findViewById(R.id.editTextSurname2);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        editTextLogin2 = findViewById(R.id.editTextLogin2);
        editTextPass2 = findViewById(R.id.editTextPass2);

        buttonCancel2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnReg(view);
            }
        });
        buttonCreateClient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                insertClient();
                finish();
                backOnMain(view);
            }
        });
    }
    private void insertClient(){
        String name = editTextTextPersonName2.getText().toString().trim();
        String surname = editTextSurname2.getText().toString().trim();
        String phone = editTextNumber2.getText().toString().trim();
        String login = editTextLogin2.getText().toString().trim();
        String pass = editTextPass2.getText().toString().trim();

        SQLiteDatabase dbCl = clientBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ClientBase.KEY_NAME, name);
        contentValues.put(ClientBase.KEY_SURNAME, surname);
        contentValues.put(ClientBase.KEY_PHONE, phone);
        contentValues.put(ClientBase.KEY_PASSWORD, pass);
        contentValues.put(ClientBase.KEY_LOGIN, login);

        long newCl = dbCl.insert(ClientBase.DATABASE_NAME, null, contentValues);
        if (newCl == -1) {
            // Если ID  -1, значит произошла ошибка
            Toast.makeText(this, "Error creating user", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CreateClientAcc.this, R.string.created,Toast.LENGTH_LONG).show();
        }
    }
    public void backOnReg(View v){
        Intent intentReg = new Intent(this, RegistrationRequest.class);
        startActivity(intentReg);
    }
    public void backOnMain(View v){
        Intent intentBackMain = new Intent(this, MainActivity.class);
        startActivity(intentBackMain);
    }
}