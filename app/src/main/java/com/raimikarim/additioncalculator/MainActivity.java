package com.raimikarim.additioncalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener, View.OnLongClickListener {

    private int adder = 0;
    private TextView sumBox, historyBox;
    private StringBuilder sb = new StringBuilder();
    private DecimalFormat decimalFormat = new DecimalFormat();
    private Button buttonClear, buttonBack, buttonA, buttonB, buttonC, buttonD, buttonE, buttonF, buttonG, buttonH,
            buttonI, buttonJ, buttonK, buttonInc, buttonDec;

    private ArrayList<Double> numberList = new ArrayList<>();
    private ArrayList<String> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumBox = findViewById(R.id.sumbox);
        sumBox.setText(decimalFormat.format(0));

        historyBox = findViewById(R.id.history_box);
        historyBox.setMovementMethod(new ScrollingMovementMethod());

        buttonClear = findViewById(R.id.button_clear);
        buttonBack = findViewById(R.id.button_back);
        buttonA = findViewById(R.id.button_a);
        buttonB = findViewById(R.id.button_b);
        buttonC = findViewById(R.id.button_c);
        buttonD = findViewById(R.id.button_d);
        buttonE = findViewById(R.id.button_e);
        buttonF = findViewById(R.id.button_f);
        buttonG = findViewById(R.id.button_g);
        buttonH = findViewById(R.id.button_h);
        buttonI = findViewById(R.id.button_i);
        buttonJ = findViewById(R.id.button_j);
        buttonK = findViewById(R.id.button_k);
        buttonInc = findViewById(R.id.button_inc);
        buttonDec = findViewById(R.id.button_dec);

        buttonClear.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        buttonE.setOnClickListener(this);
        buttonF.setOnClickListener(this);
        buttonG.setOnClickListener(this);
        buttonH.setOnClickListener(this);
        buttonI.setOnClickListener(this);
        buttonJ.setOnClickListener(this);
        buttonK.setOnClickListener(this);
        buttonInc.setOnClickListener(this);
        buttonDec.setOnClickListener(this);

        buttonA.setOnLongClickListener(this);
        buttonB.setOnLongClickListener(this);
        buttonC.setOnLongClickListener(this);
        buttonD.setOnLongClickListener(this);
        buttonE.setOnLongClickListener(this);
        buttonF.setOnLongClickListener(this);
        buttonG.setOnLongClickListener(this);
        buttonH.setOnLongClickListener(this);
        buttonI.setOnLongClickListener(this);
        buttonJ.setOnLongClickListener(this);
        buttonK.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_a: numberList.add(adder + 1.0); break;
            case R.id.button_b: numberList.add(adder + 2.0); break;
            case R.id.button_c: numberList.add(adder + 3.0); break;
            case R.id.button_d: numberList.add(adder + 4.0); break;
            case R.id.button_e: numberList.add(adder + 5.0); break;
            case R.id.button_f: numberList.add(adder + 6.0); break;
            case R.id.button_g: numberList.add(adder + 7.0); break;
            case R.id.button_h: numberList.add(adder + 8.0); break;
            case R.id.button_i: numberList.add(adder + 9.0); break;
            case R.id.button_j: numberList.add(adder + 10.0); break;
            case R.id.button_k: numberList.add(0.0); break;

            case R.id.button_inc:
                if (adder <= 80)
                    adder += 10;
                buttonTextChange(adder); break;
            case R.id.button_dec:
                if (adder != 0)
                    adder -= 10;
                buttonTextChange(adder); break;

            case R.id.button_clear:
                numberList = new ArrayList<>();
                adder = 0;
                buttonTextChange(0);
                break;
            case R.id.button_back:
                int len = numberList.size();
                if (len >= 1)
                    numberList.remove(len - 1);
                break;
        }

        int len = numberList.size();
        String sn = Integer.toString(len) + ") ";
        if (len < 10)
            sn = " " + sn;
        String str = "";

        // Print
        switch (v.getId()) {

            case R.id.button_a: str = String.valueOf(1 + adder); break;
            case R.id.button_b: str = String.valueOf(2 + adder); break;
            case R.id.button_c: str = String.valueOf(3 + adder); break;
            case R.id.button_d: str = String.valueOf(4 + adder); break;
            case R.id.button_e: str = String.valueOf(5 + adder); break;
            case R.id.button_f: str = String.valueOf(6 + adder); break;
            case R.id.button_g: str = String.valueOf(7 + adder); break;
            case R.id.button_h: str = String.valueOf(8 + adder); break;
            case R.id.button_i: str = String.valueOf(9 + adder); break;
            case R.id.button_j: str = String.valueOf(10 + adder); break;
            case R.id.button_k: str = String.valueOf(0); break;

            case R.id.button_clear:
                historyList = new ArrayList<>();
                break;
            case R.id.button_back:
                len = historyList.size();
                if (len >= 1)
                    historyList.remove(len - 1);
                break;
        }

        if (!(v.getId() == R.id.button_inc || v.getId() == R.id.button_dec ||
                v.getId() == R.id.button_clear || v.getId() == R.id.button_back ))
            historyList.add(sn + str + "\n");

        // Update views
        sumBox.setText(displaySum(numberList));
        historyBox.setText(displayHistory(historyList));
    }

    @Override
    public boolean onLongClick(View view) {

        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

        switch (view.getId()) {
            case R.id.button_a: numberList.add(1.5 + adder); break;
            case R.id.button_b: numberList.add(2.5 + adder); break;
            case R.id.button_c: numberList.add(3.5 + adder); break;
            case R.id.button_d: numberList.add(4.5 + adder); break;
            case R.id.button_e: numberList.add(5.5 + adder); break;
            case R.id.button_f: numberList.add(6.5 + adder); break;
            case R.id.button_g: numberList.add(7.5 + adder); break;
            case R.id.button_h: numberList.add(8.5 + adder); break;
            case R.id.button_i: numberList.add(9.5 + adder); break;
            case R.id.button_j: numberList.add(10.5 + adder); break;
            case R.id.button_k: numberList.add(0.5); break;
        }

        int len = numberList.size();
        String sn = Integer.toString(len) + ") ";
        if (len < 10)
            sn = " " + sn;
        String str = "";

        switch (view.getId()) {
            case R.id.button_clear: sb = new StringBuilder(); str = ""; break;
            case R.id.button_a: str = String.valueOf(1.5 + adder); break;
            case R.id.button_b: str = String.valueOf(2.5 + adder); break;
            case R.id.button_c: str = String.valueOf(3.5 + adder); break;
            case R.id.button_d: str = String.valueOf(4.5 + adder); break;
            case R.id.button_e: str = String.valueOf(5.5 + adder); break;
            case R.id.button_f: str = String.valueOf(6.5 + adder); break;
            case R.id.button_g: str = String.valueOf(7.5 + adder); break;
            case R.id.button_h: str = String.valueOf(8.5 + adder); break;
            case R.id.button_i: str = String.valueOf(9.5 + adder); break;
            case R.id.button_j: str = String.valueOf(10.5 + adder); break;
            case R.id.button_k: str = String.valueOf(0.5); break;
        }

        if (!(view.getId() == R.id.button_inc || view.getId() == R.id.button_dec ||
                view.getId() == R.id.button_clear || view.getId() == R.id.button_back ))
            historyList.add(sn + str + "\n");

        // Update views
        sumBox.setText(displaySum(numberList));
        historyBox.setText(displayHistory(historyList));

        return true;
    }

    public void buttonTextChange(int number) {
        buttonA.setText(String.valueOf(1 + number));
        buttonB.setText(String.valueOf(2 + number));
        buttonC.setText(String.valueOf(3 + number));
        buttonD.setText(String.valueOf(4 + number));
        buttonE.setText(String.valueOf(5 + number));
        buttonF.setText(String.valueOf(6 + number));
        buttonG.setText(String.valueOf(7 + number));
        buttonH.setText(String.valueOf(8 + number));
        buttonI.setText(String.valueOf(9 + number));
        buttonJ.setText(String.valueOf(10 + number));
    }

    public String displaySum(ArrayList<Double> list) {
        double sum = 0;
        for (Double d : list) {
            sum += d;
        }
        return decimalFormat.format(sum);
    }

    public String displayHistory(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder("");
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }
}
