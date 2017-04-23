package ru.pangea.efremov.yetanotherhexcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String CURRENT_OPERAND = "CURRENT_OPERAND";
    public static final String CALCULATOR = "CALCULATOR";
    private Button calculateButton;
    private Button clearButton;
    private Button plusButton;
    private Button minusButton;
    private Button orButton;
    private Button notButton;
    private Button timesButton;
    private GridView gridview;
    private TextView displayText;


    private long currentOperand = 0;

    Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculator = new Calculator();
        initViews();
    }

    private void initViews() {
        calculateButton = (Button) findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(calculateListener);
        clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(clearListener);
        gridview = (GridView) findViewById(R.id.gridview);
        displayText = (TextView) findViewById(R.id.displayText);
        displayText.setText("0");
        ArrayAdapter<String> my_arayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                digits);
        gridview.setAdapter(my_arayAdapter);
        gridview.setOnItemClickListener(buttonsBehaviour);

        // Operations
        plusButton = (Button) findViewById(R.id.plusButton);
        minusButton = (Button) findViewById(R.id.minusButton);
        timesButton = (Button) findViewById(R.id.timesButton);
        orButton = (Button) findViewById(R.id.orButton);
        notButton = (Button) findViewById(R.id.notButton);
        plusButton.setOnClickListener(operationListener);
        minusButton.setOnClickListener(operationListener);
        timesButton.setOnClickListener(operationListener);
        notButton.setOnClickListener(operationListener);
        orButton.setOnClickListener(operationListener);
    }

    private void calculateAndDisplay() {
        calculator.setOperand(currentOperand);
        currentOperand = calculator.calculate();
        displayText.setText(Long.toHexString(currentOperand));
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CALCULATOR, calculator);
        outState.putLong(CURRENT_OPERAND, currentOperand);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        currentOperand = savedInstanceState.getLong(CURRENT_OPERAND);
        calculator = (Calculator) savedInstanceState.getSerializable(CALCULATOR);
        displayText.setText(Long.toHexString(currentOperand));
        super.onRestoreInstanceState(savedInstanceState);
    }

    private View.OnClickListener operationListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculator.setOperand(currentOperand);
            switch(v.getId()) {
                case R.id.plusButton:
                    calculator.setCurrentOperation(Calculator.Operation.PLUS_OP);
                    break;
                case R.id.minusButton:
                    calculator.setCurrentOperation(Calculator.Operation.MINUS_OP);
                    break;
                case R.id.timesButton:
                    calculator.setCurrentOperation(Calculator.Operation.TIMES_OP);
                    break;
                case R.id.orButton:
                    calculator.setCurrentOperation(Calculator.Operation.OR_OP);
                    break;
                case R.id.notButton:
                    calculator.setCurrentOperation(Calculator.Operation.NOT_OP);
                    calculateAndDisplay();
                    return;
                default:
                    break;
            }
            currentOperand = 0;
//            displayText.setText(Long.toHexString(currentOperand));
        }
    };

    private View.OnClickListener clearListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            currentOperand = 0;
            calculator.clear();
            displayText.setText("0");
        }
    };

    private View.OnClickListener calculateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculateAndDisplay();
        }
    };



    private OnItemClickListener buttonsBehaviour = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            currentOperand = (currentOperand << 4) + position;
            displayText.setText(Long.toHexString(currentOperand));
        }
    };

    private String[] digits = new String[] {
            "0", "1", "2",
            "3", "4", "5",
            "6", "7", "8",
            "9", "A", "B",
            "C", "D", "E",
            "F"
    };
}
