package com.fzu.xms.rtable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class homepage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        TextView myPersonal=(TextView)findViewById(R.id.myPersonal);
        Drawable[] drawable1= myPersonal.getCompoundDrawables();
        drawable1[1].setBounds(0, 0, 100, 100);
        myPersonal.setCompoundDrawables(null, drawable1[1], null,null);

        myPersonal.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,personal.class);
                startActivity(intent);
            }
        });




        TextView myHistory=(TextView)findViewById(R.id.myHistory);
        Drawable[] drawable2= myHistory.getCompoundDrawables();
        drawable2[1].setBounds(0, 0, 100, 100);
        myHistory.setCompoundDrawables(null, drawable2[1], null,null);

        myHistory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,myhistory.class);
                startActivity(intent);
            }
        });

        TextView myGroup=(TextView)findViewById(R.id.myGroup);
        Drawable[] drawable3 = myGroup.getCompoundDrawables();
        drawable3[1].setBounds(0, 0, 100, 100);
        myGroup.setCompoundDrawables(null, drawable3[1], null,null);

        myGroup.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,friend.class);
                startActivity(intent);
            }
        });


        Button joinBtn=(Button)findViewById(R.id.join);
        joinBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,join.class);
                startActivity(intent);
            }
        });

        Button createBtn=(Button)findViewById(R.id.create);
        createBtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,create.class);
                startActivity(intent);
            }
        });

    }
}
