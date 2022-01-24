package com.favmartcompanny.favmarapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class Create_Account_Activity extends AppCompatActivity {
    EditText edName,edEmail,edPhone,edPassword,edConfirm,edAddress;
    Button btnRegister;
    TextView txtsingin;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__account_);
        getView();
        txtsingin=findViewById(R.id.txtsingin);
        btnRegister=findViewById(R.id.btnCRegister);
        txtsingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Create_Account_Activity.this , Login_Activity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    String name = edName.getText ().toString ();
                    String email = edEmail.getText ().toString ();
                    String phone = edPhone.getText ().toString ();
                    String password = edPassword.getText ().toString ();
                    String address = edAddress.getText ().toString ();

                    Intent intent = new Intent (Create_Account_Activity.this , Confirm_Activity.class);
                    intent.putExtra ("name", name );
                    intent.putExtra ("email", email );
                    intent.putExtra ("phone", phone );
                    intent.putExtra ("password", password );
                    intent.putExtra ("address", address );

                    startActivity (intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"invalid",Toast.LENGTH_LONG).show();
                }
            }
        });
        //Validation Style
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.editTextCName, RegexTemplate.NOT_EMPTY,R.string.invalid);
        awesomeValidation.addValidation(this,R.id.editTextCEmail, Patterns.EMAIL_ADDRESS,R.string.invalidEmail);
        awesomeValidation.addValidation(this,R.id.editTextCPassword, ".{8,}$",R.string.invalidpass);
        awesomeValidation.addValidation(this,R.id.editTextCConfirm,R.id.editTextCPassword,R.string.invalidmatch);

        awesomeValidation.addValidation(this,R.id.editTextCAddres, RegexTemplate.NOT_EMPTY,R.string.emptaddress);

    }
    public  void getView(){
        edName=findViewById(R.id.editTextCName);
        edEmail=findViewById(R.id.editTextCEmail);
        edPhone=findViewById(R.id.editTextCMobile);
        edConfirm=findViewById(R.id.editTextCConfirm);
        edPassword=findViewById(R.id.editTextCPassword);
        edAddress=findViewById(R.id.editTextCAddres);

    }
}