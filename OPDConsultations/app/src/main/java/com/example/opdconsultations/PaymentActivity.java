package com.example.opdconsultations;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PaymentActivity extends AppCompatActivity implements PaymentResultWithDataListener,DatePickerDialog.OnDateSetListener {

    EditText name,phone,age,gender;
    TextView showBill;
    Button proceedtopay;
    String bill,finBill;
    long time;
    TextView showAppointment;

    String nameS,ageS,genderS,numberS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Checkout.preload(getApplicationContext());

        name=findViewById(R.id.name_et);
        age=findViewById(R.id.age_et);
        phone=findViewById(R.id.mob_et);
        gender=findViewById(R.id.gender);

        nameS=name.getText().toString();
        ageS=age.getText().toString();
        numberS=phone.getText().toString();
        genderS=gender.getText().toString();

        showAppointment=findViewById(R.id.show_appointment_date);

        TextView show_bill=findViewById(R.id.show_bill);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            bill = bundle.getString("BILL");
            int integerBill=Integer.parseInt(bill);
        finBill=bundle.getString("bill");
        show_bill.setText(finBill+"\n"+integerBill/100+"Rs"+"\n");}
        else
           bill="";

        Button selectdate=findViewById(R.id.select_date_bt);
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });



        proceedtopay=findViewById(R.id.final_pay_bt);
        proceedtopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startPayment(bill,nameS,numberS);
            }
        });
    }

    private void showDatePickerDialog() {

        DatePickerDialog datePickerDialog=new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void startPayment(String bill,String name,String number_ph) {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "OPD Consultation");
            options.put("description", "Medical Service Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", bill);

            JSONObject preFill = new JSONObject();
            preFill.put("email", name+"@gmail.com");
            preFill.put("contact", number_ph);
            preFill.put("orderId","112256##1");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        startActivity(new Intent(PaymentActivity.this,MainPage.class));
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        showAppointment.setText("Appointment date:"+dayOfMonth+"/"+month+"/"+year);
    }
}
