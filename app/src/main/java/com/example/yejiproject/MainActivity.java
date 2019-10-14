package com.example.yejiproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button loginbutton = (Button)findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ID = (EditText) findViewById(R.id.ID);
                String id = ID.getText().toString();
                EditText PW = (EditText) findViewById(R.id.PW);
                String pw = PW.getText().toString();

                if (pw.equals(getPW(id))){
                    Intent intent = new Intent(getApplicationContext(), FinalActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                } else {
                    if (getPW(id) == null) {
                        Toast.makeText(getApplicationContext(), "회원이아닙니다.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "비밀번호가 틀렸습니다.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        final Button signUpButton = (Button) findViewById(R.id.signupbutton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);

            }
        });
    }
    public String getPW(String id){
        String data = null;

        try {
            FileInputStream file = openFileInput(id);
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            data = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}

