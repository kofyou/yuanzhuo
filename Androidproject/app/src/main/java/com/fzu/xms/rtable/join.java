package com.fzu.xms.rtable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class join extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Button confirmJoin=(Button)findViewById(R.id.confirmJoin);
        final EditText roomNum=findViewById(R.id.roomNum);

        confirmJoin.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(join.this,wait.class);
                intent.putExtra("roomNum",roomNum.getText().toString());
                intent.putExtra("amount",String.valueOf(2));
                startActivity(intent);
                finish();
            }
        });
    }
}
