package com.example.mydict;

import android.os.Bundle;

import com.example.mydict.utils.CommonUtils;

public class SearchBushouActivity extends BaseSearchActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleTv.setText(R.string.main_tv_bushou);
        initData(CommonUtils.FILE_BUSHOU,CommonUtils.TYPE_BUSHOU);

    }
}
