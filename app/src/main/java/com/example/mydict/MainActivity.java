package com.example.mydict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView pinyinTv,bushouTv,chengyuTv,tuernTv,juziTv;
    EditText searchEdit;

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
                break;
            case R.id.main_tv_tuwen:
                break;
        }
    }
}
