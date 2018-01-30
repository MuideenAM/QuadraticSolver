package com.example.android.quadraticsolver;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
          Variables declaration:
          aVal is final cos it will be accessed within a subclass.
          aValue, bValue and cValue store the values of  variables a, b and c respectively.
          message stores corresponding message depending on the entry point to this activity.
         */
        final EditText aVal = findViewById(R.id.a_value);
        String aValue,
                bValue = "",
                cValue = "",
                message = "";

        /*
          Declare extras bundle to store data to passed on to the next activity.
         */
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            aValue = extras.getString("A_VALUE");
            bValue = extras.getString("B_VALUE");
            cValue = extras.getString("C_VALUE");

            //Display the value of a carried over when previous button is clicked in CoefficientOfX
            // activity.
            aVal.setText(aValue);
            aVal.selectAll();

            if(!Objects.equals(bValue, "") && !Objects.equals(cValue, "")){
                message = "Coefficient of x (" + bValue + ") and\nConstant term (" + cValue
                        + ") are carried over.";
            } else if(!Objects.equals(bValue, "")){
                message = "Coefficient of x (" + bValue + ") is carried over.";
            }else if(!Objects.equals(cValue, "")){
                message = "Constant term (" + cValue + ") is carried over.";
            }
            Toast.makeText(getApplicationContext(),
                    message,
                    Toast.LENGTH_LONG).show();
        }
        final String bVal = bValue,
                cVal = cValue;

        /*
          Set a click listener for the Next button.
          button1 is final cos it will be accessed from the inner class that uses Enter button to
          click this button.
         */
        final Button button1 = findViewById(R.id.next_a);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aValue = aVal.getText().toString();
                if(Integer.parseInt(aValue) == 0){
                    Toast.makeText(getBaseContext(),
                            "Division by zero is undefined!\nHence, a value cannot be zero.",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Bundle extras = new Bundle();

                extras.putString("A_VALUE", aValue);
                extras.putString("B_VALUE", bVal);
                extras.putString("C_VALUE", cVal);

                Intent intent = new Intent(getBaseContext(), CoefficientOfX.class).putExtras(extras);
                startActivity(intent);
            }
        });

        /*
        Process Enter button to  perform Next button click by setting an Editor Action Listener.
         */
        aVal.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    button1.performClick();
                    return true;
                }
                return false;
            }
        });
    }
}
