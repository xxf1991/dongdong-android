package com.example.dongdong.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.example.dongdong.R;

import android.os.Handler;

/**
 * Created by xxf on 2014/12/7.
 */
public class PrivateSettingGenralCleanActivity extends Activity{
    ProgressBar progressBar;
    int progress=0;
    Button btn_progressBar;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_setting_genral_clean);
        progressBar = (ProgressBar)findViewById(R.id.color_progressBar);
        progressBar.setMax(100);
        btn_progressBar = (Button)findViewById(R.id.btn_progressBar);
        btn_progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(progress++<10000){
                            try {
                                Thread.sleep(100);
                                progressBar.setProgress(progress);
                            }
                            catch (Exception e){

                            }
                        }
                    }
                }).start();
            }
        });
    }
}
