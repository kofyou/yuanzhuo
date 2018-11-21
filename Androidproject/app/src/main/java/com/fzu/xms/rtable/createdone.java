package com.fzu.xms.rtable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class createdone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createdone);

        Intent intent=getIntent();
        final String roomNum=intent.getStringExtra("roomNum");
        final String amount=intent.getStringExtra("amount");
        TextView inviteTxt=(TextView)findViewById(R.id.inviteTxt);
        inviteTxt.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createdone.this,invite.class);
                intent.putExtra("roomNum",roomNum);
                startActivity(intent);
            }
        });

        TextView uploadTxt=(TextView)findViewById(R.id.uploadTxt);
        uploadTxt.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createdone.this,wait.class);
                intent.putExtra("roomNum",roomNum);
                intent.putExtra("amount",amount);
                startActivity(intent);
                finish();
            }
        });
    }
}
