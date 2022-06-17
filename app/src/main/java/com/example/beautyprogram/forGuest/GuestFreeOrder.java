package com.example.beautyprogram.forGuest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyprogram.R;
import com.example.beautyprogram.database.OrdersBase;
import com.example.beautyprogram.forWizard.WindowFreeOrder;

public class GuestFreeOrder extends AppCompatActivity {

    OrdersBase ordersBase;
    private TextView textViewCustomName3, textViewTypeofserv2, textViewDetails2, textViewEqui4, textViewSugPrice2,  textViewFDate2, textViewFTime2,textViewFAdres2;
    private ImageView imageView3;
    private Button buttonRespond3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_free_order);

        int currentOrder = getIntent().getIntExtra("idOrder", 0);
        textViewCustomName3 = findViewById(R.id.textViewCustomName3);
        buttonRespond3 = findViewById(R.id.buttonRespond3);
        textViewTypeofserv2 = findViewById(R.id.textViewTypeofserv2);
        textViewDetails2 = findViewById(R.id.textViewDetails2);
        textViewEqui4 = findViewById(R.id.textViewEqui4);
        textViewSugPrice2 = findViewById(R.id.textViewSugPrice2);
        textViewFDate2 = findViewById(R.id.textViewFDate2);
        textViewFTime2 = findViewById(R.id.textViewFTime2);
        textViewFAdres2 = findViewById(R.id.textViewFAdres2);
        imageView3 = findViewById(R.id.imageView3);
        imageView3.setImageResource(R.drawable.ic_menu_camera);
        SQLiteDatabase dbOrd = ordersBase.getWritableDatabase();

        String selectionO = OrdersBase.KEY_ID+ "=?";
        String[] selectionArgsO = {""+currentOrder};

        String[] columns = {
                OrdersBase.KEY_ID,
                OrdersBase.KEY_NAME,
                OrdersBase.KEY_TYPE_ORD,
                OrdersBase.KEY_CHECK,
                OrdersBase.KEY_PRICE,
                OrdersBase.KEY_VALUTA,
                OrdersBase.KEY_ADDRESS,
                OrdersBase.KEY_EQUIPMENT,
                OrdersBase.KEY_DATA,
                OrdersBase.KEY_TIME,
                OrdersBase.KEY_DETAILS
        };
        Cursor c;
        c = dbOrd.query(OrdersBase.DATABASE_NAME, columns, selectionO, selectionArgsO, null, null, null);
        int idCIndex = c.getColumnIndex(OrdersBase.KEY_ID);
        int nameCIndex = c.getColumnIndex(OrdersBase.KEY_NAME);
        int typeServIndex = c.getColumnIndex(OrdersBase.KEY_TYPE_ORD);
        int detCIndex = c.getColumnIndex(OrdersBase.KEY_DETAILS);
        int equipCIndex = c.getColumnIndex(OrdersBase.KEY_EQUIPMENT);
        int priceCIndex = c.getColumnIndex(OrdersBase.KEY_PRICE);
        int valuteCIndex = c.getColumnIndex(OrdersBase.KEY_VALUTA);
        int dateCIndex = c.getColumnIndex(OrdersBase.KEY_DATA);
        int timeCIndex = c.getColumnIndex(OrdersBase.KEY_TIME);
        int addressCIndex = c.getColumnIndex(OrdersBase.KEY_ADDRESS);
        while (c.moveToNext()) {
            if (c.getString(idCIndex).equals(""+currentOrder)) {
                textViewCustomName3.append(""+c.getString(nameCIndex));
                textViewTypeofserv2.append(""+c.getString(typeServIndex));
                textViewDetails2.append(""+c.getString(detCIndex));
                textViewEqui4.append(""+c.getString(equipCIndex));
                textViewSugPrice2.append(""+c.getString(priceCIndex) + " " + c.getString(valuteCIndex));
                textViewFDate2.append(""+c.getString(dateCIndex));
                textViewFTime2.append(""+c.getString(timeCIndex));
                textViewFAdres2.append(""+c.getString(addressCIndex));
            }
            else {
                Toast.makeText(GuestFreeOrder.this, "Sorry. Error!", Toast.LENGTH_LONG).show();
            }
        }
        c.close();
        buttonRespond3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(GuestFreeOrder.this, "Sorry, for this you need to be registered.", Toast.LENGTH_LONG).show();
            }
        });
    }
}