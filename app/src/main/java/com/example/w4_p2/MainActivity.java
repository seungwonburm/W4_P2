package com.example.w4_p2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText dollar;
    TextView euro, won, yen, rupi;
    GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gd = new GestureDetector(this, new Gestures());

        dollar = (EditText) findViewById(R.id.dollar);
        euro = (TextView) findViewById(R.id.euro);
        won = (TextView) findViewById(R.id.won);
        yen = (TextView) findViewById(R.id.yen);
        rupi = (TextView) findViewById(R.id.rupi);

        euro.setText("European Euro");
        won.setText("Korean Won");
        yen.setText("Japanese Yen");
        rupi.setText("Indian Rupi");

        EditText editText = (EditText)findViewById(R.id.dollar);
        DecimalFormat df = new DecimalFormat("0.##");





        dollar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                String input = dollar.getText().toString();


                try {
                    double number = Double.parseDouble(input);
                    euro.setText(df.format(number*1.02));
                    won.setText(df.format(number*1400));
                    yen.setText(df.format(number*144.74));
                    rupi.setText(df.format(number*81.64));
                    if (input.contains("-")){
                        dollar.setText("0");
                    }
                    if(input.startsWith("0")){
                        input.replace("0", number+"");
                    }

                } catch(NumberFormatException e) {
                    if (input.equals(".")){
                        dollar.setText("0.");
                    }
                    if (input.contains("-")){
                        dollar.setText("0");

                    }if (input.equals("")){
                        euro.setText("European Euro");
                        won.setText("Korean Won");
                        yen.setText("Japanese Yen");
                        rupi.setText("Indian Rupi");
                    }

                    editText.setSelection(editText.getText().length());





                } catch(NullPointerException e) {
                    dollar.setText(df.format(0));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    private class Gestures extends GestureDetector.SimpleOnGestureListener {
        DecimalFormat df = new DecimalFormat("0.##");

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float v, float v1) {

            try {
                double number = Double.parseDouble(dollar.getText().toString());

                if (Math.abs(event1.getY()-event2.getY()) > 90){
                    if (event1.getY() - event2.getY() > 40) {
                        number += 1;
                    } else if (event2.getY() - event1.getY() > 40) {
                        if (number >= 1) {
                            number -= 1;
                        }

                    }
                    Toast.makeText(getApplicationContext(), "Fling", Toast.LENGTH_LONG).show();

                }

                dollar.setText(df.format(number));

            } catch (NumberFormatException e) {

                dollar.setText(df.format(0));

            } catch (NullPointerException e) {
                dollar.setText(df.format(0));
            }
            return super.onFling(event1, event2, v, v1);
        }


        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float v, float v1) {
            try {

                double number = Double.parseDouble(dollar.getText().toString());


                if (Math.abs(event1.getY()-event2.getY()) <= 90){
                    if (event1.getY() - event2.getY() <= 40) {
                        number += 0.1;
                    } else if (event2.getY() - event1.getY() <= 40) {
                        if (number >= 0.1) {
                            number -= 0.1;
                        }

                    }
                    Toast.makeText(getApplicationContext(), "Scroll", Toast.LENGTH_LONG).show();

                }
                dollar.setText(df.format(number));


            } catch (NumberFormatException e) {

                dollar.setText(df.format(0));

            } catch (NullPointerException e) {
                dollar.setText(df.format(0));
            }


            return super.onScroll(event1, event2, v, v1);
        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gd.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


}