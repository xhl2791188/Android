package com.example.root.appinsight_testall2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ThreeView extends Activity implements View.OnClickListener,MyIntentService.UpdateUI {

	private Button searchBtn;// 确定按钮
	private ImageView img;// 图片容器
	private EditText searchEdt;// 输入框

	private String path;// URL路径

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.three);
		searchBtn = (Button) findViewById(R.id.img_btn);
		img = (ImageView) findViewById(R.id.imagevv);
		searchEdt = (EditText) findViewById(R.id.img_edt);
		searchBtn.setOnClickListener(this);

	}


	private final Handler mhandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			//Log.d("1111111111111","33333333333");

			if (msg.what == 0x123) {
				img.setImageBitmap((Bitmap) msg.obj);
			}
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.img_btn:
				//Log.d("1111111111111","2222222222222");

				path = searchEdt.getText().toString();
				Intent intentService = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("extra",path);
				intentService.putExtras(bundle);
				intentService.setClass(ThreeView.this,MyIntentService.class);
				startService(intentService);
				MyIntentService.setUpdateUI(this);
		}
	}


@Override
public void updateUI(Message message) {
	//Log.d("111111111","4444444444444");
		mhandler.sendMessage(message);
		}
}
