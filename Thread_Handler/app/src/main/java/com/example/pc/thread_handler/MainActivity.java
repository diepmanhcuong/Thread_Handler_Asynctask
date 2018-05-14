package com.example.pc.thread_handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int MESSAGE_COUNT_DOWN = 1000;
    public static final int MESSAGE_EMPTY = 1001;
    private Button mBt;
    private TextView txView;
    private Handler  mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        //initHandler();
    }

    //Use handler change text
//
//    public void initHandler(){
//        mHandler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what){
//                    case MESSAGE_COUNT_DOWN:
//                        txView.setText(String.valueOf(msg.arg1));
//                        break;
//                        case  MESSAGE_EMPTY:
//                            Toast.makeText(getApplicationContext(),"done!",Toast.LENGTH_SHORT).show();
//                            break;
//                            default:
//                                break;
//                }
//            }
//        };
//    }
    private void initView() {
        mBt = findViewById(R.id.btCountDown);
        txView = findViewById(R.id.txView);
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCountDown();
            }
        });
    }

    private void doCountDown() {
      final Thread thread = new Thread(new Runnable() {

          @Override
          public void run() {
            int time =10;
            do{
                time--;
                final int finalTime = time;
                txView.post(new Runnable() {
                    @Override
                    public void run() {
                        txView.setText(String.valueOf(finalTime));
                    }
                });
              //  Message msg = new Message();
//                msg.what = MESSAGE_COUNT_DOWN;
//                msg.arg1 = time;
//                mHandler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }while (time>0);
           // mHandler.sendEmptyMessage(MESSAGE_EMPTY);
          }
      });
      thread.start();
    }
}
