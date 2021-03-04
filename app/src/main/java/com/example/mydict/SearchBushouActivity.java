package com.example.mydict;

import android.os.Bundle;

import com.example.mydict.utils.CommonUtils;
import com.example.mydict.utils.URLUtils;

public class SearchBushouActivity extends BaseSearchActivity {

    String url;   // 获取部首 数据资源的url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleTv.setText(R.string.main_tv_bushou);
        initData(CommonUtils.FILE_BUSHOU,CommonUtils.TYPE_BUSHOU);
        word = "丨";  // 默认进入页面。获取的是 a 的；
        url = URLUtils.getBushouurl(word,page,pagesize);
        // 加载网络数据；
        loadData(url);

    }
}
