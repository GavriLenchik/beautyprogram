package com.example.beautyprogram.forWizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyprogram.R;
import com.example.beautyprogram.database.ClientBase;
import com.example.beautyprogram.database.FeedbackBaze;
import com.example.beautyprogram.database.OrdersBase;
import com.example.beautyprogram.database.WizardBase;
import com.example.beautyprogram.forClient.ClientProfile;
import com.example.beautyprogram.forClient.FeedbackList;
import com.example.beautyprogram.forClient.WindowOrder;

public class WindowFreeOrder extends AppCompatActivity {

    OrdersBase ordersBase;
FeedbackBaze feedbackBaze;
    private ImageButton imageButtonEditPrice, imageButtonCard;
    private TextView textViewCustomName2, textViewTypeofserv, textViewDetails, textViewEqui, textViewSugPrice, textViewFDate, textViewFTime, textViewFAdres;
    private Button buttonPrevRec2, buttonRespond, buttonNextRec2;
    private ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_free_order);


        ordersBase = new OrdersBase(this);
        feedbackBaze = new FeedbackBaze(this);
        String currentLogin = getIntent().getStringExtra("currentLogin");
        int currentOrder = getIntent().getIntExtra("idOrder", 0);
        imageButtonEditPrice = findViewById(R.id.imageButtonEditPrice);
        imageButtonCard = findViewById(R.id.imageButtonCard);
        textViewCustomName2 = findViewById(R.id.textViewCustomName2);
        textViewTypeofserv = findViewById(R.id.textViewTypeofserv);
        textViewDetails = findViewById(R.id.textViewDetails);
        textViewEqui = findViewById(R.id.textViewEqui);
        textViewSugPrice = findViewById(R.id.textViewSugPrice);
        textViewFDate = findViewById(R.id.textViewFDate);
        textViewFTime = findViewById(R.id.textViewFTime);
        textViewFAdres = findViewById(R.id.textViewFAdres);
        buttonPrevRec2 = findViewById(R.id.buttonPrevRec2);
        buttonRespond = findViewById(R.id.buttonRespond);
        buttonNextRec2 = findViewById(R.id.buttonNextRec2);
        imageView2 = findViewById(R.id.imageView2);
        imageView2.setImageResource(R.drawable.ic_menu_camera);


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
                textViewCustomName2.append(""+c.getString(nameCIndex));
                textViewTypeofserv.append(""+c.getString(typeServIndex));
                textViewDetails.append(""+c.getString(detCIndex));
                textViewEqui.append(""+c.getString(equipCIndex));
                textViewSugPrice.append(""+c.getString(priceCIndex) + " " + c.getString(valuteCIndex));
                textViewFDate.append(""+c.getString(dateCIndex));
                textViewFTime.append(""+c.getString(timeCIndex));
                textViewFAdres.append(""+c.getString(addressCIndex));

            }
            else {
                Toast.makeText(WindowFreeOrder.this, "Sorry. Error!", Toast.LENGTH_LONG).show();
            }
        }
        c.close();
        buttonRespond.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SQLiteDatabase dbfeed = feedbackBaze.getWritableDatabase();
                Toast.makeText(WindowFreeOrder.this, "Your response has been sent successfully", Toast.LENGTH_LONG).show();
                String selectionF = FeedbackBaze.KEY_TOLOGIN + "=?";
                String[] selectionArgsF = {OrdersBase.KEY_LOGINFROM};
                String[] columsF = {
                        FeedbackBaze.KEY_ID,
                        FeedbackBaze.KEY_FROMLOGIN,
                        FeedbackBaze.KEY_TOLOGIN
                };
                Cursor cF;
                cF = dbfeed.query(FeedbackBaze.DATABASE_NAME, columsF, selectionF, selectionArgsF, null, null, null);
                ContentValues contentValues = new ContentValues();
                int loginToCIndex = cF.getColumnIndex(FeedbackBaze.KEY_TOLOGIN);
                int loginFromCIndex = cF.getColumnIndex(FeedbackBaze.KEY_FROMLOGIN);
                while (cF.moveToNext()) {
                    contentValues.put(FeedbackBaze.KEY_FROMLOGIN, currentLogin);
                }
                backOnClOrd(view, currentLogin);
            }
        });
    }
    public void backOnClOrd(View v, String currentLogin){

        Intent intentWFreeOrd = new Intent(this, WizardFreeOrders.class);
        startActivity(intentWFreeOrd);
    }

}