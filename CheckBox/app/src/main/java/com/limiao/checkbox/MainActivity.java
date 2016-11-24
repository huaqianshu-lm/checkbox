package com.limiao.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private ListAdapter mListAdapter;
    private List<Person> mPersons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv_aty_main);
        mListAdapter = new ListAdapter(this);
        mPersons = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Person person = new Person("aaa","34");
            mPersons.add(person);
        }

        mListAdapter.setPersons(mPersons);
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mListAdapter.setVisibility(true);
                return false;
            }
        });
    }
}
