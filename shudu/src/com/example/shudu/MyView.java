package com.example.shudu;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

@SuppressLint("DrawAllocation")
public class MyView extends View {

	private float width;
	private float height;
	private  int seiectedX;
	private  int seiectedY;

	private Game game = new Game();

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		this.width = w / 9f;
		this.height = h / 9f;
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// 生成画布背景
		Paint backgroundPaint = new Paint();
		backgroundPaint.setColor(getResources().getColor(R.color.shudu_backbround));
		canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);

		Paint hlightPaint = new Paint();
		hlightPaint.setColor(getResources().getColor(R.color.shudu_hlight));

		Paint lightPaint = new Paint();
		lightPaint.setColor(getResources().getColor(R.color.shudu_light));

		Paint darkPaint = new Paint();
		darkPaint.setColor(getResources().getColor(R.color.shudu_dark));

		for (int i = 0; i < 9; i++) {
			canvas.drawLine(0, i * height, getWidth(), i * height, lightPaint);
			canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hlightPaint);
			canvas.drawLine(i * width, 0, i * width, getHeight(), lightPaint);
			canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hlightPaint);
		}

		for (int i = 1; i < 9; i++) {
			if (i % 3 != 0) {
				continue;
			}
			canvas.drawLine(0, i * height, getWidth(), i * height, darkPaint);
			canvas.drawLine(i * width, 0, i * width, getHeight(), darkPaint);
		}

		// 绘制数字
		Paint numberPaint = new Paint();
		numberPaint.setColor(Color.BLACK);
		numberPaint.setStyle(Paint.Style.STROKE);
		numberPaint.setTextSize(height * 0.75f);
		numberPaint.setTextAlign(Paint.Align.CENTER);

		// 字体居中的算法
		FontMetrics fm = numberPaint.getFontMetrics();
		float x = width / 2;
		float y = height / 2 - (fm.ascent + fm.descent) / 2;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				canvas.drawText(game.getTitleString(i, j), i * width + x, j * height + y, numberPaint);
			}
		}
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN) {
			return super.onTouchEvent(event);
		}

		seiectedX = (int) (event.getX() / width);
		seiectedY = (int) (event.getY() / height);

		int used[] = game.getUsedTilesByCoor(seiectedX,seiectedY);
		StringBuffer sb =new StringBuffer();
		for (int i = 0; i < used.length; i++) {
			sb.append(used[i]);
		}

		KeyDialog keyDialog = new KeyDialog(getContext(),used,this);
		keyDialog.show();
		return true;
	}

	
		public void setSelectedTile(int tile){	
			if(game.setTileIfValid(seiectedX, seiectedY,tile)){
					invalidate();
			} 
		}
}
