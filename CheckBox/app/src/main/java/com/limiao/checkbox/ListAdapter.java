package com.limiao.checkbox;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by limiao on 16/11/23.
 */

public class ListAdapter extends BaseAdapter {
    private List<Person> mPersons;
    private Context mContext;
    private boolean visibility = false;// 记录checkbox是否可见,默认为不可见
    private HashMap<Integer, Boolean> selceted;
    public ListAdapter(Context context) {
        mContext = context;
//        mPersons = persons;
        selceted = new HashMap<>();
//        initVisibility();
        Log.d("ListAdapter", "1");

    }

    private void initVisibility() {
        selceted = new HashMap<>();
        int size = mPersons.size();
        for (int i = 0; i < size + 1; i++) {
            selceted.put(i,false);
        }
    }


    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
        notifyDataSetChanged();
    }

    public void setSelceted(int pos , boolean visibility) {
        initVisibility();
        this.selceted.put(pos,visibility);
        Log.d("ListAdapter", "2");
        notifyDataSetChanged();
    }

    public HashMap<Integer, Boolean> getSelceted() {
        return selceted;
    }

        public void setPersons(List<Person> persons) {
        mPersons = persons;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        Log.d("ListAdapter", "3");
        return mPersons.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d("ListAdapter", "4");
        return mPersons.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.d("ListAdapter", "5");

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d("ListAdapter", "6");

        ViewHoler viewHoler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list,parent,false);
            viewHoler = new ViewHoler(convertView);
            convertView.setTag(viewHoler);
        } else {
            viewHoler = (ViewHoler) convertView.getTag();
        }
        Person person = mPersons.get(position);
        String name = person.getName();
        String age = person.getAge();
        viewHoler.mAgeTv.setText(age);
        viewHoler.mNameTv.setText(name);
        if (visibility) {
            Log.d("ListAdapter", "aaaa");
            viewHoler.mCheckBox.setVisibility(View.VISIBLE);
            final ViewHoler finalViewHoler = viewHoler;
            viewHoler.mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox) v;
                    finalViewHoler.mCheckBox.setChecked(checkBox.isChecked());
                    Log.d("ListAdapter", "checkBox.isChecked():" + checkBox.isChecked());
                    Log.d(position + "", checkBox.isChecked() + ""  );

                }
            });
        }
        return convertView;
    }

    public static class ViewHoler {
        private TextView mNameTv;
        private TextView mAgeTv;
        private CheckBox mCheckBox;

        public ViewHoler(View view) {
            mNameTv = (TextView) view.findViewById(R.id.tv_item_list_name);
            mAgeTv = (TextView) view.findViewById(R.id.tv_item_list_age);
            mCheckBox = (CheckBox) view.findViewById(R.id.ckb_item_list);
        }
    }
}
