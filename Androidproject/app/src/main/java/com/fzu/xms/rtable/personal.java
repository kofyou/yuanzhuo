package com.fzu.xms.rtable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class personal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        Button btnSetting=(Button)findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener( new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(personal.this,setting.class);
                startActivity(intent);
            }
        });
    }
}
