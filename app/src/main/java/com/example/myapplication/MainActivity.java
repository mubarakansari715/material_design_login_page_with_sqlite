package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity
{
    TextInputLayout name, password, email;
    Button sbmt;
    LinearLayout mlinearLayout;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mlinearLayout=(LinearLayout)findViewById(R.id.linear);
        name = (TextInputLayout) findViewById(R.id.nametext);
        password = (TextInputLayout) findViewById(R.id.passwordtext);
        email = (TextInputLayout) findViewById(R.id.emailtext);
        sbmt = (Button) findViewById(R.id.sbmt_add);


            sbmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(InputValidate() == true){
                        processinsert(name.getEditText().getText().toString(),password.getEditText().getText().toString(),email.getEditText().getText().toString());

                       // Toast.makeText(MainActivity.this, "Plase Enter", Toast.LENGTH_SHORT).show();

                    }
                }

            });


    }
    private boolean InputValidate() {
        boolean isValid=true;
        if (name.getEditText().getText().toString().trim().length() == 0) {
            name.setError("Name cannot be blank");
            isValid = false;
        }  if (email.getEditText().getText().toString().trim().length() == 0) {
            email.setError("Email cannot be blank");
            isValid = false;
        } if (password.getEditText().getText().toString().trim().length() == 0) {
            password.setError("Passsword cannot be blank");
            isValid = false;
        }
        return isValid;
    }


    private void processinsert(String n, String p, String e)
    {
       String res=new dbmanager(this).addrecord(n,p,e);
       name.getEditText().setText("");
       password.getEditText().setText("");
       email.getEditText().setText("");
        final ProgressDialog dialog = new ProgressDialog(ctx);
        dialog.setMessage("Insert Data Please Wait");
        dialog.show();
        Intent intent = new Intent(getApplicationContext(),fetchdata.class);
        startActivity(intent);

    }


}