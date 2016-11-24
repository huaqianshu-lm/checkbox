package com.limiao.checkbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by limiao on 16/11/24.
 */

public class MyAdapter extends BaseAdapter {
    private List<Person> mPersons;
    private Context mContext;
    private boolean mVisibility = false;// checkbox是否显示,默认不显示

    public MyAdapter(Context context) {
        mContext = context;
        notifyDataSetChanged();
    }

    public void setVisibility(boolean visibility) {
        mVisibility = visibility;
        notifyDataSetChanged();
    }

    public void setPersons(List<Person> persons) {
        mPersons = persons;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mPersons == null ? 0 : mPersons.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Person person = mPersons.get(position);
        holder.mNameTv.setText(person.getName());
        holder.mAgeTv.setText(person.getAge());
        if (mVisibility) {
            holder.mCheckBox.setVisibility(View.VISIBLE);
            final ViewHolder finalHolder = holder;

            /**
             * 此处要用setOnClickListener,而不是setOnCheckedChangeListener()
             * 否则会出现混乱的情况
             */

            holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox) v;
                    finalHolder.mCheckBox.setChecked(checkBox.isChecked());
                }
            });
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView mNameTv;
        private TextView mAgeTv;
        private CheckBox mCheckBox;

        public ViewHolder(View view) {
            mNameTv = (TextView) view.findViewById(R.id.tv_item_list_name);
            mAgeTv = (TextView) view.findViewById(R.id.tv_item_list_age);
            mCheckBox = (CheckBox) view.findViewById(R.id.ckb_item_list);
        }
    }
}
