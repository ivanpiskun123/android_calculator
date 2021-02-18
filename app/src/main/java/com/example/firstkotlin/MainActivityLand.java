package com.example.firstkotlin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivityLand extends AppCompatActivity {

    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6,btn_7,btn_8,btn_9,btn_dot,btn_e, btn_left_br,btn_clear_all, btn_right_br, btn_xy,btn_clear;
    Button btn_rotate, btn_square, btn_sq_root, btn_sin, btn_cos, btn_devide, btn_tg, btn_pi, btn_equal, btn_plus, btn_minus, btn_multi, btn_sign, btn_log;
    TextView outView;
    OperationsMaker operationsMaker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_land);

        operationsMaker = new OperationsMaker();

        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);

        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_devide = findViewById(R.id.btn_devide);
        btn_multi = findViewById(R.id.btn_multi);

        btn_dot = findViewById(R.id.btn_dot );
        btn_e = findViewById(R.id.btn_e);
        btn_left_br = findViewById(R.id.btn_left_br);
        btn_clear_all = findViewById(R.id.btn_clear_all);
        btn_right_br = findViewById(R.id.btn_right_br);
        btn_xy = findViewById(R.id.btn_xy);
        btn_clear = findViewById(R.id.btn_clear);

        btn_rotate = findViewById(R.id.btn_rotate);
        btn_square = findViewById(R.id.btn_square);
        btn_sq_root = findViewById(R.id.btn_sq_root);
        btn_sin = findViewById(R.id.btn_sin);
        btn_cos = findViewById(R.id.btn_cos);
        btn_log = findViewById(R.id.btn_log);

        btn_tg = findViewById(R.id.btn_log);
        btn_pi= findViewById(R.id.btn_pi);
        btn_equal = findViewById(R.id.btn_equal);
        outView = findViewById(R.id.outView);
        btn_sign = findViewById(R.id.btn_sign);

        outView.setText(getIntent().getStringExtra("DIG_ON_SCREEN"));

        initializeButtons();
    }

    private void initializeButtons(){
        initializeSigns();
        initializeOperatesWithOutView();

    }

    private void initializeSigns(){
        Button[] btns_digits = {btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6,btn_7,btn_8,btn_9};
        Button[] btns_funcs = {btn_sin, btn_cos, btn_log};
        Button[] btns_signs = {btn_plus, btn_minus, btn_multi, btn_devide, btn_dot, btn_left_br, btn_right_br};
        Button[] btns_const = {btn_pi, btn_e};
        String[] strs_digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] strs_funcs = {"sin(", "cos(", "log("};
        String[] strs_signs = {"+", "-", "*", "/", ".", "(", ")"};
        String[] strs_const = {"π", "e"};

        setOnClickAddToOutViewHandlers(btns_digits, strs_digits);
        setOnClickAddToOutViewHandlers(btns_funcs, strs_funcs);
        setOnClickAddToOutViewHandlers(btns_signs ,strs_signs );
        setOnClickAddToOutViewHandlers(btns_const, strs_const);
    }

    private void setOnClickAddToOutViewHandlers(Button[] btns, String[] strs){
        for(int i = 0; i < btns.length; ++i){
            int finalI = i;
            btns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(outView.getText().toString().equals("Error"))
                        outView.setText("");
                    outView.setText(outView.getText()+strs[finalI]);
                }
            });
        }
    }

    private void initializeOperatesWithOutView(){
            btn_clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    outView.setText(outView.getText().toString().substring(0,outView.getText().toString().length()-1));
                }
            });

            btn_clear_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    outView.setText("");
                }
            });

            btn_equal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    outView.setText(operationsMaker.calculate(outView.getText().toString()));
                }
            });

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("DIG_ON_SCREEN", operationsMaker.calculate(outView.getText().toString()));
            startActivity(intent);
        }



    }
}