package com.example.mydict.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AssetsUtils {
    
    public static String getAssetsContent(Context context,String filename){
        // 获取 assets 文件夹的管理者对象；
        AssetManager manager = context.getResources().getAssets();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try{
            InputStream open = manager.open(filename);
            // 输入流的读取；
            int hasRead = 0;
            byte[] buf = new byte[1024];
            while (true){
                hasRead = open.read(buf);
                if (hasRead == -1) {
                    break;
                }else {
                    stream.write(buf,0,hasRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream.toString();

    }
}
