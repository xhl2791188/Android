package com.example.root.appinsight_testall2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FourView extends Activity{

	private Button searchBtn;// 确定按钮
	private ImageView img;// 图片容器
	private EditText searchEdt;// 输入框

	private String path;// URL路径
	ImageLoader loader = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.four);

		searchBtn = (Button) findViewById(R.id.img_btn);
		img = (ImageView) findViewById(R.id.imagevv);
		searchEdt = (EditText) findViewById(R.id.img_edt);
		searchBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				path = searchEdt.getText().toString();
				loader = new ImageLoader();
		 		loader.execute(path);
			}
		});
	}


	class ImageLoader extends AsyncTask<String, Integer, Bitmap>{



		@Override
		protected void onPreExecute() {
			// 第一个执行方法
			super.onPreExecute();
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap=null;
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
					bitmap = BitmapFactory.decodeStream(inputStream);
				}
				publishProgress();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bitmap;
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {

			super.onProgressUpdate(progress);
		}
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (bitmap != null) {
				   img.setImageBitmap(bitmap);
			}
		}
	}

}
