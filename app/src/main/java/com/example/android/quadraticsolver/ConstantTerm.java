package com.example.android.quadraticsolver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConstantTerm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constant_term);

        /*
          Variables declaration:
          aValue, bValue and cValue store the values of  variables a, b and c respectively.
          extras stores passed over extra data from the other activities.
         */
        String aValue = "",
                bValue = "",
                cValue;
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            aValue = extras.getString("A_VALUE");
            bValue = extras.getString("B_VALUE");
            cValue = extras.getString("C_VALUE");

            EditText c_value_view = findViewById(R.id.c_value);
            c_value_view.setText(cValue);
            c_value_view.selectAll();

            Toast.makeText(getApplicationContext(),
                    "Coefficient of x squared (" + aValue + ") and \nCoefficient of x (" +
                            bValue + ") are carried over.",
                    Toast.LENGTH_LONG).show();
        }

        final String aVal = aValue,
                bVal = bValue;

        //Register a click event for the Previous button.
        final Button button1 = findViewById(R.id.prev);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText cVal = findViewById(R.id.c_value);
                Bundle prevExtras = new Bundle();

                prevExtras.putString("A_VALUE", aVal);
                prevExtras.putString("B_VALUE", bVal);
                prevExtras.putString("C_VALUE", cVal.getText().toString());
                Intent intent = new Intent(getBaseContext(), CoefficientOfX.class).putExtras(prevExtras);
                startActivity(intent);
            }
        });

        //Register a click event listener for the Calculate the values of x button.
        final Button button2 = findViewById(R.id.solve_equation);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText c_view = findViewById(R.id.c_value);
                int c_num_value = Integer.parseInt(c_view.getText().toString()),
                        b_num_value = Integer.parseInt(bVal),
                        a_num_value = Integer.parseInt(aVal);
                double determinant = Math.pow(b_num_value,2) - (4 * a_num_value * c_num_value);
                if(determinant < 0){
                    Toast.makeText(getBaseContext(),
                            "Square-root of negative number is invalid!\nHence, the entered" +
                                    " values do not represent true Quadratic equation.",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                double x1 = (-b_num_value + Math.sqrt(determinant)) / (2 * a_num_value),
                        x2 = (-b_num_value - Math.sqrt(determinant)) / (2 * a_num_value);

                TextView solution_view = findViewById(R.id.solution_view);
                String solution = "x = " + x1 + " or " + x2 + ".";
                solution_view.setText(solution);
                solution_view.setVisibility(View.VISIBLE);
            }
        });

        //Register editor action listener to take care of Enter and Esc buttons.
        final EditText c_value_view = findViewById(R.id.c_value);
        c_value_view.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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

        //Register click event listener for  Reset button.
        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                               
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
