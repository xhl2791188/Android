package com.example.root.appinsight_testall2;


import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends ActivityGroup {
    @SuppressWarnings("unused")
    private LinearLayout bodyView,headview;
    private LinearLayout one, two, three, four,five;
    private int flag = 0; // ͨ�������ת��ͬ��ҳ�棬��ʾ��ͬ�Ĳ˵���
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main);
        initMainView();
        // ��ʾ���ҳ��
        showView(flag);
        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                flag = 0;
                showView(flag);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                flag = 1;
                showView(flag);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                flag = 2;
                showView(flag);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                flag = 3;
                showView(flag);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                flag = 4;
                showView(flag);
            }
        });

    }

    /*
	 * ��ʼ��������
	 */
    public void initMainView() {
        headview=(LinearLayout) findViewById(R.id.head);
        bodyView=(LinearLayout) findViewById(R.id.body);
        one=(LinearLayout) findViewById(R.id.one);
        two=(LinearLayout) findViewById(R.id.two);
        three=(LinearLayout) findViewById(R.id.three);
        four=(LinearLayout) findViewById(R.id.four);
        five=(LinearLayout) findViewById(R.id.five);
    }

    // ������������ʾ��������
    public void showView(int flag) {
        switch (flag) {
            case 0:
                bodyView.removeAllViews();
                View v = getLocalActivityManager().startActivity("one",
                        new Intent(MainActivity.this, OneView.class)).getDecorView();

                one.setBackgroundResource(R.drawable.frame_button_background);
                two.setBackgroundResource(R.drawable.frame_button_nopressbg);
                three.setBackgroundResource(R.drawable.frame_button_nopressbg);
                four.setBackgroundResource(R.drawable.frame_button_nopressbg);
                five.setBackgroundResource(R.drawable.frame_button_nopressbg);

                bodyView.addView(v);
                break;
            case 1:
                bodyView.removeAllViews();
                bodyView.addView(getLocalActivityManager().startActivity("two",
                        new Intent(MainActivity.this, TwoView.class))
                        .getDecorView());
                one.setBackgroundResource(R.drawable.frame_button_nopressbg);
                two.setBackgroundResource(R.drawable.frame_button_background);
                three.setBackgroundResource(R.drawable.frame_button_nopressbg);
                four.setBackgroundResource(R.drawable.frame_button_nopressbg);
                five.setBackgroundResource(R.drawable.frame_button_nopressbg);
                break;
            case 2:
                bodyView.removeAllViews();
                bodyView.addView(getLocalActivityManager().startActivity(
                        "three", new Intent(MainActivity.this, ThreeView.class))
                        .getDecorView());
                one.setBackgroundResource(R.drawable.frame_button_nopressbg);
                two.setBackgroundResource(R.drawable.frame_button_nopressbg);
                three.setBackgroundResource(R.drawable.frame_button_background);
                four.setBackgroundResource(R.drawable.frame_button_nopressbg);
                five.setBackgroundResource(R.drawable.frame_button_nopressbg);
                break;
            case 3:
                bodyView.removeAllViews();
                bodyView.addView(getLocalActivityManager().startActivity(
                        "four", new Intent(MainActivity.this, FourView.class))
                        .getDecorView());
                one.setBackgroundResource(R.drawable.frame_button_nopressbg);
                two.setBackgroundResource(R.drawable.frame_button_nopressbg);
                three.setBackgroundResource(R.drawable.frame_button_nopressbg);
                four.setBackgroundResource(R.drawable.frame_button_background);
                five.setBackgroundResource(R.drawable.frame_button_nopressbg);
                break;
            case 4:
                bodyView.removeAllViews();
                bodyView.addView(getLocalActivityManager().startActivity(
                        "five", new Intent(MainActivity.this, FiveView.class))
                        .getDecorView());
                one.setBackgroundResource(R.drawable.frame_button_nopressbg);
                two.setBackgroundResource(R.drawable.frame_button_nopressbg);
                three.setBackgroundResource(R.drawable.frame_button_nopressbg);
                four.setBackgroundResource(R.drawable.frame_button_nopressbg);
                five.setBackgroundResource(R.drawable.frame_button_background);
                break;
            default:
                break;
        }
    }
}

