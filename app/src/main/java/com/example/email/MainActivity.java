package com.example.email;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTo,editTextSubject,editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTo=findViewById(R.id.editTextTo);
        editTextSubject=findViewById(R.id.editTextSubject);
        editTextMessage=findViewById(R.id.editTextMessage);

        Button buttonSend=findViewById(R.id.buttonSend);
         buttonSend.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 sendEmail();
             }
         });
    }
    private void sendEmail(){
        String recipient=editTextTo.getText().toString().trim();
        String subject=editTextSubject.getText().toString().trim();
        String message=editTextMessage.getText().toString().trim();

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{recipient});
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);

        intent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(intent,"Choose an email client"));
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}