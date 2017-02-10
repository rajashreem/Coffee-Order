package com.example.android.placeorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
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

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        printOrderSummary(price, hasWhippedCream);
    }

    private int calculatePrice() {
        return quantity * 5;
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void printOrderSummary(int price, boolean hasWhippedCream) {
        String message = "Name : Rajashree Malvade\n" +
                "Add Whipped Cream? " + hasWhippedCream + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: $" + price + "\nThank you!";

        displayMessage(message);
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }
}