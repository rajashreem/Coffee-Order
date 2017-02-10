package com.example.android.placeorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view){
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view){
        quantity = quantity - 1;
        display(quantity);
    }

    public void submitOrder(View view) {
        int price = calculatePrice();
        printOrderSummary(price);
    }

    private int calculatePrice() {
        return quantity * 5;
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void printOrderSummary(int price) {
        String message = "Name : Rajashree Malvade\n" +
                "Quantity: " + quantity + "\n" +
                "Total: $" + price + "\nThank you!";

        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }
}