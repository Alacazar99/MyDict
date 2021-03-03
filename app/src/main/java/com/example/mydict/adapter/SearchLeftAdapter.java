package com.example.mydict.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.mydict.R;
import com.example.mydict.bean.PinBuBean;
import com.example.mydict.utils.CommonUtils;

import java.util.List;

public class SearchLeftAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> groupDatas; // 表示分组对象；
    List<List<PinBuBean.ResultBean>> childDatas;  // 每组对应的子类列表存放到该集合；

    int type;

    LayoutInflater inflater;
    int selectGroupPos = 0;
    int selectChildPos = 0;


    public void setSelectGroupPos(int selectGroupPos) {
        this.selectGroupPos = selectGroupPos;
    }

    public void setSelectChildPos(int selectChildPos) {
        this.selectChildPos = selectChildPos;
    }



    public SearchLeftAdapter(Context context, List<String> groupDatas, List<List<PinBuBean.ResultBean>> childDatas,int type) {
        this.context = context;
        this.groupDatas = groupDatas;
        this.childDatas = childDatas;
        this.type = type;
        inflater = LayoutInflater.from(context);  // 声明布局加载器；
    }

    //  @Override
    public int getGroupCount() {
        return groupDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childDatas.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupDatas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childDatas.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_exlv_groupview,null);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (GroupViewHolder) convertView.getTag();
        }
        // 获取指定位置的数据；
        String word = groupDatas.get(groupPosition);

        if (type == CommonUtils.TYPE_PINYIN) {
            holder.groupTv.setText(word);
        }else {
            holder.groupTv.setText(word + "画");
        }

        // 选中位置改变颜色；
        if (selectGroupPos == groupPosition) {
            // 当前位置被选中；
            convertView.setBackgroundColor(Color.rgb(105,105,105));
            holder.groupTv.setTextColor(Color.rgb(255,127,80));
        }else{
            convertView.setBackgroundResource(R.color.colorBg_grey);
            holder.groupTv.setTextColor(Color.BLACK);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_exlv_child,null);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ChildViewHolder) convertView.getTag();
        }

        // 指定获取位置；
        PinBuBean.ResultBean bean = childDatas.get(groupPosition).get(childPosition);

        if (type == CommonUtils.TYPE_PINYIN) {
            holder.childTv.setText(bean.getPinyin());
        }else{
            holder.childTv.setText(bean.getBushou());
        }
        // 设置选中改变颜色；
        if (selectGroupPos == groupPosition && selectChildPos == childPosition) {
            convertView.setBackgroundResource(R.color.white);
            holder.childTv.setTextColor(Color.RED);
        }else{
            convertView.setBackgroundResource(R.color.colorBg_grey);
            holder.childTv.setTextColor(Color.BLACK);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder{
        TextView groupTv;
        public GroupViewHolder(View view){
            groupTv = view.findViewById(R.id.item_group_tv);
        }
    }

    class ChildViewHolder{
        TextView childTv;
        public ChildViewHolder(View view){
            childTv = view.findViewById(R.id.item_child_tv);
        }
    }
}
