package com.fzu.xms.rtable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class myhistory extends AppCompatActivity {
    private List<History> history = new ArrayList<History>();//可变数组list

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhistory);

        //定义9个用例测试
        history.add(new History("2018","qqqqq"));
        history.add(new History("2019","23213321"));
        history.add(new History("2020","32321313"));
        history.add(new History("2018","12312312"));
        history.add(new History("2019","23213321"));
        history.add(new History("2020","32321313"));
        history.add(new History("2018","12312312"));
        history.add(new History("2019","23213321"));
        history.add(new History("2020","32321313"));

        ListView lv=findViewById(R.id.lv);//找控件*/
        lv.setAdapter(new MyListAdapter());

    }
    //定义适配器
    private class MyListAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return history.size();//显示的个数
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override//获取一个view，用来显示listview，会作为listview的一个条目出现
        public View getView(int position, View convertView, ViewGroup parent)
        {
            TextView tv = new TextView(myhistory.this);
            String data = history.get(position).getDate();
            String document = history.get(position).getDocument();
            tv.setText(data + "\n" + document+ "\n");
            return tv;
        }
    }
}
