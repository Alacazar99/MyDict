package com.example.mydict;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 网络请求的工具类；
 * */

public class BaseActivity extends AppCompatActivity implements Callback.CommonCallback<String> {
    // 封装加载网络数据请求的过程；
    public void loadData(String path){
        // 请求体参数；
        RequestParams params = new RequestParams(path);
        x.http().get(params,this);
    }

    @Override
    public void onSuccess(String result) {
        Log.d("onSuccess", "onSuccess: ");
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        Log.d("onError", "onError: ");
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
