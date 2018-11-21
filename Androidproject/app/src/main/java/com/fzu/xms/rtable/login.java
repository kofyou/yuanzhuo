package com.fzu.xms.rtable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button logIn=(Button)findViewById(R.id.logIn);
        logIn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,homepage.class);
                startActivity(intent);
            }
        });

        TextView signUp=(TextView)findViewById(R.id.signUp);
        signUp.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,signup.class);
                startActivity(intent);
            }
        });

    }
}
