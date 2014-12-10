package com.example.dongdong.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.dongdong.R;

/**
 * @author deofly
 * @since 1.0 2014/12/05
 */
public class PrivateSettingGeneralActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_setting_general);
        findViewById(R.id.clean).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()){
            case R.id.clean:
                intent = new Intent(this, PrivateSettingGenralCleanActivity.class);
                break;
            default:
                break;
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}
