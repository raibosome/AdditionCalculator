package com.raimikarim.additioncalculator;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdditionActivity extends AppCompatActivity {

    private int adder = 0;
    private TextView sumBox, historyBox;
    private StringBuilder sb = new StringBuilder();
    private DecimalFormat decimalFormat = new DecimalFormat();
    private Button buttonBack;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button0;
    private Button buttonSave;
    private Button buttonCsv;
    private Button buttonClear;
    private Button buttonHelp;

    private ArrayList<Double> numberList = new ArrayList<>();
    private ArrayList<String> historyList = new ArrayList<>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        sumBox = findViewById(R.id.sumbox);
        sumBox.setText(decimalFormat.format(0));

        historyBox = findViewById(R.id.history_box);
        historyBox.setMovementMethod(new ScrollingMovementMethod());
        
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        button10 = findViewById(R.id.button_10);
        button0 = findViewById(R.id.button_0);
        buttonHelp = findViewById(R.id.button_help);

        buttonClear = findViewById(R.id.button_clear);
        buttonBack = findViewById(R.id.button_back);
        buttonSave = findViewById(R.id.button_save);
        buttonCsv = findViewById(R.id.button_csv);

        OnGestureRegisterListener onGestureRegisterListener = new OnGestureRegisterListener(this) {
            public void onSwipeRight(View view) {
                add10(view);
            }
            public void onSwipeLeft(View view) {
                minus10(view);
            }
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.button_1: numberList.add(adder + 1.0); break;
                    case R.id.button_2: numberList.add(adder + 2.0); break;
                    case R.id.button_3: numberList.add(adder + 3.0); break;
                    case R.id.button_4: numberList.add(adder + 4.0); break;
                    case R.id.button_5: numberList.add(adder + 5.0); break;
                    case R.id.button_6: numberList.add(adder + 6.0); break;
                    case R.id.button_7: numberList.add(adder + 7.0); break;
                    case R.id.button_8: numberList.add(adder + 8.0); break;
                    case R.id.button_9: numberList.add(adder + 9.0); break;
                    case R.id.button_10: numberList.add(adder + 10.0); break;
                    case R.id.button_0: numberList.add(0.0); break;

                    case R.id.button_save:
                        save(); break;
                    case R.id.button_csv:
                        rate(); break;

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
                    case R.id.button_help:
                        help(); return;
                }

                int len = numberList.size();
                String sn = Integer.toString(len) + ") ";
                if (len < 10)
                    sn = " " + sn;
                String str = "";

                // Print
                switch (v.getId()) {
                    case R.id.button_1: str = String.valueOf(1 + adder); break;
                    case R.id.button_2: str = String.valueOf(2 + adder); break;
                    case R.id.button_3: str = String.valueOf(3 + adder); break;
                    case R.id.button_4: str = String.valueOf(4 + adder); break;
                    case R.id.button_5: str = String.valueOf(5 + adder); break;
                    case R.id.button_6: str = String.valueOf(6 + adder); break;
                    case R.id.button_7: str = String.valueOf(7 + adder); break;
                    case R.id.button_8: str = String.valueOf(8 + adder); break;
                    case R.id.button_9: str = String.valueOf(9 + adder); break;
                    case R.id.button_10: str = String.valueOf(10 + adder); break;
                    case R.id.button_0: str = String.valueOf(0); break;

                    case R.id.button_clear:
                        historyList = new ArrayList<>();
                        break;
                    case R.id.button_back:
                        len = historyList.size();
                        if (len >= 1)
                            historyList.remove(len - 1);
                        break;
                }

                if (!(v.getId() == R.id.button_save || v.getId() == R.id.button_csv ||
                        v.getId() == R.id.button_clear || v.getId() == R.id.button_back ||
                        v.getId() == R.id.button_help))
                    historyList.add(sn + str + "\n");

                sumBox.setText(displaySum(numberList));
                historyBox.setText(displayHistory(historyList));

            }
            public boolean onLongClick(View view) {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                switch (view.getId()) {
                    case R.id.button_1: numberList.add(1.5 + adder); break;
                    case R.id.button_2: numberList.add(2.5 + adder); break;
                    case R.id.button_3: numberList.add(3.5 + adder); break;
                    case R.id.button_4: numberList.add(4.5 + adder); break;
                    case R.id.button_5: numberList.add(5.5 + adder); break;
                    case R.id.button_6: numberList.add(6.5 + adder); break;
                    case R.id.button_7: numberList.add(7.5 + adder); break;
                    case R.id.button_8: numberList.add(8.5 + adder); break;
                    case R.id.button_9: numberList.add(9.5 + adder); break;
                    case R.id.button_10: numberList.add(10.5 + adder); break;
                    case R.id.button_0: numberList.add(0.5); break;
                    case R.id.button_save:
                    case R.id.button_csv:
                    case R.id.button_help:
                    case R.id.button_clear:
                    case R.id.button_back: return true;
                }

                int len = numberList.size();
                String sn = Integer.toString(len) + ") ";
                if (len < 10)
                    sn = " " + sn;
                String str = "";

                switch (view.getId()) {
                    case R.id.button_clear: sb = new StringBuilder(); str = ""; break;
                    case R.id.button_1: str = String.valueOf(1.5 + adder); break;
                    case R.id.button_2: str = String.valueOf(2.5 + adder); break;
                    case R.id.button_3: str = String.valueOf(3.5 + adder); break;
                    case R.id.button_4: str = String.valueOf(4.5 + adder); break;
                    case R.id.button_5: str = String.valueOf(5.5 + adder); break;
                    case R.id.button_6: str = String.valueOf(6.5 + adder); break;
                    case R.id.button_7: str = String.valueOf(7.5 + adder); break;
                    case R.id.button_8: str = String.valueOf(8.5 + adder); break;
                    case R.id.button_9: str = String.valueOf(9.5 + adder); break;
                    case R.id.button_10: str = String.valueOf(10.5 + adder); break;
                    case R.id.button_0: str = String.valueOf(0.5); break;
                }

                if (!(view.getId() == R.id.button_save || view.getId() == R.id.button_csv ||
                        view.getId() == R.id.button_clear || view.getId() == R.id.button_back ||
                        view.getId() == R.id.button_help ))
                    historyList.add(sn + str + "\n");

                sumBox.setText(displaySum(numberList));
                historyBox.setText(displayHistory(historyList));

                return true;
            }
        };

        button1.setOnTouchListener(onGestureRegisterListener);
        button2.setOnTouchListener(onGestureRegisterListener);
        button3.setOnTouchListener(onGestureRegisterListener);
        button4.setOnTouchListener(onGestureRegisterListener);
        button5.setOnTouchListener(onGestureRegisterListener);
        button6.setOnTouchListener(onGestureRegisterListener);
        button7.setOnTouchListener(onGestureRegisterListener);
        button8.setOnTouchListener(onGestureRegisterListener);
        button9.setOnTouchListener(onGestureRegisterListener);
        button10.setOnTouchListener(onGestureRegisterListener);
        button0.setOnTouchListener(onGestureRegisterListener);

        buttonClear.setOnTouchListener(onGestureRegisterListener);
        buttonBack.setOnTouchListener(onGestureRegisterListener);
        buttonSave.setOnTouchListener(onGestureRegisterListener);
        buttonCsv.setOnTouchListener(onGestureRegisterListener);
        buttonHelp.setOnTouchListener(onGestureRegisterListener);
    }

    private void minus10(View v) {
        if (!(v.getId() == R.id.button_clear || v.getId() == R.id.button_back ||
                v.getId() == R.id.button_save || v.getId() == R.id.button_csv)) {
            if (adder != 0) {
                adder -= 10;
                buttonTextChange(adder);
            }
        }
    }

    private void add10(View v) {
        if (!(v.getId() == R.id.button_clear || v.getId() == R.id.button_back ||
                v.getId() == R.id.button_save || v.getId() == R.id.button_csv)) {
            if (adder <= 80)
                adder += 10;
            buttonTextChange(adder);
        }
    }

    public void buttonTextChange(int number) {
        button1.setText(String.valueOf(1 + number));
        button2.setText(String.valueOf(2 + number));
        button3.setText(String.valueOf(3 + number));
        button4.setText(String.valueOf(4 + number));
        button5.setText(String.valueOf(5 + number));
        button6.setText(String.valueOf(6 + number));
        button7.setText(String.valueOf(7 + number));
        button8.setText(String.valueOf(8 + number));
        button9.setText(String.valueOf(9 + number));
        button10.setText(String.valueOf(10 + number));
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

    public void save() {
        String body = historyBox.getText().toString().trim();
        body = body.replace("\n ","\n");
        if (body.isEmpty()) {
            Toast.makeText(this, "Nothing to save!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(intent, getResources().getString(R.string.save_to)));
        }
    }

    public void rate() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("market://details?id=com.raimikarim.additioncalculator"));
        startActivity(intent);
    }

    public void help() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Help");

        builder.setMessage("1) Tap to add\n" +
                "2) Swipe left/right to minus/plus 10\n" +
                "3) Long-press to add 0.5\n");

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNeutralButton("Privacy Policy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://raiboso.me/swift-grader/privacy-policy.html"));
                startActivity(intent);
            }
        });

        builder.create().show();
    }

}
