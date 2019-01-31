package com.lo.moslem.quran;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lo.moslem.R;

import java.util.ArrayList;

public class QuranAdapter extends BaseAdapter {

    private ArrayList<QuranItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public QuranAdapter (Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<QuranItems> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final QuranItems item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_quran, null);
            holder.tvNo = (TextView) convertView.findViewById(R.id.tvNo);
            holder.tvSurah = (TextView) convertView.findViewById(R.id.tvSurah);
            holder.tvSurahFrom = (TextView) convertView.findViewById(R.id.tvSurahFrom);
            holder.tvNoAyat = (TextView) convertView.findViewById(R.id.tvNoAyat);
            holder.tvAsma = (TextView) convertView.findViewById(R.id.tvAsma);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvNo.setText(String.valueOf(mData.get(position).getChapterNumber()));
        holder.tvSurah.setText(mData.get(position).getNamaSurah());
        holder.tvSurahFrom.setText(mData.get(position).getTempatWhayu());
        holder.tvNoAyat.setText(String.valueOf(mData.get(position).getAyatDihitung()));
        holder.tvAsma.setText(mData.get(position).getTulisanArab());

        return convertView;
    }

    private static class ViewHolder {
        TextView tvNo;
        TextView tvSurah;
        TextView tvSurahFrom;
        TextView tvNoAyat;
        TextView tvAsma;
    }

    public void updateList(ArrayList<QuranItems> newList) {
        mData = new ArrayList<>();
        mData.addAll(newList);
        notifyDataSetChanged();
    }
}
