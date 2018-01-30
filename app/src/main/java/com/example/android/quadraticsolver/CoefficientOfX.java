package com.example.android.quadraticsolver;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class CoefficientOfX extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coefficient_of_x);
        /*
          Variables declaration:
          aValue, bValue and cValue store the values of  variables a, b and c respectively.
          message stores corresponding message depending on the entry point to this activity.
          extras stores passed over extra data from the other activities.
         */

        String aValue = "",
                bValue = "",
                cValue = "",
                message = "";
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            aValue = extras.getString("A_VALUE");
            bValue = extras.getString("B_VALUE");
            cValue = extras.getString("C_VALUE");

            if(!Objects.equals(aValue, "") && !Objects.equals(cValue, "")){
                message = "Coefficient of x squared (" + aValue + ") and\nConstant term (" + cValue
                        + ") are carried over.";
            } else if(!Objects.equals(aValue, "")){
                message = "Coefficient of x squared (" + aValue + ") is carried over.";
            }else if(!Objects.equals(cValue, "")){
                message = "Constant term (" + cValue + ") is carried over.";
            }
            Toast.makeText(getApplicationContext(),
                    message,
                    Toast.LENGTH_LONG).show();
        }

        //Display a previously entered b value, if any.
        if(!Objects.equals(bValue, "")){
                EditText b_value_view = findViewById(R.id.b_value);
                b_value_view.setText(bValue);
                b_value_view.selectAll();
        }

        //aVal and cVal are declared final since they are accessed in subclasses.
        final String aVal = aValue,
                     cVal = cValue;

        //Register a click event listener for the Previous button.
        final Button button1 = findViewById(R.id.prev);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = new Bundle();
                extras.putString("A_VALUE", aVal);
                extras.putString("C_VALUE", cVal);

                EditText b_value_view = findViewById(R.id.b_value);
                String bValue = b_value_view.getText().toString();

                extras.putString("B_VALUE", bValue);


                Intent intent = new Intent(getBaseContext(),MainActivity.class).putExtras(extras);
                startActivity(intent);
            }
        });

        //Register a click event for the Next button in this activity.
        final Button button2 = findViewById(R.id.next_a_and_b);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = new Bundle();
                extras.putString("A_VALUE", aVal);
                extras.putString("C_VALUE", cVal);

                EditText b_value_view = findViewById(R.id.b_value);
                String bValue = b_value_view.getText().toString();

                extras.putString("B_VALUE", bValue);

                Intent intent = new Intent(getBaseContext(), ConstantTerm.class).putExtras(extras);
                startActivity(intent);
            }
        });

        //Register editor action listener to process Enter and Esc buttons.
        EditText b_value_view = findViewById(R.id.b_value);
        b_value_view.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    button2.performClick();
                    return true;
                }else if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ESCAPE) {
                    button1.performClick();
                    return true;
                }
                return false;
            }
        });
    }
}
