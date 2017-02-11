package com.example.android.placeorder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity == 99) {
            Toast.makeText(this, "You can not order more than 99 cups of coffee", Toast.LENGTH_SHORT).show();
        } else {
            quantity = quantity + 1;
            display(quantity);
        }
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You can not order less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
        } else {
            quantity = quantity - 1;
            display(quantity);
        }
    }

    public void submitOrder(View view) {
        EditText nameText = (EditText) findViewById(R.id.name_edit_text);
        String name = nameText.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String orderSummary = printOrderSummary(name, price, hasWhippedCream, hasChocolate);

        sendEmail(orderSummary);
    }

    private void sendEmail(String orderSummary) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your coffee order");
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = 5;

        if (hasWhippedCream) {
            basePrice += 1;
        }
        if (hasChocolate) {
            basePrice += 2;
        }

        return basePrice * quantity;
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private String printOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {
        String message = "Name : " + name + "\n" +
                "Add Whipped Cream? " + hasWhippedCream + "\n" +
                "Add Chocolate? " + hasChocolate + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: $" + price + "\nThank you!";

        return message;
    }
}