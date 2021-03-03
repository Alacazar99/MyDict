package com.example.mydict;

import android.os.Bundle;

import com.example.mydict.utils.CommonUtils;

public class SearchPinyinAcitvity extends BaseSearchActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleTv.setText(R.string.main_tv_pinyin);
        initData(CommonUtils.FILE_PINYIN,CommonUtils.TYPE_PINYIN);

    }
}
