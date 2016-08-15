package com.example.jeffrey.investingcalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GrahamValuation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graham_valuation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get the references to the widgets
        final EditText tCurEps = (EditText) findViewById(R.id.curEps);
        final EditText tCurPrice = (EditText) findViewById(R.id.curPrice);
        final EditText tGrowth = (EditText) findViewById(R.id.growth);
        final EditText tYield = (EditText) findViewById(R.id.yield);
        final EditText tMarginSafety = (EditText) findViewById(R.id.marginSafety);
        final TextView vIntrinsicValue = (TextView) findViewById(R.id.intrinsicValue);

        findViewById(R.id.ib1).setOnClickListener(new View.OnClickListener() {

            // Logic for validation, input can't be empty
            @Override
            public void onClick(View v) {

                String strCurEps = tCurEps.getText().toString();
                String strCurPrice = tCurPrice.getText().toString();
                String strGrowth = tGrowth.getText().toString();
                String strYield = tYield.getText().toString();
                String strMarginSafety = tMarginSafety.getText().toString();

                if(TextUtils.isEmpty(strCurEps)){
                    tCurEps.setError("Please enter current EPS");
                    tCurEps.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(strCurPrice)){
                    tCurPrice.setError("Please enter current price");
                    tCurPrice.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(strGrowth)){
                    tGrowth.setError("Please enter growth.");
                    tGrowth.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(strYield)){
                    tYield.setError("Please enter Yield.");
                    tYield.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(strMarginSafety)){
                    tMarginSafety.setError("Please enter Margin of Safety.");
                    tMarginSafety.requestFocus();
                    return;
                }

                //Get the user values from the widget reference
                float fCurEps = Float.parseFloat(strCurEps);
                float fCurPrice = Float.parseFloat(strCurPrice);
                float fGrowth = Float.parseFloat(strGrowth);
                float fYield = Float.parseFloat(strYield );
                float fMarginSafety = Float.parseFloat(strMarginSafety);

                //Calculate intrinsic value
                // v = (fCurEps * (8.5 + (2 * fGrowth)) * 4.4) / fYield
                double fIntrinsicValue = (fCurEps * (8.5 + (2 * fGrowth)) * 4.4) / fYield;

                //Display result
                vIntrinsicValue.setText(String.valueOf(fIntrinsicValue));

                //Calculate and display buy below price
                double fBuyBelowPrice = (fCurEps * (8.5 + (2 * fGrowth)) * 4.4) / fYield;
            }
        });
    }
}
