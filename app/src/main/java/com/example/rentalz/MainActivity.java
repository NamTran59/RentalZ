package com.example.rentalz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText date;
    EditText price;
    EditText reportName;

    Spinner spinnerProperty;
    Spinner spinnerBedroom;
    Spinner spinnerFurniture;

    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitButtonClicked();
            }
        });

        // bind to layout item
        date = findViewById(R.id.date);
        price = findViewById(R.id.price);
        reportName = findViewById(R.id.reporter_name);

        // set current date to date field
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = formatter.format(date);
        this.date.setText(currentDate);

        // bind to layout item
        spinnerProperty = findViewById(R.id.property_spinner);
        spinnerBedroom = findViewById(R.id.bedroom_spinner);
        spinnerFurniture = findViewById(R.id.furniture_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> propertyAdapter = ArrayAdapter.createFromResource(this,
                R.array.property_list, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> bedroomAdapter = ArrayAdapter.createFromResource(this,
                R.array.bedroom_list, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> furnitureAdapter = ArrayAdapter.createFromResource(this,
                R.array.furniture_list, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        propertyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bedroomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        furnitureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerProperty.setAdapter(propertyAdapter);
        spinnerBedroom.setAdapter(bedroomAdapter);
        spinnerFurniture.setAdapter(furnitureAdapter);
    }

    private void submitButtonClicked() {

        // get text from Spinner item
        String property = spinnerProperty.getSelectedItem().toString();
        String bedroom = spinnerBedroom.getSelectedItem().toString();

        // get text from EditText item
        String date = this.date.getText().toString();
        String price = this.price.getText().toString();
        String reporter_name = this.reportName.getText().toString();

        // check if the value has input
        boolean propertyStatus = property.equals("Select an item");
        boolean bedroomStatus = bedroom.equals("Select an item");
        boolean dateStatus = date.trim().isEmpty();
        boolean priceStatus = price.trim().isEmpty();
        boolean reporterNameStatus = reporter_name.trim().isEmpty();

        if (propertyStatus || bedroomStatus || dateStatus || priceStatus || reporterNameStatus) {
            Toast submitToast = Toast.makeText(MainActivity.this, "Missing in required fields!", Toast.LENGTH_SHORT);
            submitToast.show();

            // get text view from spinner
            TextView propertyError = (TextView) spinnerProperty.getSelectedView();
            TextView bedroomError = (TextView) spinnerBedroom.getSelectedView();

            if (propertyStatus) {
                propertyError.setError("Please select a property type");
            }
            if (bedroomStatus) {
                bedroomError.setError("Please select a bedroom type");
            }
            if (priceStatus) {
                this.price.setError("Please enter a price");
            }
            if (reporterNameStatus) {
                this.reportName.setError("Please enter a reporter's name");
            }
        }

        else {
            Toast submitToast = Toast.makeText(MainActivity.this, "Property submitted!", Toast.LENGTH_SHORT);
            submitToast.show();
        }
    }
}