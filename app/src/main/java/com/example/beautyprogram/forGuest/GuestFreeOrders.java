package com.example.beautyprogram.forGuest;

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
import com.example.beautyprogram.database.OrdersBase;
import com.example.beautyprogram.forGuest.GuestAccount;
import com.example.beautyprogram.forWizard.WindowFreeOrder;

public class GuestFreeOrders extends AppCompatActivity {

    LinearLayout linGOrderList;
    private ImageButton imageButtonBack5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_free_orders);

        OrdersBase ordersBase;
        ordersBase = new OrdersBase(this);
        linGOrderList= findViewById(R.id.linGOrderList);
        imageButtonBack5 = findViewById(R.id.imageButtonBack5);
        imageButtonBack5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                backOnGuestAcc(view);
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
            btnNew.setBackgroundColor(Color.parseColor("#da87ed"));
            btnNew.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            btnNew.setId(Integer.parseInt(c.getString(ordIndex)));
            linGOrderList.addView(btnNew,layoutParams);
            btnNew.findViewById(btnNew.getId()).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    onFreeOrder(view,btnNew.getId());
                }
            });
        }
        c.close();
    }
    public void backOnGuestAcc(View v){
        Intent intentWizAcc = new Intent(this, GuestAccount.class);
        startActivity(intentWizAcc);
    }
    public void onFreeOrder(View v, int idOrder){
        Intent intentFreeOrder = new Intent(this, GuestFreeOrder.class);
        intentFreeOrder.putExtra("idOrder", idOrder);
        startActivity(intentFreeOrder);
    }
}