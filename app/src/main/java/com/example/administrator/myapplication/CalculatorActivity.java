package com.example.administrator.myapplication;

import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class CalculatorActivity extends AppCompatActivity {
    private EditText etLoanAmount, etDownPayment, etTerm, etAnnualInterestRate;
    private TextView tvMonthlyPayment, tvTotalRepayment, tvTotalInterest, tvAverageMonthlyInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        etLoanAmount = (EditText) findViewById(R.id.loan_amount);
        etDownPayment = (EditText) findViewById(R.id.down_payment);
        etTerm = (EditText) findViewById(R.id.term);
        etAnnualInterestRate = (EditText) findViewById(R.id.annual_interest_rate);
        tvMonthlyPayment = (TextView) findViewById(R.id.monthly_repayment);
        tvTotalRepayment = (TextView) findViewById(R.id.total_repayment);
        tvTotalInterest = (TextView) findViewById(R.id.total_interest);
        tvAverageMonthlyInterest = (TextView) findViewById(R.id.average_monthly_interest);

        if (savedInstanceState!=null) {
            etLoanAmount.setText(savedInstanceState.getCharSequence("LoanAmount"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putCharSequence("LoanAmount", etLoanAmount.getText());

        super.onSaveInstanceState(outState);
    }

    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.button_calculate:
                Log.d("check", "cal clicked");
                calculate();
                break;
            case R.id.button_reset:
                reset();
//                Log.d("check", "reset clicked");
                break;
        }
    }
    private void calculate()
    {
        String amount = etLoanAmount.getText().toString();
        String downPayment = etDownPayment.getText().toString();
        String interestRate = etAnnualInterestRate.getText().toString();
        String term = etTerm.getText().toString();

        double loanAmount = Double.parseDouble(amount) - Double.parseDouble(downPayment);
        double interest = Double.parseDouble(interestRate) / 12 /100;
        double noOfMonth = (Integer.parseInt(term)* 12);

        if (noOfMonth > 0)
        {

            double monthlyRepayment = loanAmount * (interest + (interest / (java.lang.Math.pow((1 + interest), noOfMonth) -1)));
            double tcimalll = Math.round(monthlyRepayment*100.0)/100.0;
            double totalRepayment = monthlyRepayment * noOfMonth;
            double totalInterest = totalRepayment - loanAmount;
            double monthlyInterest = totalInterest / noOfMonth;

//            tvMonthlyPayment.setText(String.valueOf(monthlyRepayment));
//            tvMonthlyPayment.setText(String.format("%.2f", monthlyRepayment));
            tvMonthlyPayment.setText(String.format("%.2f", tcimalll));
            tvTotalRepayment.setText(String.format("%.2f",totalRepayment));
            tvTotalInterest.setText(String.format("%.2f",totalInterest));
            tvAverageMonthlyInterest.setText(String.format("%.2f",monthlyInterest));
           }
    }

    private void reset()
    {
        etLoanAmount.setText("");
        etDownPayment.setText("");
        etTerm.setText("");
        etAnnualInterestRate.setText("");

        tvMonthlyPayment.setText(R.string.default_result);
        tvTotalRepayment.setText(R.string.default_result);
        tvTotalInterest.setText(R.string.default_result);
        tvAverageMonthlyInterest.setText(R.string.default_result);


    }
}
