package com.example.beautyprogram.forWizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.beautyprogram.R;
import com.example.beautyprogram.database.OrdersBase;
import com.example.beautyprogram.database.WizardBase;
import com.example.beautyprogram.forWizard.WizardAccount;

public class WizardFreeOrders extends AppCompatActivity {

    LinearLayout linOrderList;
    private ImageButton imageButtonBack2;
    private Button buttonUrgent, buttonAll;
    OrdersBase ordersBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard_free_orders);

        String currentLogin = getIntent().getStringExtra("currentLogin");
        ordersBase = new OrdersBase(this);
        imageButtonBack2 = findViewById(R.id.imageButtonBack2);
        linOrderList = findViewById(R.id.linOrderList);
        buttonUrgent = findViewById(R.id.buttonUrgent);
        buttonAll = findViewById(R.id.buttonAll);

        imageButtonBack2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnWizAcc(view);
            }
        });
        SQLiteDatabase dbOrd = ordersBase.getWritableDatabase();

                String[] columns = {OrdersBase.KEY_ID,
                        OrdersBase.KEY_NAME,
                        OrdersBase.KEY_TYPE_ORD,
                        OrdersBase.KEY_CHECK,
                        OrdersBase.KEY_PRICE,
                        OrdersBase.KEY_VALUTA,
                        OrdersBase.KEY_ADDRESS,
                        OrdersBase.KEY_EQUIPMENT,
                        OrdersBase.KEY_DATA,
                        OrdersBase.KEY_DETAILS
                };

                Cursor c;
                c = dbOrd.query(OrdersBase.DATABASE_NAME, columns, null, null, null, null, null);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

                int ordIndex = c.getColumnIndex(OrdersBase.KEY_ID);
                int typeIndex = c.getColumnIndex(OrdersBase.KEY_TYPE_ORD);
                while (c.moveToNext()) {
                    Button btnNew = new Button(this);
                    btnNew.setText(c.getString(typeIndex));
                    btnNew.setBackgroundColor(Color.parseColor("#ebaed9"));
                    btnNew.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                    btnNew.setId(Integer.parseInt(c.getString(ordIndex)));
                    linOrderList.addView(btnNew,layoutParams);
                    btnNew.findViewById(btnNew.getId()).setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            onFreeOrder(view,btnNew.getId(),currentLogin);
                        }
                    });
                }
                c.close();


    }
    public void backOnWizAcc(View v){
        Intent intentWizAcc = new Intent(this, WizardAccount.class);
        startActivity(intentWizAcc);
    }
    public void onFreeOrder(View v, int idOrder, String currentLogin){
        Intent intentFreeOrder = new Intent(this, WindowFreeOrder.class);
        intentFreeOrder.putExtra("idOrder", idOrder);
        intentFreeOrder.putExtra("currentLogin", currentLogin);
        startActivity(intentFreeOrder);
    }


}