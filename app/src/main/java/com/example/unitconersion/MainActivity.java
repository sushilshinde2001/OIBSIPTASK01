package com.example.unitconersion;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText text1;
    private String selectedUnit,selectedType;
    private TextView textviewResult;
    TextView text2;
    Button btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1=findViewById(R.id.editTextNumber1);
        textviewResult=findViewById(R.id.textView);
        btn=findViewById(R.id.button);
        text2=findViewById(R.id.textview2);



        Spinner spinner_1=findViewById(R.id.spinner_1);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.Spinner_Array1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner_1.setAdapter(adapter);


        Spinner spinner_3=findViewById(R.id.spinner_3);
        ArrayAdapter<CharSequence> adapter3=ArrayAdapter.createFromResource(this,R.array.Spinner_Array3, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner_3.setAdapter(adapter3);

        spinner_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedType= adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUnit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where nothing is selected
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value=text1.getText().toString();
                if((!value.isEmpty()) && selectedType.equals("Length") && (selectedUnit.equals("Centimeter") || selectedUnit.equals("Meter"))){
                        performConversionM(value);
                } else if ((!value.isEmpty()) && selectedType.equals("Mass") && (selectedUnit.equals("Kilogram") || selectedUnit.equals("Gram")) ) {
                        performConversionK(value);
                }else if ( (!value.isEmpty()) && selectedType.equals("Mass") && (selectedUnit.equals("Centimeter") || selectedUnit.equals("Meter"))) {
                    Toast.makeText(MainActivity.this, "Please select the valid type from above Drop-Down option", Toast.LENGTH_SHORT).show();
                }else if ((!value.isEmpty()) && selectedType.equals("Length") && (selectedUnit.equals("Kilogram") || selectedUnit.equals("Gram"))) {
                    Toast.makeText(MainActivity.this, "Please select the valid type from above Drop-Down option", Toast.LENGTH_SHORT).show();
                }
                else  {
                    Toast.makeText(MainActivity.this, "Please give a valid input", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    private double result1;
    private void performConversionM(String input){
        if(selectedUnit!=null){
            double value=Double.parseDouble(input);

            switch(selectedUnit){
                case "Centimeter":
                    result1 = value /100;
                    text2.setText("Meter");
                    break;

                case "Meter":
                    result1= value*100;
                    text2.setText("Centimeter");
                    break;

            }

            textviewResult.setText(String.valueOf(result1));
        }
    }
    private double result;
    private void performConversionK(String input){
        if (selectedUnit != null){
            double value= Double.parseDouble(input);
            switch (selectedUnit){
                case "Kilogram":
                    result= value * 1000;
                    text2.setText("Gram");
                    break;
                case "Gram":
                    result=value/1000;
                    text2.setText("Kilogram");
            }
            textviewResult.setText(String.valueOf(result));

        }
    }


}