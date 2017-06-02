package com.example.root.appinsight_testall2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TwoView extends Activity{


	private Button searchBtn;// 确定按钮
	private ImageView img;// 图片容器
	private EditText searchEdt;// 输入框
	private Handler mhandler;
	private Handler myThreadHandler;
	private String path;// URL路径
	private HandlerThread myThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.two);
		searchBtn = (Button) findViewById(R.id.img_btn);
		img = (ImageView) findViewById(R.id.imagevv);
		searchEdt = (EditText) findViewById(R.id.img_edt);


		mhandler = new Handler() {
			public void handleMessage(Message message) {
				if (message.what == 0x124) {
					//Log.d("1111111111111111","444444444444444");
					img.setImageBitmap((Bitmap) message.obj);
				}
			}
		};
		searchBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				path = searchEdt.getText().toString();
				//初始化HandlerThread
				myThread = new HandlerThread("handlerThread_test");
				//启动HandlerThread
				myThread.start();
				myThreadHandler = new Handler(myThread.getLooper()){
					@Override
					//实现消息处理。
					public void handleMessage(Message msg) {
						if (msg.what == 0x123) {
							//img.setImageBitmap((Bitmap) msg.obj);
							Message message = new Message();
							message.what = 0x124;
							message.obj = msg.obj;
							mhandler.sendMessage(message);
						//	Log.d("1111111111111111","3333333333333333");
						}
					}
				};


				myThreadHandler.post(new Runnable(){
					@Override
					public void run() {
						try {

						//	Thread.sleep(1000);


							//Log.d("111111111111111","1111111111111111111");
							URL url = new URL(path);
							HttpURLConnection conn = (HttpURLConnection) url.openConnection();
							conn.setRequestMethod("GET");
							conn.setConnectTimeout(5000);
							conn.setRequestProperty(
									"User-Agent",
									"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; Shuame)");
							int responseCode = conn.getResponseCode();
							if (responseCode == 200) {
							//	Log.d("1111111111111111","222222222222222");
								InputStream inputStream = conn.getInputStream();
								Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
								// 采用传送消息的模式 把view操作消息发给主线程
								Message msg = new Message();
								msg.what = 0x123;
								msg.obj = bitmap;
								myThreadHandler.sendMessage(msg);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

			}
		});
	}

}
