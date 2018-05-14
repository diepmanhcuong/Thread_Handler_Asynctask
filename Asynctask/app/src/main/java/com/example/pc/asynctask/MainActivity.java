package com.example.pc.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button bt;
    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.btCount);
        tx = findViewById(R.id.txView);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doIncrese();
            }
        });
    }

    public void doIncrese(){
        Increse increse = new Increse();
        increse.execute();
    }
    public class Increse extends AsyncTask<Void,Integer,Void>{
        int time =0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"Start",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    do {
                        time++;
                        publishProgress(time);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }while (time<20);
                }
            });
            thread.start();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tx.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(),"finish",Toast.LENGTH_SHORT).show();
        }
    }

}
