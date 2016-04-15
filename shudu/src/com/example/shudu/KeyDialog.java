package com.example.shudu;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class KeyDialog extends Dialog {
	
	private final View keys[] = new View[9];
	private final int user[];
	private  MyView myView;
	
	public KeyDialog(Context context,int [] user,MyView myView) {
		super(context);
		this.user = user;
		this.myView = myView;
	}
	
	//当一个Dialog第一次显示，调用其方法
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("KeyDialog");
		setContentView(R.layout.keypad);
		findViews();
		for(int i =0;i<user.length;i++){
			if(user[i]!=0){
				System.out.println(user[i]);
				keys[user[i]-1].setVisibility(View.INVISIBLE);
			}
		}
		
		
		setListeners();
	}
	
	private void  findViews() {
		keys[0]= findViewById(R.id.keypad_1);
		keys[1]= findViewById(R.id.keypad_2);
		keys[2]= findViewById(R.id.keypad_3);
		keys[3]= findViewById(R.id.keypad_4);
		keys[4]= findViewById(R.id.keypad_5);
		keys[5]= findViewById(R.id.keypad_6);
		keys[6]= findViewById(R.id.keypad_7);
		keys[7]= findViewById(R.id.keypad_8);
		keys[8]= findViewById(R.id.keypad_9);
	}

	private void returnResult(int tile){
		myView.setSelectedTile(tile);
		dismiss();
	}
	
	private void setListeners(){
			for(int i =0;i<keys.length;i++){
				final int t = i+1;
				keys[i].setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						returnResult(t);
					}
				});
			}
		
	}
}
