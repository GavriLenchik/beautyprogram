package com.example.beautyprogram.forClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyprogram.R;
import com.example.beautyprogram.database.ClientBase;
import com.example.beautyprogram.database.FeedbackBaze;
import com.example.beautyprogram.database.OrdersBase;

public class WindowOrderManic extends AppCompatActivity {

    FeedbackBaze feedbackBase;
    OrdersBase ordersBase;
    ClientBase clientBase = new ClientBase(this);
    String[] spinnerV = { "UAN", "USD", "EUR"};
    String[] spinnerEquipChosse = { "Master`s", "Client`s"};
    private String choice;
    private Button buttonCancelRec2, buttonSendRec2;
    private Spinner spinnerValute2, spinnerEquipChosse2;
    private TextView textViewType2;
    private CheckBox checkBoxEven2, checkBoxEven3, checkBoxEven4, checkBoxEven5;
    private EditText editTextNumberDecimal2, editTextTextAddress2, editTextDate3, editTextTime2, editTextTextMultiLine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_order_manic);

        String currentLogin = getIntent().getStringExtra("currentLogin");
        ordersBase = new OrdersBase(this);
        feedbackBase = new FeedbackBaze(this);
        buttonCancelRec2 = findViewById(R.id.buttonCancelRec2);
        textViewType2 = findViewById(R.id.textViewType2);
        checkBoxEven2 = findViewById(R.id.checkBoxEven2);
        checkBoxEven3 = findViewById(R.id.checkBoxEven3);
        checkBoxEven4 = findViewById(R.id.checkBoxEven4);
        checkBoxEven5 = findViewById(R.id.checkBoxEven5);
        editTextNumberDecimal2 = findViewById(R.id.editTextNumberDecimal2);
        editTextTextAddress2 = findViewById(R.id.editTextTextAddress2);
        editTextDate3 = findViewById(R.id.editTextDate3);
        editTextTime2 = findViewById(R.id.editTextTime2);
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        buttonSendRec2 = findViewById(R.id.buttonSendRec2);
        spinnerValute2 = findViewById(R.id.spinnerValute2);
        spinnerEquipChosse2 = findViewById(R.id.spinnerEquipChosse2);

        buttonCancelRec2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnOrderList(view);
            }
        });
        buttonSendRec2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String selectedValute = spinnerValute2.getSelectedItem().toString();
                String selectedEquip = spinnerEquipChosse2.getSelectedItem().toString();
                if(checkBoxEven2.isChecked()) {
                    choice = checkBoxEven2.getText().toString();
                }
                else if(checkBoxEven3.isChecked()) {
                    choice = checkBoxEven3.getText().toString();
                }
                else if(checkBoxEven4.isChecked()) {
                    choice = checkBoxEven4.getText().toString();
                }
                else {
                    choice = checkBoxEven5.getText().toString();
                }
                insertOrd(selectedValute, selectedEquip,currentLogin,choice);
                finish();
                backOnOrderList(view);
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerV);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerValute2.setAdapter(adapter);

        ArrayAdapter<String> adapterV = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerEquipChosse);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerEquipChosse2.setAdapter(adapterV);

        AdapterView.OnItemSelectedListener itemSelectedListenerV = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerValute2.setOnItemSelectedListener(itemSelectedListenerV);
        AdapterView.OnItemSelectedListener itemSelectedListenerE = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerEquipChosse2.setOnItemSelectedListener(itemSelectedListenerE);
    }
    private void insertOrd(String selectedValute, String selectedEquip, String currentLogin, String choice){
        SQLiteDatabase dbfeed = feedbackBase.getWritableDatabase();
        SQLiteDatabase dbCl = clientBase.getWritableDatabase();
        SQLiteDatabase dbOrd = ordersBase.getWritableDatabase();
        ContentValues contentValuesF = new ContentValues();
        ContentValues contentValues = new ContentValues();

        String selectionC = ClientBase.KEY_LOGIN + "=?";
        String[] selectionArgsC = {currentLogin};
        String[] columns = {
                ClientBase.KEY_ID,
                ClientBase.KEY_NAME,
                ClientBase.KEY_SURNAME,
                ClientBase.KEY_LOGIN,
                ClientBase.KEY_PASSWORD,
                ClientBase.KEY_PHONE,
        };

        Cursor c;
        c = dbCl.query(ClientBase.DATABASE_NAME, columns , selectionC, selectionArgsC, null, null, null);
        int loginCIndex = c.getColumnIndex(ClientBase.KEY_LOGIN);
        int nameCIndex = c.getColumnIndex(ClientBase.KEY_NAME);
        int surnameCIndex = c.getColumnIndex(ClientBase.KEY_SURNAME);

        String clientName;
        while (c.moveToNext()) {
            if (c.getString(loginCIndex).equals(currentLogin)) {
                clientName = ""+c.getString(nameCIndex) + " " + c.getString(surnameCIndex)+"";
                contentValues.put(OrdersBase.KEY_NAME, clientName);
                contentValues.put(OrdersBase.KEY_LOGINFROM, currentLogin);
                contentValuesF.put(FeedbackBaze.KEY_TOLOGIN, currentLogin);
            } else {
                Toast.makeText(WindowOrderManic.this, "Sorry. Error!", Toast.LENGTH_LONG).show();
            }
        }
        c.close();
        String type = textViewType2.getText().toString().trim();
        String price = editTextNumberDecimal2.getText().toString().trim();
        String valuta = selectedValute;
        String address = editTextTextAddress2.getText().toString().trim();
        String equipment = selectedEquip;
        String data = editTextDate3.getText().toString().trim();
        String time = editTextTime2.getText().toString().trim();
        String details = editTextTextMultiLine.getText().toString().trim();


        contentValues.put(OrdersBase.KEY_TYPE_ORD, type);
        contentValues.put(OrdersBase.KEY_CHECK, choice);
        contentValues.put(OrdersBase.KEY_PRICE, price);
        contentValues.put(OrdersBase.KEY_VALUTA, valuta);
        contentValues.put(OrdersBase.KEY_ADDRESS, address);
        contentValues.put(OrdersBase.KEY_EQUIPMENT, equipment);
        contentValues.put(OrdersBase.KEY_DATA, data);
        contentValues.put(OrdersBase.KEY_TIME, time);
        contentValues.put(OrdersBase.KEY_DETAILS, details);

        long newWiz = dbOrd.insert(OrdersBase.DATABASE_NAME, null, contentValues);
        if (newWiz == -1) {
            // Если ID  -1, значит произошла ошибка
            Toast.makeText(this, "Error creating order", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(WindowOrderManic.this, R.string.succes,Toast.LENGTH_LONG).show();
        }
    }
    public void backOnOrderList(View v){
        Intent intentOrderList = new Intent(this, ClientAccount.class);
        startActivity(intentOrderList);
    }
}