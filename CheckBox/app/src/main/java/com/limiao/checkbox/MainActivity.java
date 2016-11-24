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
//    private ListAdapter mListAdapter;
    private MyAdapter mMyAdapter;
    private List<Person> mPersons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv_aty_main);
        mPersons = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Person person = new Person("aaa","34");
            mPersons.add(person);
        }
//        mListAdapter = new ListAdapter(this);
        mMyAdapter = new MyAdapter(this);
//        mListAdapter.setPersons(mPersons);
        mMyAdapter.setPersons(mPersons);
//        mListView.setAdapter(mListAdapter);
        mListView.setAdapter(mMyAdapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                mListAdapter.setVisibility(true);
//                mListAdapter.setSelceted(position,true);
                mMyAdapter.setVisibility(true);
                return false;
            }
        });
    }
}
