package com.example.convert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.models.Currency;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtFeature, txtSourceSymbol, txtSource;
    TextView txtResultSymbol, txtResult;
    TextView txtRate, txtLastUpdate;
    Spinner spnSourceCurrency, spnResultCurrency;
    Button btnPoint,btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnBS,btnCE;

    ArrayList<String> listSourceCurrency;
    ArrayAdapter<String> adapterSource;
    ArrayList<String> listResultCurrency;
    ArrayAdapter<String> adapterResult;

    View.OnClickListener eventWriteSourceAndCalculate;

    Currency usd = new Currency("USD", "$", 23442.50);
    Currency vnd = new Currency("VND", "₫", 1.0);
    Currency eur = new Currency("EUR", "€", 25623.29);
    Currency jpy = new Currency("JPY", "¥", 218.27);
    Currency rub = new Currency("RUB", "₽", 317.41);
    Currency currencySource = usd;
    Currency currencyResult = usd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        addControlls();
        addEvents();
    }

    private void calculate(){
        String source = txtSource.getText().toString();
        String result = String.valueOf((double)Math.round(Double.parseDouble(source)*currencySource.getRateTo(currencyResult)*1000)/1000);
        txtResult.setText(result);
    }

    // Xoá ký tự vừa nhập vào
    private String deleteAString(String source) {
        if(source.length()>1) {
            String temp = source.substring(0, source.length() - 1);
            return temp;
        }
        else if(source.length() == 1){
            return "0";
        }

        return source ;
    }
    private void writeSource(View v){
        String source = txtSource.getText().toString();

        switch (v.getId()) {
            case R.id.btnCE:
                source="0";
                break;
            // Xoá 1 ký tự vừa nhập
            case R.id.btnBS:
                String newNumber = deleteAString(source);
                source=newNumber;
                break;
            // Nối chuỗi các toán hạng và loại bỏ số 0 ở đầu toán hạng
            default:
                if(source.equals("0")){
                    source="";
                }
                source+=((Button)v).getText().toString();
                break;
        }

        txtSource.setText(source);
    }


    private void addEvents() {
        eventWriteSourceAndCalculate = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeSource(v);
                calculate();
            }
        };

        btnCE.setOnClickListener(eventWriteSourceAndCalculate);
        btnBS.setOnClickListener(eventWriteSourceAndCalculate);
        btn0.setOnClickListener(eventWriteSourceAndCalculate);
        btn1.setOnClickListener(eventWriteSourceAndCalculate);
        btn2.setOnClickListener(eventWriteSourceAndCalculate);
        btn3.setOnClickListener(eventWriteSourceAndCalculate);
        btn4.setOnClickListener(eventWriteSourceAndCalculate);
        btn5.setOnClickListener(eventWriteSourceAndCalculate);
        btn6.setOnClickListener(eventWriteSourceAndCalculate);
        btn7.setOnClickListener(eventWriteSourceAndCalculate);
        btn8.setOnClickListener(eventWriteSourceAndCalculate);
        btn9.setOnClickListener(eventWriteSourceAndCalculate);

        spnSourceCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        currencySource = usd;
                        break;
                    case 1:
                        currencySource = vnd;
                        break;
                    case 2:
                        currencySource = eur;
                        break;
                    case 3:
                        currencySource = jpy;
                        break;
                    default:
                        currencySource = rub;
                        break;
                }

                txtSourceSymbol.setText(currencySource.getSymbol());
                txtRate.setText("1 " + currencySource.getName() + " = " + currencySource.getRateTo(currencyResult) + " " + currencyResult.getName());

                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnResultCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        currencyResult = usd;
                        break;
                    case 1:
                        currencyResult = vnd;
                        break;
                    case 2:
                        currencyResult = eur;
                        break;
                    case 3:
                        currencyResult = jpy;
                        break;
                    default:
                        currencyResult = rub;
                        break;
                }

                txtResultSymbol.setText(currencyResult.getSymbol());
                txtRate.setText("1 " + currencySource.getName() + " = " + currencySource.getRateTo(currencyResult) + " " + currencyResult.getName());

                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addControlls() {
        txtFeature = findViewById(R.id.txtFeature);
        txtSourceSymbol = findViewById(R.id.txtSourceSymbol);
        txtSource = findViewById(R.id.txtSource);
        txtResultSymbol = findViewById(R.id.txtResultSymbol);
        txtResult = findViewById(R.id.txtResult);
        txtRate = findViewById(R.id.txtRate);
        txtLastUpdate = findViewById(R.id.txtLastUpdate);

        spnSourceCurrency = findViewById(R.id.spnSourceCurrency);
        listSourceCurrency= new ArrayList<>();
        listSourceCurrency.add("USD");
        listSourceCurrency.add("VND");
        listSourceCurrency.add("EUR");
        listSourceCurrency.add("JPY");
        listSourceCurrency.add("RUB");
        adapterSource = new ArrayAdapter<String>
                (MainActivity.this, android.R.layout.simple_spinner_item, listSourceCurrency);
        adapterSource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSourceCurrency.setAdapter(adapterSource);

        spnResultCurrency = findViewById(R.id.spnResultCurrency);
        listResultCurrency= new ArrayList<>();
        listResultCurrency.add("USD");
        listResultCurrency.add("VND");
        listResultCurrency.add("EUR");
        listResultCurrency.add("JPY");
        listResultCurrency.add("RUB");
        adapterResult = new ArrayAdapter<String>
                (MainActivity.this, android.R.layout.simple_spinner_item, listResultCurrency);
        adapterResult.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnResultCurrency.setAdapter(adapterResult);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnPoint = findViewById(R.id.btnPoint);
        btnBS = findViewById(R.id.btnBS);
        btnCE = findViewById(R.id.btnCE);
    }


}
