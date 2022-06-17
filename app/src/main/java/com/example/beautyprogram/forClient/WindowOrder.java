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
import com.example.beautyprogram.database.FeedbackBaze;
import com.example.beautyprogram.database.ClientBase;
import com.example.beautyprogram.database.OrdersBase;
import com.example.beautyprogram.database.WizardBase;
import com.example.beautyprogram.forWizard.WizardProfile;

public class WindowOrder extends AppCompatActivity {

    FeedbackBaze feedbackBase;
    OrdersBase ordersBase;
    ClientBase clientBase = new ClientBase(this);
    String[] countriesV = { "UAN", "USD", "EUR"};
    String[] countriesE = { "Master`s", "Client`s"};
    private String choice;
    private Button buttonCancelRec, buttonSendRec;
    private Spinner spinnerValute, spinnerEquipChosse;
    private TextView textViewType;
    private CheckBox checkBoxEven, checkBoxBor, checkBoxInv, checkBoxTheatr, checkBoxClassic;
    private EditText editTextNumberDecimal, editTextTextAddress, editTextDate, editTextTime, editTextTextMultiLine2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_order);

        String currentLogin = getIntent().getStringExtra("currentLogin");
        ordersBase = new OrdersBase(this);
        feedbackBase = new FeedbackBaze(this);
        buttonCancelRec = findViewById(R.id.buttonCancelRec);
        textViewType = findViewById(R.id.textViewType);
        checkBoxEven = findViewById(R.id.checkBoxEven);
        checkBoxBor = findViewById(R.id.checkBoxBor);
        checkBoxInv = findViewById(R.id.checkBoxInv);
        checkBoxTheatr = findViewById(R.id.checkBoxTheatr);
        checkBoxClassic = findViewById(R.id.checkBoxClassic);
        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        editTextTextAddress = findViewById(R.id.editTextTextAddress);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);
        editTextTextMultiLine2 = findViewById(R.id.editTextTextMultiLine2);
        buttonSendRec = findViewById(R.id.buttonSendRec);
        spinnerValute = findViewById(R.id.spinnerValute);
        spinnerEquipChosse = findViewById(R.id.spinnerEquipChosse);

        buttonCancelRec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnOrderList(view);
            }
        });
        buttonSendRec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String selectedValute = spinnerValute.getSelectedItem().toString();
                String selectedEquip = spinnerEquipChosse.getSelectedItem().toString();
                if(checkBoxEven.isChecked()) {
                    choice = checkBoxEven.getText().toString();
                }
                else if(checkBoxBor.isChecked()) {
                    choice = checkBoxBor.getText().toString();
                }
                else if(checkBoxInv.isChecked()) {
                    choice = checkBoxInv.getText().toString();
                }
                else if(checkBoxTheatr.isChecked()) {
                    choice = checkBoxTheatr.getText().toString();
                }
                else {
                    choice = checkBoxClassic.getText().toString();
                }
                insertOrd(selectedValute, selectedEquip,currentLogin,choice);
                finish();
                backOnOrderList(view);
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countriesV);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerValute.setAdapter(adapter);

        ArrayAdapter<String> adapterV = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countriesE);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerEquipChosse.setAdapter(adapterV);

        AdapterView.OnItemSelectedListener itemSelectedListenerV = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerValute.setOnItemSelectedListener(itemSelectedListenerV);
        AdapterView.OnItemSelectedListener itemSelectedListenerE = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerEquipChosse.setOnItemSelectedListener(itemSelectedListenerE);
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
            Toast.makeText(WindowOrder.this, "Sorry. Error!", Toast.LENGTH_LONG).show();
        }
        }
        c.close();
        String type = textViewType.getText().toString().trim();
        String price = editTextNumberDecimal.getText().toString().trim();
        String valuta = selectedValute;
        String address = editTextTextAddress.getText().toString().trim();
        String equipment = selectedEquip;
        String data = editTextDate.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();
        String details = editTextTextMultiLine2.getText().toString().trim();


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

            Toast.makeText(WindowOrder.this, R.string.succes,Toast.LENGTH_LONG).show();
        }
    }
    public void backOnOrderList(View v){
        Intent intentOrderList = new Intent(this, ClientAccount.class);
        startActivity(intentOrderList);
    }
}