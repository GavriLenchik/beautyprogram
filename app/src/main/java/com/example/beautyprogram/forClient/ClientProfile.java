package com.example.beautyprogram.forClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyprogram.R;
import com.example.beautyprogram.database.ClientBase;

public class ClientProfile extends AppCompatActivity {

    ClientBase clientBase;
    String[] countries = { "Odessa", "Kyiv", "Lviv", "Mykolayiv", "Kharkiv", "Kherson"};
    private TextView textViewCName;
    private Spinner spinnerCity2;
    private ImageView imageViewC;
    private Button buttonMast, buttonRev, buttonGrad, buttonCExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        String currentLogin = getIntent().getStringExtra("currentLogin");
        clientBase=new ClientBase(this);
        textViewCName = findViewById(R.id.textViewCName);
        buttonMast = findViewById(R.id.buttonMast);
        buttonRev = findViewById(R.id.buttonRev);
        buttonGrad = findViewById(R.id.buttonGrad);
        buttonCExit = findViewById(R.id.buttonCExit);
        spinnerCity2 = findViewById(R.id.spinnerCity2);
        imageViewC = findViewById(R.id.imageViewC);
        imageViewC.setImageResource(R.drawable.ic_menu_camera);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countries);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerCity2.setAdapter(adapter);

        SQLiteDatabase dbCl = clientBase.getWritableDatabase();

        String[] projectionC = {
                ClientBase.KEY_ID,
                ClientBase.KEY_NAME,
                ClientBase.KEY_SURNAME,
                ClientBase.KEY_LOGIN,
                ClientBase.KEY_PASSWORD,
                ClientBase.KEY_PHONE,
        };
        String selectionC = ClientBase.KEY_LOGIN + "=?";
        String[] selectionArgsC = {currentLogin};

        Cursor c;
        c = dbCl.query(ClientBase.DATABASE_NAME, projectionC, selectionC, selectionArgsC, null, null, null);
        int nameCIndex = c.getColumnIndex(ClientBase.KEY_NAME);
        int surnameCIndex = c.getColumnIndex(ClientBase.KEY_SURNAME);
        int loginCIndex = c.getColumnIndex(ClientBase.KEY_LOGIN);
        while (c.moveToNext()) {
            if (c.getString(loginCIndex).equals(currentLogin)) {
                textViewCName.append("" + c.getString(nameCIndex) + " " + c.getString(surnameCIndex));
            } else {
                Toast.makeText(ClientProfile.this, "Sorry. Error!", Toast.LENGTH_LONG).show();
            }
        }
        c.close();

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerCity2.setOnItemSelectedListener(itemSelectedListener);

        buttonCExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnClientAcc(view);
            }
        });
        buttonRev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onClFeedb(view, currentLogin);
            }
        });
    }
    public void backOnClientAcc(View v){
        Intent intentClientAcc = new Intent(this, ClientAccount.class);
        startActivity(intentClientAcc);
    }
    public void onClFeedb(View v, String currentLogin){
        Intent intentFeedb = new Intent(this, FeedbackList.class);
        intentFeedb.putExtra("currentLogin", currentLogin);
        startActivity(intentFeedb);
    }

}