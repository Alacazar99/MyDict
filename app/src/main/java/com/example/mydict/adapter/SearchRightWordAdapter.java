package com.example.mydict.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mydict.R;
import com.example.mydict.bean.PinBuWordBean;

import java.util.List;

public class SearchRightWordAdapter extends BaseAdapter {
    Context context;
    List<PinBuWordBean.ResultBean.ListBean> mDatas;  //查询页面右侧的数据源；
    LayoutInflater inflater;

    public SearchRightWordAdapter(Context context, List<PinBuWordBean.ResultBean.ListBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
//        return mDatas.size();
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_search_word,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 数据源
//        PinBuWordBean.ResultBean.ListBean bean = mDatas.get(position);
//        String word = bean.getZi();
//        viewHolder.tv_word.setText(word);
        return convertView;
    }

    class ViewHolder{
        TextView tv_word;
        public  ViewHolder(View view){
            tv_word = view.findViewById(R.id.item_search_tv_word);
        }
    }
}
