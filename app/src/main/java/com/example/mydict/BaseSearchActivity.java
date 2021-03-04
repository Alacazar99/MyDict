package com.example.mydict;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.mydict.adapter.SearchLeftAdapter;
import com.example.mydict.adapter.SearchRightWordAdapter;
import com.example.mydict.bean.PinBuBean;
import com.example.mydict.bean.PinBuWordBean;
import com.example.mydict.utils.AssetsUtils;
import com.example.mydict.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

public class BaseSearchActivity extends BaseActivity {

    TextView titleTv;
    ExpandableListView ElistView;
    PullToRefreshGridView pullToRefreshGridView;
    List<String> groupDatas; // 表示分组对象；
    List<List<PinBuBean.ResultBean>> childDatas;
    SearchLeftAdapter adapter;
    int selGroupPos = 0; // 表示被点击的组的位置；
    int selChildPos = 0; // 表示被点击的子元素的位置；

    List<PinBuWordBean.ResultBean.ListBean> gridDatas;  //查询页面右侧的数据源；

    SearchRightWordAdapter gridAdapter;

    int totalPage;  // 总页数；
    int page;    // 当前的 获取页数；
    int pagesize = 24;   // 默认一页默认获取24条数据；
    String word = "";   // 点击的左侧的那个拼音 或者 部首；


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_base);
        titleTv = findViewById(R.id.search_tv_title);
        ElistView = findViewById(R.id.search_elv);
        pullToRefreshGridView = findViewById(R.id.searchpy_gv);
        setExLvListener();
        initGridDatas();
    }

    /**
     * 初始化 查询页面右侧的数据源；
     * */
    private void initGridDatas() {
        gridDatas = new ArrayList<>();
        // 设置适配器；
        gridAdapter = new SearchRightWordAdapter(this, gridDatas);
        pullToRefreshGridView.setAdapter(gridAdapter);
    }

    /**
     * 加载数据成功，调用，json;
     * @param result
     */
    @Override
    public void onSuccess(String result) {

        PinBuWordBean bean = new Gson().fromJson( result, PinBuWordBean.class);
        Log.d("TAG", "onSuccess: " + bean);

        PinBuWordBean.ResultBean resultBean = bean.getResult();
        totalPage = resultBean.getTotalpage();   //将当前获取数据的总页数进行保存
        List<PinBuWordBean.ResultBean.ListBean> list = resultBean.getList();
        // 加载数据；
        refreshDataByGv(list);
    }

    /**
     * 更新gridview 当中的数据，提示适配器重新加载；
     * @param list
     */
    private void refreshDataByGv( List<PinBuWordBean.ResultBean.ListBean> list) {
        if (page == 1) {
            gridDatas.clear();
            gridDatas.addAll(list);
            adapter.notifyDataSetChanged();
        }else {    // 进行上拉 加载新的一页； 数据保留；
            gridDatas.addAll(list);
            adapter.notifyDataSetChanged();
            // 停止显示加载条；
            pullToRefreshGridView.onRefreshComplete();
        }
    }

    private void setExLvListener() {
        ElistView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                adapter.setSelectGroupPos(groupPosition);
                adapter.notifyDataSetInvalidated();
                // 获取被点击的位置;
                selGroupPos = groupPosition;
                // 获取右侧数据信息；
                return false;
            }
        });
        ElistView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                adapter.setSelectGroupPos(groupPosition);
                adapter.setSelectChildPos(childPosition);
                adapter.notifyDataSetInvalidated();
                // 获取child 被点击的位置；
                selGroupPos = groupPosition;
                selChildPos = childPosition;
                // 网络加载右面Gv的数据源；
                return false;
            }
        });
    }

    public void initData(String assetsName,int type) {
        /**
         * 读取Assets文件夹昂中的数据，使用Gson解析，将数据分组存储到二维列表当中
         * @param assetsName 文件名称
         * @param type 文件类型   pinyin--0    bushou--1
         * */
        groupDatas = new ArrayList<>();
        childDatas = new ArrayList<>();
        String json = AssetsUtils.getAssetsContent(this, assetsName);
        if (!TextUtils.isEmpty(json)) {
            PinBuBean pinBuBean = new Gson().fromJson(json, PinBuBean.class);
            List<PinBuBean.ResultBean> list = pinBuBean.getResult();
//            整理数据
            List<PinBuBean.ResultBean>grouplist = new ArrayList<>(); //声明每组包含的元素集合
            for (int i = 0; i < list.size(); i++) {
                PinBuBean.ResultBean bean = list.get(i);   // id,pinyin_key,pinyi
                if (type == CommonUtils.TYPE_PINYIN) {
                    String pinyin_key = bean.getPinyin_key();  //获取大写字母
                    if (!groupDatas.contains(pinyin_key)) {   //判断是否存在于分组的列表当中
                        groupDatas.add(pinyin_key);
//                        说明上一个拼音的已经全部录入到grouplist当中了，可以将上一个拼音的集合添加到childDatas
                        if (grouplist.size()>0) {
                            childDatas.add(grouplist);
                        }
//                        既然是新的一组，就要创建一个对应的新的子列表
                        grouplist = new ArrayList<>();
                        grouplist.add(bean);
                    }else{
                        grouplist.add(bean);  //大写字母存在，说明还在当前这组当中，可以直接添加
                    }
                }else if (type==CommonUtils.TYPE_BUSHOU) {
                    String bihua = bean.getBihua();
                    if (!groupDatas.contains(bihua)) {
                        groupDatas.add(bihua);
//                        新的一组，把上一组进行添加
                        if (grouplist.size()>0) {
                            childDatas.add(grouplist);
                        }
//                        新的一组，新创建子列表
                        grouplist = new ArrayList<>();
                        grouplist.add(bean);
                    }else{
                        grouplist.add(bean);//当前笔画存在，就不用向组当中添加了
                    }
                }
            }
//            循环结束之后，最后一组并没有添加进去，所以需要手动添加
            childDatas.add(grouplist);

            adapter = new SearchLeftAdapter(this, groupDatas, childDatas, type);
            ElistView.setAdapter(adapter);

        }
    }
//    public void initData(String s,int type) {
//        /**
//         * 读取assets文件夹中的数据源；使用Gson 解析，将数据分组存储到二维列表中；
//         * @param s 文件名称；
//         * @param type 文件类型： pinyin-0; bushou-1
//         */
//        groupDatas = new ArrayList<>();
//        childDatas = new ArrayList<>();
//        String json = AssetsUtils.getAssetsContent(this, s);
//
//        if (!TextUtils.isEmpty(json)) {
//            PinBuBean pinBuBean = new Gson().fromJson(json, PinBuBean.class);
//            List<PinBuBean.ResultBean> result = pinBuBean.getResult();  // 得到文件中的result;
//            // 整理数据；
//
//            List<PinBuBean.ResultBean> groupList = new ArrayList<>();  // 声明每组包含的元素数据
//            for (int i = 0; i < result.size(); i++) {
//                PinBuBean.ResultBean resultBean = result.get(i); //{id,pinyin_key,pinyi} or {id,Bihua,bushou}
//                if (type == CommonUtils.TYPE_PINYIN) {  // 判断 resultBean 是拼音还是部首；
//                    String pinyin_key = resultBean.getPinyin_key();  // 获取key: 大写字母；
//
//                    if (!groupDatas.contains(pinyin_key)) {    // 判断是否存在于分组的列表中；
//                        // 不存在，则说明 该key(大写字母)之前的key 对应的拼音已经全部加入;
//                        groupDatas.add(pinyin_key);
//                        if (groupList.size()>0) {
//                            childDatas.add(groupList);  // 将每一个大写字母对应的列表加入childDatas；
//                        }
//                        // 新的一个大写字母，对应一个新的空 子列表；
//                        groupList = new ArrayList<>();
//                        groupList.add(resultBean);
//                    }else{
//                        groupList.add(resultBean);
//                    }
//
//                } else if (type == CommonUtils.TYPE_BUSHOU){
//                    String bihua = resultBean.getBihua();  // 获取笔画；
//                    if (!groupDatas.contains(bihua)) {
//                        groupDatas.add(bihua);   // 如果groupDatas 中不包括此笔画，则加入；
//                        // 不存在，则说明 bihua 之前的 bihua 对应的部首已经全部加入;
//                        if (groupList.size() > 0) {
//                            childDatas.add(groupList);
//                        }
//                        // 新的一个大写字母，对应一个新的空 子列表；
//                        groupList = new ArrayList<>();
//                        groupList.add(resultBean);
//                    }else{
//                        groupList.add(resultBean);
//                    }
//                    }
//                // 循环结束，手动加入最后一组
//                childDatas.add(groupList);
//
//                // 设置适配器；
//                adapter = new SearchLeftAdapter(this,groupDatas,childDatas,type);
//                ElistView.setAdapter(adapter);
//                }
//            }
//        Log.i("tag", "groupDatas: "+ groupDatas);
//        Log.i("tag", "childDatas: "+ childDatas);
//    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_iv_back:
                finish();
                break;
        }
    }
}
