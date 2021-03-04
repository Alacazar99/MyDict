package com.example.mydict;

import android.os.Bundle;
import android.util.Log;

import com.example.mydict.utils.CommonUtils;
import com.example.mydict.utils.URLUtils;

public class SearchPinyinAcitvity extends BaseSearchActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleTv.setText(R.string.main_tv_pinyin);
        initData(CommonUtils.FILE_PINYIN,CommonUtils.TYPE_PINYIN);
        ElistView.expandGroup(0);  // 默认展开第一组第一项；
        word = "a";  // 默认进入页面。获取的是 a 的；
        url = URLUtils.getPinyinurl(word,page,pagesize);
        // 加载网络数据；
        loadData(url);
        Log.i("loadData", "onCreate: "+ "加载网络数据");
    }
}
