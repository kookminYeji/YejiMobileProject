package com.example.yejiproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final RadioButton accept = (RadioButton)findViewById(R.id.accept);
        final Button signupbutton = (Button) findViewById(R.id.signUp);

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(accept.isChecked()) {
                    EditText ID = (EditText) findViewById(R.id.ID);
                    String id = ID.getText().toString();
                    EditText PW = (EditText) findViewById(R.id.PW);
                    String pw = PW.getText().toString();
                    EditText name = (EditText) findViewById(R.id.name);
                    String Name = name.getText().toString();
                    EditText address = (EditText) findViewById(R.id.address);
                    String Address = address.getText().toString();
                    EditText phone = (EditText) findViewById(R.id.phone);
                    String Phone = phone.getText().toString();

                    SignUp(id, pw, Name, Address, Phone);
                }else{
                    Toast.makeText(getApplicationContext(), "약관 동의를 해주십시오.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public boolean SignUp (String id, String pw, String Name, String Address, String Phone){
        String data = pw +"\n"+ Name +"\n"+ Address +"\n"+ Phone;

        if(getData(id)!=null){
            Toast.makeText(getApplicationContext(), "이미 존재하는 아이디입니다.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(id.length() < 6){
            Toast.makeText(getApplicationContext(), "아이디는 6글자 이상으로해주세요.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(pw.length()<8){
            Toast.makeText(getApplicationContext(), "비밀번호는 8글자 이상으로 해주세요.", Toast.LENGTH_LONG).show();
            return false;
        }
        try{
            FileOutputStream fos = openFileOutput(id, Context.MODE_PRIVATE);

            PrintWriter writer = new PrintWriter(fos);
            writer.print(data);
            writer.close();

            Toast.makeText(getApplicationContext(), "회원가입 성공.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("data", id);
            startActivity(intent);
        } catch(FileNotFoundException e){
            e.printStackTrace();

            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
        }
        return true;
    }
    public String getData(String id) {
        String go = null;
        try {
            FileInputStream fis = openFileInput(id);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            go = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return go;
    }

}




