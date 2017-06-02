package com.example.root.appinsight_testall2;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public static UpdateUI updateUI;


    public static void setUpdateUI(UpdateUI updateUIInterface){
        updateUI=updateUIInterface;
    }
    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        try {


           // Thread.sleep(1000);


           // Log.d("1111111111111","1111111111111111");
            Bundle bundle = intent.getExtras();
            String path = bundle.getString("extra");
            Log.d("1111111111111",path);
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty(
                    "User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Shuame)");
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                // 采用传送消息的模式 把view操作消息发给主线程
                Message msg = new Message();
                msg.what = 0x123;
                msg.obj = bitmap;
                if(updateUI!=null){
                    updateUI.updateUI(msg);
                }


            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public interface UpdateUI{
        void updateUI(Message message);
    }
    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.e("TAG","onCreate");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.e("TAG","onDestroy");
    }
}
