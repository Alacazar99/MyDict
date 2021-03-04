package com.example.mydict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class MainActivity extends AppCompatActivity {
    TextView pinyinTv,bushouTv,chengyuTv,tuernTv,juziTv;
    EditText searchEdit;
    ImageView mainIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        pinyinTv = findViewById(R.id.main_tv_pinyin);
        bushouTv = findViewById(R.id.main_tv_bushou);
        chengyuTv = findViewById(R.id.main_tv_chengyu);
        tuernTv = findViewById(R.id.main_tv_tuwen);
        juziTv = findViewById(R.id.main_tv_juzi);
        searchEdit = findViewById(R.id.main_edit);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.main_iv_setting:
                break;
            case R.id.main_iv_search:
                break;
            case R.id.main_tv_pinyin:
                intent.setClass(this, SearchPinyinAcitvity.class);
                startActivity(intent);
                break;
            case R.id.main_tv_bushou:
                intent.setClass(this, SearchBushouActivity.class);
                startActivity(intent);
                break;
            case R.id.main_tv_chengyu:
                initTextxUtils();
                break;
            case R.id.main_tv_tuwen:
                break;
        }
    }

    private void initTextxUtils() {
        RequestParams params = new RequestParams("https://www.baidu.com/s");
        // params.setSslSocketFactory(...); // 如果需要自定义SSL
        // params.addQueryStringParameter("wd", "xUtils");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("TAG", "onSuccess: " + result);
                Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("onError", "onError: " );
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d("onCancelled", "onCancelled: ");
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFinished() {
                Log.d("onFinished", "onFinished: " );
            }
        });

    }
}
