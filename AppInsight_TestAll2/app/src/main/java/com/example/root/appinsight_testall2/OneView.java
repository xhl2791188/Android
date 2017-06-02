package com.example.root.appinsight_testall2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OneView extends Activity {

	private Button searchBtn;// 确定按钮
	private ImageView img;// 图片容器
	private EditText searchEdt;// 输入框
	private Handler mhandler;

	private String path;// URL路径


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.one);
		searchBtn = (Button) findViewById(R.id.img_btn);
		img = (ImageView) findViewById(R.id.imagevv);
		searchEdt = (EditText) findViewById(R.id.img_edt);

		init();
	}

	private void init() {
		// 接收message通知
		mhandler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					img.setImageBitmap((Bitmap) msg.obj);
				}
			}
		};
		// 为按钮设置监听时间
		searchBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				path = searchEdt.getText().toString();
				Thread th = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {

							//Thread.sleep(1000);


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
								mhandler.sendMessage(msg);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				th.start();
			}
		});
	}


}
