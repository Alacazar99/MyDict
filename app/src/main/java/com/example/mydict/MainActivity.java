package com.example.mydict;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
        switch (view.getId()) {
            case R.id.main_iv_setting:
                break;
            case R.id.main_iv_search:
                break;
            case R.id.main_tv_pinyin:
                break;
            case R.id.main_tv_bushou:
                break;
            case R.id.main_tv_chengyu:
                break;
            case R.id.main_tv_tuwen:
                break;
        }
    }
}
