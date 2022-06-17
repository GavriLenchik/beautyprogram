package com.example.beautyprogram.forWizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyprogram.R;
import com.example.beautyprogram.database.OrdersBase;
import com.example.beautyprogram.database.WizardBase;

public class WizardProfile extends AppCompatActivity {
    String[] countries = { "Odessa", "Kyiv", "Lviv", "Mykolayiv", "Kharkiv", "Kherson"};
    String[] countriesActiv = { "Visagiste", "Make-up master", "Manicurist", "Masseur"};

    WizardBase wizardBase;

    private Spinner spinnerCity, spinnerActivity;
    private TextView textViewWName,textViewNumber,textViewStatus2;
    private ImageView imageViewW;
    private EditText editTextTextMultiLineSpec;
    private Button buttonUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_profile);


        String currentLogin = getIntent().getStringExtra("currentLogin");
        wizardBase=new WizardBase(this);
        textViewWName =(TextView)findViewById(R.id.textViewWName);
        textViewNumber =(TextView)findViewById(R.id.textViewNumber);
        spinnerActivity =findViewById(R.id.spinnerActivity);
        buttonUpdate =findViewById(R.id.buttonUpdate);
        imageViewW = findViewById(R.id.imageViewW);
        spinnerCity = findViewById(R.id.spinnerCity);
        textViewStatus2 =(TextView)findViewById(R.id.textViewStatus2);
        editTextTextMultiLineSpec =findViewById(R.id.editTextTextMultiLineSpec);
        imageViewW.setImageResource(R.drawable.ic_menu_camera);


        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countries);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerCity.setAdapter(adapter);
        ArrayAdapter<String> adapterAct = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countriesActiv);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerActivity.setAdapter(adapterAct);
        String selectedCity = spinnerCity.getSelectedItem().toString();
        String selectedActiv = spinnerActivity.getSelectedItem().toString();
        SQLiteDatabase dbWiz = wizardBase.getWritableDatabase();

        String[] projectionW = {
                WizardBase.KEY_ID,
                WizardBase.KEY_NAME,
                WizardBase.KEY_SURNAME,
                WizardBase.KEY_MAIL,
                WizardBase.KEY_LOGIN,
                WizardBase.KEY_PASSWORD,
                WizardBase.KEY_PHONE,
                WizardBase.KEY_CITY,
                WizardBase.KEY_ACTIVITY,
                WizardBase.KEY_RANGE,
                WizardBase.KEY_SPECIAL,
                WizardBase.KEY_STATUS
        };
        String selectionW = WizardBase.KEY_LOGIN + "=?";
        String[] selectionArgsW = {currentLogin};

        Cursor c;
        c = dbWiz.query(WizardBase.DATABASE_NAME, projectionW, selectionW, selectionArgsW, null, null, null);
        int nameWIndex = c.getColumnIndex(WizardBase.KEY_NAME);
        int surnameWIndex = c.getColumnIndex(WizardBase.KEY_SURNAME);
        int phoneWIndex = c.getColumnIndex(WizardBase.KEY_PHONE);
        int statusWIndex = c.getColumnIndex(WizardBase.KEY_STATUS);
        int loginWIndex = c.getColumnIndex(WizardBase.KEY_LOGIN);
        int cityWIndex = c.getColumnIndex(WizardBase.KEY_CITY);
        int activWIndex = c.getColumnIndex(WizardBase.KEY_ACTIVITY);
        int specialWIndex = c.getColumnIndex(WizardBase.KEY_SPECIAL);
        while (c.moveToNext()) {
            if (c.getString(loginWIndex).equals(currentLogin)) {
                textViewWName.append("" + c.getString(nameWIndex) + " " + c.getString(surnameWIndex));
                textViewNumber.append("" + c.getString(phoneWIndex));
                textViewStatus2.append("" + c.getString(statusWIndex));
                buttonUpdate.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(WizardBase.KEY_CITY, selectedCity);
                        contentValues.put(WizardBase.KEY_ACTIVITY, selectedActiv);
                        contentValues.put(WizardBase.KEY_SPECIAL, editTextTextMultiLineSpec.getText().toString());
                    }
                });
            } else {
                Toast.makeText(WizardProfile.this, "Sorry. Error!", Toast.LENGTH_LONG).show();
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
        spinnerCity.setOnItemSelectedListener(itemSelectedListener);
        AdapterView.OnItemSelectedListener itemSelectedListenerA = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerActivity.setOnItemSelectedListener(itemSelectedListenerA);

    }

}
