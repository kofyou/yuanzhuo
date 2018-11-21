package com.fzu.xms.rtable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;

public class create extends AppCompatActivity {

    private String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        final Spinner howMany=findViewById(R.id.howMany);
        List<String> dataList= new ArrayList<>();
        for(int i=2;i<=12;i++)
            dataList.add(String.valueOf(i));

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,dataList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        howMany.setAdapter(arrayAdapter);

        howMany.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2,long arg3){
                //补上
                amount =(String)howMany.getItemAtPosition(arg2);
            }

            public void onNothingSelected(AdapterView<?> arg0){
                amount="12";
            }
        });
        Button confirmCreate=(Button)findViewById(R.id.confirmCreate);
        final EditText roomNum=findViewById(R.id.roomNum);
        confirmCreate.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create.this,wait.class);
                intent.putExtra("roomNum",roomNum.getText().toString());
                intent.putExtra("amount",amount);
                startActivity(intent);
            }
        });
    }
}
