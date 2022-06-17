package com.example.beautyprogram.forClient;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyprogram.R;
import com.example.beautyprogram.database.OrdersBase;
import com.example.beautyprogram.database.WizardBase;
import com.example.beautyprogram.forWizard.WindowFreeOrder;

public class WizardProfileForClient extends AppCompatActivity {

    private TextView textViewWName3, textViewCity11, textViewStatus4, textViewField4, textViewNumber3, textViewSpec4;
    private ImageView imageViewW3;

    WizardBase wizardBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_profile_for_client);

        wizardBase = new WizardBase(this);
        String currentLogin = getIntent().getStringExtra("currentLogin");
        int currentOrder = getIntent().getIntExtra("idOrder", 0);
        textViewWName3 = findViewById(R.id.textViewWName3);
        textViewCity11 = findViewById(R.id.textViewCity11);
        textViewStatus4 = findViewById(R.id.textViewStatus4);
        textViewField4 = findViewById(R.id.textViewField4);
        textViewNumber3 = findViewById(R.id.textViewNumber3);
        textViewSpec4 = findViewById(R.id.textViewSpec4);
        imageViewW3 = findViewById(R.id.imageViewW3);
        imageViewW3.setImageResource(R.drawable.ic_menu_camera);

        SQLiteDatabase dbWiz = wizardBase.getWritableDatabase();

        String selectionO = WizardBase.KEY_ID+ "=?";
        String[] selectionArgsO = {""+currentOrder};

        String[] columns = {
                WizardBase.KEY_ID,
                WizardBase.KEY_NAME,
                WizardBase.KEY_CITY,
                WizardBase.KEY_ACTIVITY,
                WizardBase.KEY_SURNAME,
                WizardBase.KEY_MAIL,
                WizardBase.KEY_PHONE,
                WizardBase.KEY_LOGIN,
                WizardBase.KEY_PASSWORD,
                WizardBase.KEY_SPECIAL,
                WizardBase.KEY_STATUS,
                WizardBase.KEY_RANGE
        };
        Cursor c;
        c = dbWiz.query(WizardBase.DATABASE_NAME, columns, selectionO, selectionArgsO, null, null, null);
        int idWIndex = c.getColumnIndex(WizardBase.KEY_ID);
        int nameWIndex = c.getColumnIndex(WizardBase.KEY_NAME);
        int cityWIndex = c.getColumnIndex(WizardBase.KEY_CITY);
        int activWIndex = c.getColumnIndex(WizardBase.KEY_ACTIVITY);
        int surnameWIndex = c.getColumnIndex(WizardBase.KEY_SURNAME);
        int phoneWIndex = c.getColumnIndex(WizardBase.KEY_PHONE);
        int statusWIndex = c.getColumnIndex(WizardBase.KEY_STATUS);
        int specialWIndex = c.getColumnIndex(WizardBase.KEY_SPECIAL);
        while (c.moveToNext()) {
            if (c.getString(idWIndex).equals(""+currentOrder)) {
                textViewWName3.append(""+c.getString(nameWIndex)+ " " +c.getString(surnameWIndex));
                textViewCity11.append(""+c.getString(cityWIndex));
                textViewStatus4.append(""+c.getString(statusWIndex));
                textViewField4.append(""+c.getString(activWIndex));
                textViewNumber3.append(""+c.getString(phoneWIndex));
                textViewSpec4.append(""+c.getString(specialWIndex));
            }
            else {
                Toast.makeText(WizardProfileForClient.this, "Sorry. Error!", Toast.LENGTH_LONG).show();
            }
        }
        c.close();

  }
}