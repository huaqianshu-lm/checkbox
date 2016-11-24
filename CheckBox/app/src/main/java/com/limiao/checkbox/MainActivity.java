package com.limiao.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private MyAdapter mMyAdapter;
    private List<Person> mPersons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv_aty_main);
        mPersons = new ArrayList<>();
        for (int i = 0; i < 90; i++) {
            Person person = new Person("aaa","34");
            mPersons.add(person);
        }
        mMyAdapter = new MyAdapter(this);
        mMyAdapter.setPersons(mPersons);
        mListView.setAdapter(mMyAdapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mMyAdapter.setVisibility(true);
                return false;
            }
        });
    }
}
