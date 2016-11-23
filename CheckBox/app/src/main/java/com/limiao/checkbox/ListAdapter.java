package com.limiao.checkbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by limiao on 16/11/23.
 */

public class ListAdapter extends BaseAdapter {
    private List<Person> mPersons;
    private Context mContext;
    private boolean visibility = false;// 记录checkbox是否可见,默认为不可见

    public ListAdapter(Context context) {
        mContext = context;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
        notifyDataSetChanged();
    }

    public void setPersons(List<Person> persons) {
        mPersons = persons;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mPersons.size();
    }

    @Override
    public Object getItem(int position) {
        return mPersons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
        viewHoler.mCheckBox.setChecked(person.isChecked());
        viewHoler.mAgeTv.setText(age);
        viewHoler.mNameTv.setText(name);
        if (visibility) {
            viewHoler.mCheckBox.setVisibility(View.VISIBLE);
            final ViewHoler finalViewHoler = viewHoler;
            viewHoler.mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox) v;
                    finalViewHoler.mCheckBox.setChecked(checkBox.isChecked());
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
